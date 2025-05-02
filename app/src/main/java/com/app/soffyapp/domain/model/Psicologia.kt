package com.app.soffyapp.domain.model

data class ObjetivoPsicologico(
    val idObjetivo: Int,
    val objetivo: String,
    val actividad: String,
    val tiempo: String,
    val metodologia: String,
    val observaciones: String
)

data class Psicologia(
    val idSeguimiento: Int,
    val numSesion: Int,
    val fecha: String,
    val sesionObjetivo: String,
    val sesionJustificacion: String,
    val analisisPsicologico: String,
    val recomendaciones: String,
    val sesionBitacora: String,
    val objetivos: List<ObjetivoPsicologico> = emptyList()
)