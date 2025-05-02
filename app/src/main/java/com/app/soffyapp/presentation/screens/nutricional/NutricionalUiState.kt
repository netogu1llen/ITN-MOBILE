package com.app.soffyapp.presentation.screens.nutricional

import com.app.soffyapp.domain.model.NutricionalData

data class NutricionalUiState(
    val isLoading: Boolean = false,
    val data: NutricionalData? = null,
    val error: String? = null
)
