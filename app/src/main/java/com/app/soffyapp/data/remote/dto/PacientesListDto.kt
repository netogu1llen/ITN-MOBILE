package com.app.soffyapp.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PacientesListDto(
    @SerializedName("success") val success: Boolean,
    @SerializedName("results") val results: List<PacientesResultDto>,
)
