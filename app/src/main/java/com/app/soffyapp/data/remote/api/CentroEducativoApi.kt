package com.app.soffyapp.data.remote.api

import com.app.soffyapp.data.remote.dto.CentroEducativoListDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CentroEducativoApi {
    @GET("boletas/{idExpediente}")
    suspend fun getCentroEducativoList(
        @Path("idExpediente") idExpediente: Int
    ): CentroEducativoListDto
}
