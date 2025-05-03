package com.app.soffyapp.domain.usecase

import com.app.soffyapp.data.mapper.toDomain
import com.app.soffyapp.domain.model.Psicologia
import com.app.soffyapp.domain.repository.PsicologiaRepository
import com.app.soffyapp.presentation.common.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPsicologiaListUseCase @Inject constructor(
    private val repository: PsicologiaRepository
) {
    operator fun invoke(idExpediente: Int): Flow<Result<Pair<String, List<Psicologia>>>> = flow {
        try {
            emit(Result.Loading)
            val result = repository.getPsicologiaList(idExpediente)
            emit(Result.Success(
                Pair(
                    result.alumno,
                    result.results.map { it.toDomain() }
                )
            ))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}