package com.app.soffyapp.presentation.screens.psicologia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.soffyapp.domain.model.Psicologia
import com.app.soffyapp.domain.repository.PsicologiaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PsicologiaDetailViewModel @Inject constructor(
    private val repository: PsicologiaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PsicologiaDetailUiState())
    val uiState: StateFlow<PsicologiaDetailUiState> = _uiState.asStateFlow()

    fun loadDetalleSeguimiento(idSeguimiento: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val detalle = repository.getDetalleSeguimiento(idSeguimiento)
                _uiState.update {
                    it.copy(
                        seguimiento = detalle,
                        isLoading = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        error = e.message ?: "Error al cargar detalle",
                        isLoading = false
                    )
                }
            }
        }
    }
}

data class PsicologiaDetailUiState(
    val seguimiento: Psicologia? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)