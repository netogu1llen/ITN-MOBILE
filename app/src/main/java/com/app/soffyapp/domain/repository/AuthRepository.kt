package com.app.soffyapp.domain.repository

import com.app.soffyapp.domain.model.AuthToken

/**
 * Interfaz que define las operaciones del repositorio de autenticación
 */
interface AuthRepository {
    /**
     * Autentica al usuario con un token de Google
     * @param idToken Token ID proporcionado por Google
     * @return Token de autenticación generado por el backend
     */
    suspend fun loginWithGoogle(idToken: String): AuthToken
}