package com.app.soffyapp.data.mapper

import com.app.soffyapp.data.remote.dto.PacienteDto
import com.app.soffyapp.domain.model.Paciente

fun PacienteDto.toPaciente(): Paciente {
    return Paciente(
        idExpediente = this.idExpediente ?: 0,
        nombre = this.nombre ?: "",
        apellidoPaterno = this.apellidoPaterno ?: "",
        apellidoMaterno = this.apellidoMaterno ?: "",
        fechaNacimiento = this.fechaNacimiento ?: "",
        telefono = this.telefono ?: "",
        estado = this.estado ?: "",
        ciudad = this.ciudad ?: "",
        calle = this.calle ?: "",
        codigoPostal = this.codigoPostal ?: "",
        localidad = this.localidad ?: "",
        numeroCasa = this.numeroCasa ?: "",
        enfermedades = this.enfermedades ?: "",
        medicamentos = this.medicamentos ?: "",
        estudioSocioeconomico = this.estudioSocioeconomico ?: "",
        tipoSangre = this.tipoSangre ?: "",
        grado = this.grado ?: "",
        nivelEscolar = this.nivelEscolar ?: ""
    )
}
