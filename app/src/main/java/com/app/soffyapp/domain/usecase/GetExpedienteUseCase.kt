package com.app.soffyapp.domain.usecase

import com.app.soffyapp.domain.model.Expediente
import com.app.soffyapp.domain.repository.ExpedienteRepository
import com.app.soffyapp.presentation.common.Result
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetExpedienteUseCase
@Inject
constructor(
    private val repository: ExpedienteRepository,
) {
    operator fun invoke(id: Int): Flow<Result<Expediente>> =
        flow {
            try {
                emit(Result.Loading)
                val expediente = repository.getExpedienteById(id)
                emit(Result.Success(expediente))
            } catch (e: Exception) {
                emit(Result.Error(e))
            }
        }
}