package com.app.soffyapp.presentation.screens.psicologia

import com.app.soffyapp.domain.model.ObjetivoPsicologico
import com.app.soffyapp.domain.model.Psicologia

data class PsicologiaUiState(
    val centroList: List<Psicologia> = emptyList(),
    val seguimientos: List<Psicologia> = emptyList(),
    val objetivos: List<ObjetivoPsicologico> = emptyList(),
    val alumnoNombre: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
)