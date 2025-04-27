package com.app.soffyapp.domain.usecase

import com.app.soffyapp.domain.repository.PacientesRepository
import com.app.soffyapp.domain.model.Paciente
import javax.inject.Inject

class GetPacientesListUseCase @Inject constructor(
    private val repository: PacientesRepository,
) {
    suspend operator fun invoke(): List<Paciente> {
        return repository.getPacientesList()
    }
}