package com.app.soffyapp.domain.usecase

import com.app.soffyapp.domain.model.AuthToken
import com.app.soffyapp.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Caso de uso para la autenticación con Google
 * Abstrae la lógica de negocio entre el ViewModel y el repositorio
 */
class GoogleLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    /**
     * Invoca el caso de uso para autenticar con Google
     * @param idToken Token ID proporcionado por Google
     * @return Flow con resultado de autenticación (éxito o error)
     */
    operator fun invoke(idToken: String): Flow<Result<AuthToken>> = flow {
        try {
            val authToken = authRepository.loginWithGoogle(idToken)
            emit(Result.success(authToken))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}