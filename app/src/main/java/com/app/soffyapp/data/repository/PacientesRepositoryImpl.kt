package com.app.soffyapp.data.repository

import com.app.soffyapp.data.mapper.toDomain
import com.app.soffyapp.data.remote.api.PacientesApi
import com.app.soffyapp.domain.model.Paciente
import com.app.soffyapp.domain.repository.PacientesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PacientesRepositoryImpl
    @Inject
    constructor(
        private val api: PacientesApi,
    ) : PacientesRepository {
        override suspend fun getPacientesList(): List<Paciente> {
            // TODO: Reemplazar por llamada real cuando esté lista la API
            // return Paciente.getMockData()
            val response = api.getPacientesList()
            return response.results.map { it.toDomain() }
        }

        override suspend fun getPacienteById(id: String): Paciente {
            return Paciente.getMockData().firstOrNull { it.idExpediente.toString() == id }
                ?: Paciente.getMockData().first() // fallback
        }
    }
