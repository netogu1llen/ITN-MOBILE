package com.app.soffyapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ExpedienteDto(
    @SerializedName("IDExpediente") val idExpediente: String,
    @SerializedName("nombreCompleto") val nombreCompleto: String,
    @SerializedName("numExpediente") val numExpediente: String,
    @SerializedName("fechaNacimiento") val fechaNacimiento: String,
    @SerializedName("contacto") val contacto: String,
    @SerializedName("estado") val estado: String,
    @SerializedName("ciudad") val ciudad: String,
    @SerializedName("calle") val calle: String,
    @SerializedName("cp") val cp: String,
    @SerializedName("localidad") val localidad: String,
    @SerializedName("numCasa") val numCasa: String,
    @SerializedName("enfermedades") val enfermedades: String,
    @SerializedName("medicamentos") val medicamentos: String,
    @SerializedName("estudioSocioeconomico") val estudioSocioeconomico: String,
    @SerializedName("grado") val grado: String,
    @SerializedName("nvEscolar") val nvEscolar: String,
    @SerializedName("sangre") val sangre: String,
)
