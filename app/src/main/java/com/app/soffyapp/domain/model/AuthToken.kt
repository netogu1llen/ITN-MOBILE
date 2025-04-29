package com.app.soffyapp.domain.model

/**
 * Data class representing an authentication token
 */
data class AuthToken(
    val token: String,
    val refreshToken: String,
    val expiresAt: Long
)