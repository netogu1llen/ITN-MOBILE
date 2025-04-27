package com.app.soffyapp.presentation.screens.pacientes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.soffyapp.domain.model.Paciente
import com.app.soffyapp.domain.usecase.GetPacientesListUseCase
import com.app.soffyapp.presentation.common.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PacienteDetailViewModel @Inject constructor(
    private val getPacientesListUseCase: GetPacientesListUseCase
) : ViewModel() {

    private val _paciente = MutableStateFlow<Paciente?>(null)
    val paciente: StateFlow<Paciente?> = _paciente.asStateFlow()

    fun cargarPacientePorId(idExpediente: Int) {
        viewModelScope.launch {
            getPacientesListUseCase().collect { result ->
                when (result) {
                    is Result.Success -> {
                        val pacienteEncontrado = result.data.find { it.idExpediente == idExpediente }
                        _paciente.value = pacienteEncontrado
                    }
                    is Result.Error -> {
                        _paciente.value = null
                    }
                    is Result.Loading -> {
                        // opcional: podrías manejar loading
                    }
                }
            }
        }
    }
}
