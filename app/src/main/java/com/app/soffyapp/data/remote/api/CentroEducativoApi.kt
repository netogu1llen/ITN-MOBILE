package com.app.soffyapp.data.remote.api

import com.app.soffyapp.data.remote.dto.CentroEducativoListDto
import retrofit2.http.GET
import retrofit2.http.Path

// Interfaz para consumir la API relacionada al módulo Centro Educativo.
// Utiliza Retrofit para hacer peticiones HTTP.
interface CentroEducativoApi {

    // Llama al endpoint GET /boletas/{idExpediente} para obtener las boletas de un paciente.
    // La respuesta será un CentroEducativoListDto que contiene una lista de boletas.
    @GET("boletas/{idExpediente}")
    suspend fun getCentroEducativoList(
        @Path("idExpediente") idExpediente: Int // Inyecta el ID del expediente en la URL.
    ): CentroEducativoListDto
}
