package com.app.soffyapp.data.remote.dto

/**
 * Data class para la solicitud de autenticación con Google
 */
data class AuthDto(
    val idToken: String // Token ID de Google
)

/**
 * Data class para la respuesta de autenticación con Google
 */
data class AuthResponse(
    val token: String,  // JWT devuelto por tu backend
    val success: Boolean,
    val message: String? = null
)