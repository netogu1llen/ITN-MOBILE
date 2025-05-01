package com.app.soffyapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ExpedienteResponseDto(
    @SerializedName("success") val success: Boolean,
    @SerializedName("expediente") val expediente: ExpedienteDto,
)