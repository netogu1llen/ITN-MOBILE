package com.app.soffyapp.domain.repository

import com.app.soffyapp.domain.model.NutricionalData

interface NutricionalRepository {
    suspend fun getNutricionalData(idExpediente: String): NutricionalData
}
