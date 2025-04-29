package com.app.soffyapp.data.repository

import android.util.Log
import com.app.soffyapp.data.remote.AuthApiService
import com.app.soffyapp.data.remote.GoogleAuthRequest
import com.app.soffyapp.domain.model.AuthToken
import com.app.soffyapp.domain.repository.AuthRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Implementación del repositorio de autenticación
 */
class AuthRepositoryImpl @Inject constructor(
    private val authApiService: AuthApiService
) : AuthRepository {
    private val TAG = "AuthRepository"

    /**
     * Autentica al usuario con un token de Google
     * @param idToken Token ID proporcionado por Google
     * @return Token de autenticación generado por el backend
     * @throws Exception si falla la autenticación
     */
    override suspend fun loginWithGoogle(idToken: String): AuthToken {
        Log.d(TAG, "Iniciando loginWithGoogle con idToken")

        try {
            // Crear la petición con el token ID
            val request = GoogleAuthRequest(idToken = idToken)

            // Enviar la petición al backend
            val response = authApiService.loginWithGoogle(request)

            // Verificar si la respuesta fue exitosa
            if (!response.success) {
                val errorMsg = response.message ?: "Error de autenticación"
                Log.e(TAG, "Error en respuesta del servidor: $errorMsg")
                throw Exception(errorMsg)
            }

            // Convertir la respuesta al modelo de dominio
            Log.d(TAG, "Autenticación exitosa, token JWT recibido")
            return AuthToken(
                token = response.token,
                refreshToken = null, // No se incluye refresh token en la respuesta
                expiresAt = System.currentTimeMillis() + (response.expiresIn * 1000)
            )
        } catch (e: HttpException) {
            // Error HTTP (4xx, 5xx)
            val errorBody = e.response()?.errorBody()?.string()
            Log.e(TAG, "Error HTTP ${e.code()}: $errorBody")
            throw Exception("Error de conexión con el servidor: ${e.message()}")
        } catch (e: IOException) {
            // Error de red
            Log.e(TAG, "Error de red: ${e.message}")
            throw Exception("Error de conexión: Verifica tu internet")
        } catch (e: Exception) {
            // Otros errores
            Log.e(TAG, "Error inesperado: ${e.message}")
            throw Exception("Error al autenticar: ${e.message}")
        }
    }
}
