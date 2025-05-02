package com.app.soffyapp.data.mapper

import com.app.soffyapp.data.remote.dto.DetalleCompletoDto
import com.app.soffyapp.data.remote.dto.ObjetivoPsicologicoDto
import com.app.soffyapp.data.remote.dto.PsicologiaResultDto
import com.app.soffyapp.domain.model.ObjetivoPsicologico
import com.app.soffyapp.domain.model.Psicologia

fun PsicologiaResultDto.toDomain(): Psicologia =
    Psicologia(
        idSeguimiento = idSeguimiento,
        numSesion = numSesion,
        fecha = fecha,
        sesionObjetivo = sesionObjetivo,
        sesionJustificacion = sesionJustificacion,
        analisisPsicologico = analisisPsicologico,
        recomendaciones = recomendaciones,
        sesionBitacora = sesionBitacora
    )

fun ObjetivoPsicologicoDto.toDomain(): ObjetivoPsicologico = ObjetivoPsicologico(
    idObjetivo = idObjetivo,
    objetivo = objetivo,
    actividad = actividad,
    tiempo = tiempo,
    metodologia = metodologia,
    observaciones = observaciones
)

fun DetalleCompletoDto.toDomain(): Psicologia = Psicologia(
    idSeguimiento = idSeguimiento,
    numSesion = numSesion,
    fecha = fecha,
    sesionObjetivo = sesionObjetivo,
    sesionJustificacion = sesionJustificacion,
    analisisPsicologico = analisisPsicologico,
    recomendaciones = recomendaciones,
    sesionBitacora = sesionBitacora,
    objetivos = objetivos.map { it.toDomain() }
)