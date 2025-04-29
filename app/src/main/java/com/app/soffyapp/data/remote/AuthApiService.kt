package com.app.soffyapp.data.remote

import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Interfaz para las llamadas de API relacionadas con autenticación
 */
interface AuthApiService {
    /**
     * Envía el token de Google al backend para autenticación
     * @param idToken Token ID proporcionado por Google
     * @return Respuesta con el token JWT del backend
     */
    @POST("auth/google")
    suspend fun loginWithGoogle(@Body idToken: String): TokenResponse
}

/**
 * Modelo de datos para la respuesta del servidor con el token JWT
 */
data class TokenResponse(
    val token: String,
    val expiresIn: Long
)