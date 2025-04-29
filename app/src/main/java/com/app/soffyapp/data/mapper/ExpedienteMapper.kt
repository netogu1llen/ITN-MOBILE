package com.app.soffyapp.data.mapper

import com.app.soffyapp.data.remote.dto.ExpedienteDto
import com.app.soffyapp.domain.model.Expediente

fun ExpedienteDto.toDomain(): Expediente =
    Expediente(
        idExpediente = idExpediente,
        nombreCompleto = nombreCompleto,
        numExpediente = numExpediente,
        fechaNacimiento = fechaNacimiento,
        contacto = contacto,
        estado = estado,
        ciudad = ciudad,
        calle = calle,
        cp = cp,
        localidad = localidad,
        numCasa = numCasa,
        enfermedades = enfermedades,
        medicamentos = medicamentos,
        estudioSocioeconomico = estudioSocioeconomico,
        grado = grado,
        nvEscolar = nvEscolar,
        sangre = sangre,
    )
