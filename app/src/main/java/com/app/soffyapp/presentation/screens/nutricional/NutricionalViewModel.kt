package com.app.soffyapp.presentation.screens.nutricional

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.soffyapp.domain.usecase.GetNutricionalDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutricionalViewModel @Inject constructor(
    private val getNutricionalDataUseCase: GetNutricionalDataUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(NutricionalUiState())
    val uiState: StateFlow<NutricionalUiState> = _uiState

    fun loadNutricionalData(expedienteId: String) {
        _uiState.value = NutricionalUiState(isLoading = true)

        viewModelScope.launch {
            try {
                val result = getNutricionalDataUseCase(expedienteId)
                _uiState.value = NutricionalUiState(data = result)
            } catch (e: Exception) {
                _uiState.value = NutricionalUiState(error = e.message ?: "Error inesperado")
            }
        }
    }
}
