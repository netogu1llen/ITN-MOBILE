package com.app.soffyapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PacientesResultDto(
    @SerializedName("IDExpediente") val idExpediente: Int,
    @SerializedName("nombreCompleto") val nombreCompleto: String,
    @SerializedName("fechaNacimiento") val fechaNacimiento: String,
    @SerializedName("nvEscolar") val nvEscolar: String,
)
