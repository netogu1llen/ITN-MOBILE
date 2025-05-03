package com.app.soffyapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ObjetivoPsicologicoDto(
    @SerializedName("idObjetivo") val idObjetivo: Int,
    @SerializedName("objetivo") val objetivo: String,
    @SerializedName("actividad") val actividad: String,
    @SerializedName("tiempo") val tiempo: String,
    @SerializedName("metodologia") val metodologia: String,
    @SerializedName("observaciones") val observaciones: String,
    @SerializedName("fechaCreacion") val fechaCreacion: String,
    @SerializedName("fechaActualizacion") val fechaActualizacion: String
)

data class DetalleSeguimientoResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("detalle") val detalle: DetalleCompletoDto
)

data class DetalleCompletoDto(
    @SerializedName("idSeguimiento") val idSeguimiento: Int,
    @SerializedName("numSesion") val numSesion: Int,
    @SerializedName("fecha") val fecha: String,
    @SerializedName("sesionObjetivo") val sesionObjetivo: String,
    @SerializedName("sesionJustificacion") val sesionJustificacion: String,
    @SerializedName("analisisPsicologico") val analisisPsicologico: String,
    @SerializedName("recomendaciones") val recomendaciones: String,
    @SerializedName("sesionBitacora") val sesionBitacora: String,
    @SerializedName("objetivos") val objetivos: List<ObjetivoPsicologicoDto>
)