package com.app.soffyapp.domain.model

/**
 * Data class representing an authentication token
 * The refreshToken is nullable since the web backend might not provide it
 */
data class AuthToken(
    val token: String,
    val refreshToken: String?, // Ahora es nullable
    val expiresAt: Long
)