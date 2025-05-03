package com.app.soffyapp.data.mapper

import com.app.soffyapp.data.remote.dto.ExpedienteDto
import com.app.soffyapp.domain.model.Expediente

// Convierte un DTO recibido desde la API a un modelo de dominio usable en la app.
// Esta separación ayuda a mantener limpia la lógica del dominio y desacoplarla de los datos externos.
fun ExpedienteDto.toDomain(): Expediente =
    Expediente(
        idExpediente = idExpediente,
        nombreCompleto = nombreCompleto,
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
