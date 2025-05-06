package com.app.soffyapp.domain.usecase

import com.app.soffyapp.domain.model.NutricionalData
import com.app.soffyapp.domain.repository.NutricionalRepository
import javax.inject.Inject

class GetNutricionalDataUseCase @Inject constructor(
    private val repository: NutricionalRepository
) {
    suspend operator fun invoke(idExpediente: String): NutricionalData {
        return repository.getNutricionalData(idExpediente)
    }
}
