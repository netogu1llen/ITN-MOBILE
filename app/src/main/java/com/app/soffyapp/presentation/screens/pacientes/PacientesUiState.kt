package com.app.soffyapp.presentation.screens.pacientes

import com.app.soffyapp.domain.model.Paciente

data class PacientesUiState(
    val pacientesList: List<Paciente> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)
