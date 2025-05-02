package com.app.soffyapp.data.repository

import com.app.soffyapp.data.remote.api.NutricionalApi
import com.app.soffyapp.data.mapper.toDomain
import com.app.soffyapp.domain.model.NutricionalData
import com.app.soffyapp.domain.repository.NutricionalRepository
import javax.inject.Inject

class NutricionalRepositoryImpl @Inject constructor(
    private val api: NutricionalApi
) : NutricionalRepository {
    override suspend fun getNutricionalData(idExpediente: Int): NutricionalData {
        return api.getNutricionalData(idExpediente).data.toDomain()
    }
}
