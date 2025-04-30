package com.app.soffyapp.data.repository

import com.app.soffyapp.data.mapper.toDomain
import com.app.soffyapp.data.remote.api.CentroEducativoApi
import com.app.soffyapp.domain.model.CentroEducativo
import com.app.soffyapp.domain.repository.CentroEducativoRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CentroEducativoRepositoryImpl
@Inject constructor(
    private val api: CentroEducativoApi
) : CentroEducativoRepository {

    override suspend fun getCentroEducativoList(idExpediente: Int): Pair<String, List<CentroEducativo>> {
        val response = api.getCentroEducativoList(idExpediente)
        return Pair(
            response.alumno,
            response.results.map { it.toDomain() }
        )
    }
}
