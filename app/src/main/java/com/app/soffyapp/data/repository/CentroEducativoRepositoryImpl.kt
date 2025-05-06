package com.app.soffyapp.data.repository

import com.app.soffyapp.data.mapper.toDomain
import com.app.soffyapp.data.remote.api.CentroEducativoApi
import com.app.soffyapp.domain.model.CentroEducativo
import com.app.soffyapp.domain.repository.CentroEducativoRepository
import javax.inject.Inject
import javax.inject.Singleton

// Implementación del repositorio de Centro Educativo.
// Se marca como Singleton para que haya una única instancia en la app.
// Se inyecta la API correspondiente mediante constructor.

@Singleton
class CentroEducativoRepositoryImpl
@Inject constructor(
    private val api: CentroEducativoApi
) : CentroEducativoRepository {

    // Obtiene los datos del Centro Educativo desde la API,
    // mapea la lista de resultados al modelo de dominio
    // y retorna también el nombre del alumno como primer elemento del par.
    override suspend fun getCentroEducativoList(idExpediente: String): Pair<String, List<CentroEducativo>> {
        val response = api.getCentroEducativoList(idExpediente)
        return Pair(
            response.alumno,
            response.results.map { it.toDomain() }
        )
    }
}
