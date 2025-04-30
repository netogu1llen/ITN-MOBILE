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
        return listOf(
            Paciente(
                idExpediente = 1,
                nombre = "Diego",
                apellidoPaterno = "Ponce",
                apellidoMaterno = "B",
                fechaNacimiento = "2003-01-01",
                telefono = "1234567890",
                estado = "Querétaro",
                ciudad = "Querétaro",
                calle = "Calle Falsa",
                codigoPostal = "76000",
                localidad = "Centro",
                numeroCasa = "123",
                enfermedades = "Ninguna",
                medicamentos = "Ninguno",
                estudioSocioeconomico = "Alto",
                tipoSangre = "O+",
                grado = "Universidad",
                nivelEscolar = "Superior"
            ),
            Paciente(
                idExpediente = 2,
                nombre = "María",
                apellidoPaterno = "López",
                apellidoMaterno = "Ramírez",
                fechaNacimiento = "1985-05-10",
                telefono = "0987654321",
                estado = "CDMX",
                ciudad = "Ciudad de México",
                calle = "Otra Calle",
                codigoPostal = "06700",
                localidad = "Roma",
                numeroCasa = "456",
                enfermedades = "Asma",
                medicamentos = "Inhalador",
                estudioSocioeconomico = "Medio",
                tipoSangre = "A-",
                grado = "Licenciatura",
                nivelEscolar = "Superior"
            )
        )
    }

    override suspend fun getPacienteById(id: String): Paciente {
        return Paciente(
            idExpediente = id.toInt(),
            nombre = "Paciente $id",
            apellidoPaterno = "Apellido$id",
            apellidoMaterno = "Materno$id",
            fechaNacimiento = "1995-01-01",
            telefono = "123456789",
            estado = "Estado",
            ciudad = "Ciudad",
            calle = "Calle",
            codigoPostal = "00000",
            localidad = "Localidad",
            numeroCasa = "0",
            enfermedades = "Ninguna",
            medicamentos = "Ninguno",
            estudioSocioeconomico = "Alto",
            tipoSangre = "A+",
            grado = "Universidad",
            nivelEscolar = "Superior"
        )
    }
}
