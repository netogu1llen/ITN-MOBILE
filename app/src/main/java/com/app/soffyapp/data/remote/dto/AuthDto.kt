package com.app.soffyapp.data.remote.auth.dto

/**
 * Data class para la solicitud de autenticación con Google
 */
data class GoogleAuthRequest(
    val idToken: String
)

/**
 * Data class para la respuesta de autenticación con Google
 */
data class GoogleAuthResponse(
    val token: String,
    val expiresIn: Long
)