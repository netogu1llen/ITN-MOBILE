package com.app.soffyapp.data.remote.api

import com.app.soffyapp.data.remote.dto.ExpedienteResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ExpedienteApi {
    @GET("expediente/{id}")
    suspend fun getExpediente(
        @Path("id") id: Int,
    ): ExpedienteResponseDto
}