package com.app.soffyapp.domain.repository

import com.app.soffyapp.domain.model.Expediente

interface ExpedienteRepository {
    suspend fun getExpedienteById(id: String): Expediente
}
