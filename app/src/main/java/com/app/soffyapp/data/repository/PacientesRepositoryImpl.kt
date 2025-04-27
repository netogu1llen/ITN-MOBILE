package com.app.soffyapp.data.repository

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
        return response.results.map { entity ->
            Paciente(
                idExpediente = entity.idExpediente,
                nombre = entity.nombre,
                apellidoPaterno = entity.apellidoPaterno,
                apellidoMaterno = entity.apellidoMaterno,
                fechaNacimiento = entity.fechaNacimiento,
                telefono = entity.telefono,
                estado = entity.estado,
                ciudad = entity.ciudad,
                calle = entity.calle,
                codigoPostal = entity.codigoPostal,
                localidad = entity.localidad,
                numeroCasa = entity.numeroCasa,
                enfermedades = entity.enfermedades,
                medicamentos = entity.medicamentos,
                estudioSocioeconomico = entity.estudioSocioeconomico,
                tipoSangre = entity.tipoSangre,
                grado = entity.grado,
                nivelEscolar = entity.nivelEscolar
            )
        }
    }

    override suspend fun getPacienteById(id: String): Paciente {
        // Implementación temporal utilizando datos simulados
        return Paciente.getMockData().firstOrNull { it.idExpediente.toString() == id }
            ?: Paciente.getMockData().first() // Fallback en caso de no encontrar coincidencia
    }
}
