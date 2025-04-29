package com.app.soffyapp.data.remote

import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Clase para encapsular el token ID de Google en la petición al backend
 */
data class GoogleAuthRequest(
    val idToken: String
)

/**
 * Respuesta del servidor con el token JWT
 */
data class TokenResponse(
    val token: String,
    val expiresIn: Long,
    val success: Boolean = true,
    val message: String? = null
)

/**
 * Interfaz para las llamadas de API relacionadas con autenticación
 */
interface AuthApiService {
    /**
     * Envía el token de Google al backend para autenticación
     * Endpoint específico para aplicaciones móviles
     */
    @POST("google/mobile")
    suspend fun loginWithGoogle(@Body request: GoogleAuthRequest): TokenResponse
}