package com.app.soffyapp.domain.repository

import com.app.soffyapp.domain.model.CentroEducativo

interface CentroEducativoRepository {
    suspend fun getCentroEducativoList(idExpediente: String): Pair<String, List<CentroEducativo>>
}
