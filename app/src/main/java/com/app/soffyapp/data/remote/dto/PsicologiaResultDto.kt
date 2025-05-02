package com.app.soffyapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PsicologiaResultDto(
    @SerializedName("idSeguimiento") val idSeguimiento: Int,
    @SerializedName("numSesion") val numSesion: Int,
    @SerializedName("fecha") val fecha: String,
    @SerializedName("sesionObjetivo") val sesionObjetivo: String,
    @SerializedName("sesionJustificacion") val sesionJustificacion: String,
    @SerializedName("analisisPsicologico") val analisisPsicologico: String,
    @SerializedName("recomendaciones") val recomendaciones: String,
    @SerializedName("sesionBitacora") val sesionBitacora: String,
)