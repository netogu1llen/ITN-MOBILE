package com.app.soffyapp.data.mapper

import com.app.soffyapp.data.remote.dto.PacientesResultDto
import com.app.soffyapp.domain.model.Paciente

fun PacientesResultDto.toDomain(): Paciente =
    Paciente(
        idExpediente = idExpediente,
        nombreCompleto = nombreCompleto,
        fechaNacimiento = fechaNacimiento,
        nvEscolar = nvEscolar,
    )
