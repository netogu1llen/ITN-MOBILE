package com.app.soffyapp.domain.repository

import com.app.soffyapp.domain.model.Paciente

interface PacientesRepository {
    suspend fun getPacientesList(): List<Paciente>

    suspend fun getPacienteById(id: String): Paciente
}
