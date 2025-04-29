package com.app.soffyapp.data.remote.auth

import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Cliente para manejar la autenticación con Google
 */
@Singleton
class GoogleAuthClient @Inject constructor(
    private val context: Context,
    private val clientId: String
) {
    private val TAG = "GoogleAuthClient"

    // Crear cliente de Google Sign-In con opciones específicas
    private val googleSignInClient: GoogleSignInClient by lazy {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(clientId) // Solicitar token ID para autenticación con backend
            .requestEmail()           // Solicitar email para identificación de usuario
            .build()

        GoogleSignIn.getClient(context, gso)
    }

    /**
     * Obtiene el intent para iniciar el flujo de inicio de sesión con Google
     */
    fun getSignInIntent(): Intent {
        // Registra el proceso para debugging
        Log.d(TAG, "Iniciando proceso de Sign-In con Google. Client ID: $clientId")
        return googleSignInClient.signInIntent
    }

    /**
     * Procesa el resultado de la actividad de inicio de sesión para obtener el token ID
     * @param intent Intent devuelto de la actividad de inicio de sesión
     * @return Result con el token ID o error
     */
    suspend fun handleSignInResult(intent: Intent?): Result<String> {
        if (intent == null) {
            Log.e(TAG, "Intent es nulo, no se puede procesar el resultado")
            return Result.failure(Exception("No se recibieron datos de inicio de sesión"))
        }

        return try {
            // Obtener tarea de la cuenta con el intent
            val task = GoogleSignIn.getSignedInAccountFromIntent(intent)

            try {
                // Extraer la cuenta del resultado
                val account = task.await()
                val idToken = account.idToken

                if (idToken.isNullOrEmpty()) {
                    Log.e(TAG, "Token ID nulo o vacío")
                    Result.failure(Exception("No se pudo obtener el token de Google"))
                } else {
                    // Mostrar parte del token para debugging (no el token completo por seguridad)
                    val tokenPreview = idToken.take(15) + "..." + idToken.takeLast(10)
                    Log.d(TAG, "Token ID obtenido correctamente: $tokenPreview")
                    Result.success(idToken)
                }
            } catch (e: ApiException) {
                // Manejo de errores específicos de la API de Google
                val errorMessage = when (e.statusCode) {
                    // Códigos de error comunes de Google Sign-In
                    12500 -> "Error de Google Play Services, actualiza la aplicación"
                    12501 -> "Inicio de sesión cancelado"
                    12502 -> "Error de conexión, verifica tu internet"
                    else -> "Error en la autenticación: Código ${e.statusCode}"
                }
                Log.e(TAG, "ApiException: $errorMessage")
                Result.failure(Exception(errorMessage))
            }
        } catch (e: Exception) {
            // Manejo de otros errores no específicos
            Log.e(TAG, "Error inesperado: ${e.message}")
            Result.failure(Exception("Error inesperado: ${e.message}"))
        }
    }

    /**
     * Cierra la sesión actual del usuario con Google
     */
    suspend fun signOut() {
        try {
            googleSignInClient.signOut().await()
            Log.d(TAG, "Sesión cerrada correctamente")
        } catch (e: Exception) {
            Log.e(TAG, "Error al cerrar sesión: ${e.message}")
            throw e
        }
    }
}