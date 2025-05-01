package com.app.soffyapp.presentation.screens.expediente

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.soffyapp.domain.usecase.GetExpedienteUseCase
import com.app.soffyapp.presentation.common.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpedienteViewModel @Inject constructor(
    private val getExpedienteUseCase: GetExpedienteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExpedienteUiState())
    val uiState: StateFlow<ExpedienteUiState> = _uiState.asStateFlow()

    fun loadExpediente(pacienteId: Int) {  // ← pacienteId ya es Int, no hay que convertir
        viewModelScope.launch {
            getExpedienteUseCase(pacienteId).collect { result -> // ← Directo
                when (result) {
                    is Result.Loading -> {
                        _uiState.update { it.copy(isLoading = true, error = null) }
                    }
                    is Result.Success -> {
                        _uiState.update {
                            it.copy(
                                expediente = result.data,
                                isLoading = false,
                                error = null
                            )
                        }
                    }
                    is Result.Error -> {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                error = result.exception.message ?: "Error desconocido"
                            )
                        }
                    }
                }
            }
        }
    }
}
