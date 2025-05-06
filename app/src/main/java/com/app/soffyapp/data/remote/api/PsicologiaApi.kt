package com.app.soffyapp.data.remote.api

import com.app.soffyapp.data.remote.dto.DetalleSeguimientoResponse
import com.app.soffyapp.data.remote.dto.PsicologiaListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PsicologiaApi {
    @GET("api/psicologia/{idExpediente}")
    suspend fun getPsicologiaList(
        @Path("idExpediente") idExpediente: String
    ): PsicologiaListDto

    @GET("api/psicologia/detalle/{idSeguimiento}")
    suspend fun getDetalleSeguimiento(
        @Path("idSeguimiento") idSeguimiento: Int
    ): Response<DetalleSeguimientoResponse>

}