package com.app.soffyapp.data.mapper

import com.app.soffyapp.data.local.entity.PacienteEntity
import com.app.soffyapp.domain.model.Paciente

fun PacienteEntity.toPaciente() = Paciente(
    idExpediente = idExpediente,
    nombre = nombre,
    apellidoPaterno = apellidoPaterno,
    apellidoMaterno = apellidoMaterno,
    fechaNacimiento = fechaNacimiento,
    telefono = telefono,
    estado = estado,
    ciudad = ciudad,
    calle = calle,
    codigoPostal = codigoPostal,
    localidad = localidad,
    numeroCasa = numeroCasa,
    enfermedades = enfermedades,
    medicamentos = medicamentos,
    estudioSocioeconomico = estudioSocioeconomico,
    tipoSangre = tipoSangre,
    grado = grado,
    nivelEscolar = nivelEscolar
)
