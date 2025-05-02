package com.app.soffyapp.presentation.screens.centroeducativo

import com.app.soffyapp.domain.model.CentroEducativo

data class CentroEducativoUiState(
    val centroList: List<CentroEducativo> = emptyList(),
    val alumnoNombre: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
)
