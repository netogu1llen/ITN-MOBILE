package com.app.soffyapp.presentation.screens.login

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
}

/**
 * ViewModel para manejar la lógica de autenticación
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

    /**
     * Obtiene el Intent para iniciar el flujo de Google Sign-In
     */
    fun getGoogleSignInIntent(): Intent {
        return googleAuthClient.getSignInIntent()
    }

    /**
     * Procesa el resultado de la autenticación con Google
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
                        Log.e(TAG, "Error al obtener token ID: ${exception.message}")
                        _uiState.value = LoginUiState.Error("Error: ${exception.message}")
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