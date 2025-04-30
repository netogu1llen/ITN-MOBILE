package com.app.soffyapp.presentation.screens.pacientes

import com.app.soffyapp.domain.model.Paciente
import retrofit2.http.Query

data class PacientesUiState(
    val pacientesList: List<Paciente> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchQuery: String = ""
)
