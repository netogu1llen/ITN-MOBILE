package com.app.soffyapp.data.repository

import com.app.soffyapp.data.mapper.toPaciente
import com.app.soffyapp.data.remote.api.PacientesApi
import com.app.soffyapp.domain.model.Paciente
import com.app.soffyapp.domain.repository.PacientesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PacientesRepositoryImpl @Inject constructor(
    private val api: PacientesApi
) : PacientesRepository {

    override suspend fun getPacientesList(): List<Paciente> {
        val response = api.getPacientesList()
        return response.results.map { it.toPaciente() }
    }

    override suspend fun getPacienteById(id: String): Paciente {
        val response = api.getPaciente(id)
        return response.toPaciente()
    }
}