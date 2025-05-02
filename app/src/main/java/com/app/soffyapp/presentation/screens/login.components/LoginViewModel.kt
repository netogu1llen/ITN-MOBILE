package com.app.soffyapp.presentation.screens.login.components

import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.soffyapp.data.local.TokenDataStore
import com.app.soffyapp.data.remote.auth.GoogleAuthClient
import com.app.soffyapp.domain.usecase.GoogleLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Estados posibles de la UI durante el login
 */
sealed class LoginUiState {
    object Idle : LoginUiState()
    object Loading : LoginUiState()
    data class Success(val message: String) : LoginUiState()
    data class Error(val message: String) : LoginUiState()
    object NeedsReauth : LoginUiState() // Nuevo estado para indicar que se necesita reautenticación
}

/**
 * ViewModel para manejar la lógica de autenticación
 * Con soporte para reintentos automáticos en caso de DEVELOPER_ERROR
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val googleLoginUseCase: GoogleLoginUseCase,
    private val tokenDataStore: TokenDataStore,
    private val googleAuthClient: GoogleAuthClient
) : ViewModel() {
    private val TAG = "LoginViewModel"

    // Estado de la UI
    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState

    // Contador de reintentos para evitar bucles infinitos
    private var retryCount = 0
    private val MAX_RETRIES = 3

    /**
     * Obtiene el Intent para iniciar el flujo de Google Sign-In
     */
    fun getGoogleSignInIntent(): Intent {
        return googleAuthClient.getSignInIntent()
    }

    /**
     * Resetea el contador de reintentos
     * Llamar cuando el usuario inicia manualmente un nuevo intento de login
     */
    fun resetRetryCount() {
        retryCount = 0
    }

    /**
     * Procesa el resultado de la autenticación con Google
     * Con manejo automático de DEVELOPER_ERROR
     *
     * @param data Intent con los datos de resultado de la autenticación
     */
    fun handleGoogleSignInResult(data: Intent?) {
        // Actualizar estado a Loading
        _uiState.value = LoginUiState.Loading
        Log.d(TAG, "Procesando resultado de Sign-In con Google")

        viewModelScope.launch {
            try {
                // 1. Procesar el resultado para obtener el token ID
                val idTokenResult = googleAuthClient.handleSignInResult(data)

                idTokenResult.fold(
                    onSuccess = { idToken ->
                        Log.d(TAG, "Token ID obtenido, autenticando con backend")

                        // Resetear contador de reintentos tras éxito
                        retryCount = 0

                        // 2. Autenticar con el backend usando el token
                        googleLoginUseCase(idToken).collect { result ->
                            result.onSuccess { authToken ->
                                // 3. Guardar el token JWT recibido
                                tokenDataStore.saveToken(authToken.token)
                                Log.d(TAG, "Autenticación exitosa, token JWT guardado")
                                _uiState.value = LoginUiState.Success("Sesión iniciada correctamente")
                            }.onFailure { exception ->
                                // Error en la autenticación con el backend
                                Log.e(TAG, "Error en backend: ${exception.message}")
                                _uiState.value = LoginUiState.Error("Error: ${exception.message}")
                            }
                        }
                    },
                    onFailure = { exception ->
                        // Error al obtener el token ID de Google
                        val errorMessage = exception.message ?: "Error desconocido"
                        Log.e(TAG, "Error al obtener token ID: $errorMessage")

                        // Detectar DEVELOPER_ERROR y aplicar reintento automático
                        if (errorMessage.contains("DEVELOPER_ERROR", ignoreCase = true) &&
                            retryCount < MAX_RETRIES) {

                            retryCount++
                            Log.d(TAG, "Detectado DEVELOPER_ERROR, intentando con configuración alternativa #$retryCount")

                            // Cambiar a la siguiente configuración
                            val hasMoreConfigs = googleAuthClient.switchToNextConfig()

                            if (hasMoreConfigs) {
                                // Notificar a la UI que necesitamos iniciar de nuevo la autenticación
                                _uiState.value = LoginUiState.NeedsReauth
                            } else {
                                // Si ya no hay más configuraciones, mostrar error
                                _uiState.value = LoginUiState.Error(
                                    "Error de configuración persistente. Verifica que la aplicación esté correctamente registrada en Google Cloud Console."
                                )
                            }
                        } else {
                            // Error normal o se agotaron los reintentos
                            _uiState.value = LoginUiState.Error("Error: $errorMessage")
                        }
                    }
                )
            } catch (e: Exception) {
                // Error inesperado
                Log.e(TAG, "Error inesperado: ${e.message}", e)
                _uiState.value = LoginUiState.Error("Error inesperado: ${e.message}")
            }
        }
    }
}