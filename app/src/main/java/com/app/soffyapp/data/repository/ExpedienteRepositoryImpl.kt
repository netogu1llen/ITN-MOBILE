package com.app.soffyapp.data.repository


import com.app.soffyapp.data.mapper.toDomain
import com.app.soffyapp.data.remote.api.ExpedienteApi
import com.app.soffyapp.domain.model.Expediente
import com.app.soffyapp.domain.repository.ExpedienteRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExpedienteRepositoryImpl @Inject constructor(
    private val api: ExpedienteApi,
) : ExpedienteRepository {
    override suspend fun getExpedienteById(id: Int): Expediente {
        return api.getExpediente(id).expediente.toDomain()
    }
}