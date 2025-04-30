package com.app.soffyapp.domain.usecase

import com.app.soffyapp.domain.model.CentroEducativo
import com.app.soffyapp.domain.repository.CentroEducativoRepository
import com.app.soffyapp.presentation.common.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCentroEducativoListUseCase @Inject constructor(
    private val repository: CentroEducativoRepository
) {
    operator fun invoke(idExpediente: Int): Flow<Result<Pair<String, List<CentroEducativo>>>> = flow {
        emit(Result.Loading)
        try {
            val (alumno, lista) = repository.getCentroEducativoList(idExpediente)
            emit(Result.Success(Pair(alumno, lista)))
        } catch (e: Exception) {
            emit(Result.Error(e))
        }
    }
}
