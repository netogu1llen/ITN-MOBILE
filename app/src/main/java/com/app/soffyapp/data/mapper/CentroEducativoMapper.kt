package com.app.soffyapp.data.mapper

import com.app.soffyapp.data.remote.dto.CentroEducativoResultDto
import com.app.soffyapp.domain.model.CentroEducativo

// Función de mapeo para convertir un DTO de red a un modelo de dominio.
// Esto permite desacoplar la capa de datos remotos de la lógica de negocio.
fun CentroEducativoResultDto.toDomain(): CentroEducativo =
    CentroEducativo(
        idBoleta = idBoleta,
        periodoEscolar = periodoEscolar,
        grado = grado,
        nvEscolar = nvEscolar,
        promedio = promedio
    )
