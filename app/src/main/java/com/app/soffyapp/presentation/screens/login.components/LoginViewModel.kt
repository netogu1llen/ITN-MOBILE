package com.app.soffyapp.presentation.screens.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.soffyapp.data.local.TokenDataStore
import com.app.soffyapp.network.api.enviarTokenGoogleAlBackend
import com.google.android.gms.auth.api.signin.GoogleSignIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

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
 * ViewModel para manejar la lógica de autenticación
 *
 * Responsabilidades:
 * - Gestionar el estado del login
 * - Comunicarse con Google Sign-In
 * - Interactuar con el backend
 * - Almacenar el token JWT recibido
 */
class LoginViewModel : ViewModel() {

    // Flujo mutable para el estado (privado)
    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    // Flujo público de solo lectura para observar el estado
    val uiState: StateFlow<LoginUiState> = _uiState

    /**
     * Inicia el proceso de login con Google
     * @param context Contexto de Android necesario para Google Sign-In
     */
    fun iniciarSesionConGoogle(context: Context) {
        // Actualiza estado a Loading
        _uiState.value = LoginUiState.Loading

        viewModelScope.launch {
            // 1. Obtener cuenta de Google previamente autenticada
            val cuenta = GoogleSignIn.getLastSignedInAccount(context)
            val idToken = cuenta?.idToken

            // 2. Verificar si se obtuvo token
            if (idToken == null) {
                _uiState.value = LoginUiState.Error("No se pudo obtener el ID Token de Google")
                return@launch
            }

            // 3. Enviar token al backend y obtener JWT
            val jwt = enviarTokenGoogleAlBackend(idToken)

            // 4. Manejar respuesta del backend
            if (jwt != null) {
                // Almacenar token localmente
                TokenDataStore.guardarToken(context, jwt)
                _uiState.value = LoginUiState.Success("Sesión iniciada correctamente")
            } else {
                _uiState.value = LoginUiState.Error("Error al autenticar con el servidor")
            }
        }
    }

    /**
     * Envía el token de Google al backend y obtiene el JWT de la app
     * @param idToken Token de Google obtenido del cliente
     * @return JWT proporcionado por el backend o null si falla
     */
    private suspend fun enviarTokenGoogleAlBackend(idToken: String): String? {
        // Implementación real haría una llamada HTTP al backend
        return try {
            // Ejemplo simulado:
            val response = apiService.loginWithGoogle(idToken)
            response.token
        } catch (e: Exception) {
            null
        }
    }
}