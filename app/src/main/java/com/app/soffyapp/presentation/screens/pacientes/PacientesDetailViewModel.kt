package com.app.soffyapp.presentation.screens.pacientes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.soffyapp.domain.model.Paciente
import com.app.soffyapp.domain.usecase.GetPacientesListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PacientesDetailViewModel @Inject constructor(
    private val getPacientesListUseCase: GetPacientesListUseCase
) : ViewModel() {

    private val _paciente = MutableStateFlow<Paciente?>(null)
    val paciente: StateFlow<Paciente?> = _paciente.asStateFlow()

    fun cargarPacientePorId(id: String) {
        viewModelScope.launch {
            try {
                val pacientes = getPacientesListUseCase()
                val pacienteEncontrado = pacientes.find { it.idExpediente.toString() == id }
                _paciente.value = pacienteEncontrado
            } catch (e: Exception) {
                // Aquí podrías manejar el error si quieres
                _paciente.value = null
            }
        }
    }
}
