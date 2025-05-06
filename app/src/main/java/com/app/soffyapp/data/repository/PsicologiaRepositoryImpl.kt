package com.app.soffyapp.data.repository

import com.app.soffyapp.data.mapper.toDomain
import com.app.soffyapp.data.remote.api.PsicologiaApi
import com.app.soffyapp.data.remote.dto.PsicologiaListDto
import com.app.soffyapp.domain.model.Psicologia
import com.app.soffyapp.domain.repository.PsicologiaRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PsicologiaRepositoryImpl @Inject constructor(
    private val api: PsicologiaApi
) : PsicologiaRepository {

    override suspend fun getPsicologiaList(idExpediente: String): PsicologiaListDto {
        return api.getPsicologiaList(idExpediente)
    }

    override suspend fun getDetalleSeguimiento(idSeguimiento: Int): Psicologia {
        val response = api.getDetalleSeguimiento(idSeguimiento)
        if (!response.isSuccessful || response.body() == null) {
            throw Exception("Error al obtener detalle")
        }
        return response.body()!!.detalle.toDomain()
    }
}