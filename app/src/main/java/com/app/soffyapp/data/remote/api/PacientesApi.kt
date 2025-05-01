package com.app.soffyapp.data.remote.api

import com.app.soffyapp.data.remote.dto.PacienteDto
import com.app.soffyapp.data.remote.dto.PacientesListDto
import retrofit2.http.GET
import retrofit2.http.Path

interface PacientesApi {
    @GET("pacientes")
    suspend fun getPacientesList(): PacientesListDto

    @GET("pacientes/{id}")
    suspend fun getPaciente(
        @Path("id") id: String,
    ): PacienteDto
}