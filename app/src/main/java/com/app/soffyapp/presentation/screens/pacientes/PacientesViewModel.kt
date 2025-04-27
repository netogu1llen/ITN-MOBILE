package com.app.soffyapp.presentation.screens.pacientes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.soffyapp.domain.usecase.GetPacientesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PacientesViewModel @Inject constructor(
    private val getPacientesListUseCase: GetPacientesListUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow(PacientesUiState())
    val uiState: StateFlow<PacientesUiState> = _uiState.asStateFlow()

    init {
        loadPacientesList()
    }

    private fun loadPacientesList() {
        viewModelScope.launch {
            val result = getPacientesListUseCase()

            val pacientesFiltrados = result.filter { paciente ->
                paciente.idExpediente != 0
            }

            _uiState.update {
                it.copy(pacientesList = pacientesFiltrados)
            }
        }
    }
}
