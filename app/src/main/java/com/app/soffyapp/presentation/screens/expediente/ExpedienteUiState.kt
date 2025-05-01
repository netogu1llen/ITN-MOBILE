package com.app.soffyapp.presentation.screens.expediente

import com.app.soffyapp.domain.model.Expediente

data class ExpedienteUiState(
    val expediente: Expediente? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)