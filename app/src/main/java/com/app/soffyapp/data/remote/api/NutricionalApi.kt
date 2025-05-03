package com.app.soffyapp.data.remote.api

import com.app.soffyapp.data.remote.dto.NutricionalResultDto
import retrofit2.http.GET
import retrofit2.http.Path

interface NutricionalApi {
    @GET("/api/nutricional/{idExpediente}")
    suspend fun getNutricionalData(
        @Path("idExpediente") idExpediente: Int
    ): NutricionalResultDto
}
