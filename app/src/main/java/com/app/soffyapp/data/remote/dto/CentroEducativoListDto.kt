package com.app.soffyapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CentroEducativoListDto(
    @SerializedName("success") val success: Boolean,
    @SerializedName("alumno") val alumno: String,
    @SerializedName("results") val results: List<CentroEducativoResultDto>,
)
