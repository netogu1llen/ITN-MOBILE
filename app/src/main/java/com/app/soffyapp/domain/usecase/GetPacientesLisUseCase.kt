package com.app.soffyapp.domain.usecase

import com.app.soffyapp.domain.model.Paciente
import com.app.soffyapp.domain.repository.PacientesRepository
import com.app.soffyapp.presentation.common.Result
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPacientesListUseCase
    @Inject
    constructor(
        // Inyectado por Hilt
        private val repository: PacientesRepository,
    ) {
        // Puede ser llamado como useCase()
        operator fun invoke(): Flow<Result<List<Paciente>>> =
            flow {
                try {
                    // Primer valor: Loading
                    emit(Result.Loading)

                    // Obtiene datos
                    val pacientesList = repository.getPacientesList()

                    // Segundo valor: Success con datos
                    emit(Result.Success(pacientesList))
                } catch (e: Exception) {
                    // O Error si algo falla
                    emit(Result.Error(e))
                }
            }
    }
