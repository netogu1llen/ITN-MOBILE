package com.app.soffyapp.domain.usecase

import com.app.soffyapp.domain.model.Paciente
import com.app.soffyapp.domain.repository.PacientesRepository
import javax.inject.Inject

class GetPacienteByIdUseCase @Inject constructor(
    private val repository: PacientesRepository
) {
    suspend operator fun invoke(id: String): Paciente {
        return repository.getPacienteById(id)
    }
}
