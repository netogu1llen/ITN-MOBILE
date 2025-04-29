package com.app.soffyapp.presentation.screens.login

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.soffyapp.data.local.TokenDataStore
import com.app.soffyapp.data.remote.auth.GoogleAuthClient
import com.app.soffyapp.domain.usecase.GoogleLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Representa los posibles estados de la UI durante el login
 *
 * Patrón sealed class que encapsula todos los estados posibles:
 * - Idle: Estado inicial/inactivo
 * - Loading: Cuando se está procesando el login
 * - Success: Cuando el login es exitoso (contiene mensaje)
 * - Error: Cuando ocurre un error (contiene mensaje de error)
 */
sealed class LoginUiState {
    object Idle : LoginUiState()          // Estado inicial/inactivo
    object Loading : LoginUiState()       // Login en progreso
    data class Success(val message: String) : LoginUiState()  // Login exitoso
    data class Error(val message: String) : LoginUiState()    // Error en login
}

/**
 * ViewModel para manejar la lógica de autenticación usando Hilt
 *
 * Responsabilidades:
 * - Gestionar el estado del login
 * - Comunicarse con Google Sign-In a través del GoogleAuthClient
 * - Interactuar con el backend a través del GoogleLoginUseCase
 * - Almacenar el token JWT recibido a través del TokenDataStore
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val googleLoginUseCase: GoogleLoginUseCase,
    private val tokenDataStore: TokenDataStore,
    private val googleAuthClient: GoogleAuthClient
) : ViewModel() {

    // Flujo mutable para el estado (privado)
    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    // Flujo público de solo lectura para observar el estado
    val uiState: StateFlow<LoginUiState> = _uiState

    /**
     * Obtiene el Intent para iniciar el flujo de Google Sign-In
     * @return Intent para iniciar el flujo de autenticación
     */
    fun getGoogleSignInIntent(): Intent {
        return googleAuthClient.getSignInIntent()
    }

    /**
     * Procesa el resultado de la autenticación con Google
     * @param data Intent con los datos de resultado de la autenticación
     */
    fun handleGoogleSignInResult(data: Intent?) {
        _uiState.value = LoginUiState.Loading

        viewModelScope.launch {
            try {
                // 1. Extraer el token ID de Google
                val idToken = extractGoogleIdToken(data)
                    ?: throw Exception("No se pudo obtener el ID Token de Google")

                // 2. Usar el caso de uso para autenticar con el backend
                googleLoginUseCase(idToken).collect { result ->
                    result.onSuccess { authToken ->
                        // 3. Guardar el token recibido
                        tokenDataStore.saveToken(authToken.token)
                        _uiState.value = LoginUiState.Success("Sesión iniciada correctamente")
                    }.onFailure { exception ->
                        _uiState.value = LoginUiState.Error("Error: ${exception.message}")
                    }
                }
            } catch (e: Exception) {
                _uiState.value = LoginUiState.Error("Error: ${e.message}")
            }
        }
    }

    /**
     * Extrae el token ID de Google del intent de resultado
     * @param data Intent con los datos de resultado
     * @return Token ID o null si no se pudo obtener
     */
    private suspend fun extractGoogleIdToken(data: Intent?): String? {
        return try {
            val task = com.google.android.gms.auth.api.signin.GoogleSignIn
                .getSignedInAccountFromIntent(data)
            task.await().idToken
        } catch (e: Exception) {
            null
        }
    }
}