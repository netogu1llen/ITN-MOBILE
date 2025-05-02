package com.app.soffyapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CentroEducativoResultDto(
    @SerializedName("IDBoleta") val idBoleta: Int,
    @SerializedName("periodoEscolar") val periodoEscolar: String,
    @SerializedName("grado") val grado: String,
    @SerializedName("nvEscolar") val nvEscolar: String,
    @SerializedName("promedio") val promedio: String,
)
