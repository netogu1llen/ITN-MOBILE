package com.app.soffyapp.domain.repository

import com.app.soffyapp.data.remote.dto.PsicologiaListDto
import com.app.soffyapp.domain.model.Psicologia

interface PsicologiaRepository {
    suspend fun getPsicologiaList(idExpediente: Int): PsicologiaListDto
    suspend fun getDetalleSeguimiento(idSeguimiento: Int): Psicologia
}