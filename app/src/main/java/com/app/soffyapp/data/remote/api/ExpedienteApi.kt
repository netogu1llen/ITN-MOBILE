package com.app.soffyapp.data.remote.api

import com.app.soffyapp.data.remote.dto.ExpedienteResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

// Interfaz que define la comunicación con el endpoint remoto de "expediente".
// Utiliza Retrofit y devuelve un DTO con los datos generales del expediente.
interface ExpedienteApi {
    @GET("api/expediente/{id}")
    suspend fun getExpediente(
        @Path("id") id: Int,
    ): ExpedienteResponseDto
}
