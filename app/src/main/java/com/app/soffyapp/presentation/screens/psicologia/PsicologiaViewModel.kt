package com.app.soffyapp.presentation.screens.psicologia

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.soffyapp.domain.repository.PsicologiaRepository
import com.app.soffyapp.domain.usecase.GetPsicologiaListUseCase
import com.app.soffyapp.presentation.common.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PsicologiaViewModel @Inject constructor(
    private val getPsicologiaListUseCase: GetPsicologiaListUseCase,
    private val repository: PsicologiaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(PsicologiaUiState())
    val uiState: StateFlow<PsicologiaUiState> = _uiState.asStateFlow()

    private val _navigationEvents = MutableSharedFlow<NavigationEvent>()
    val navigationEvents: SharedFlow<NavigationEvent> = _navigationEvents.asSharedFlow()

    fun loadPsicologia(idExpediente: String) {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            getPsicologiaListUseCase(idExpediente).collect { result ->
                _uiState.update {
                    when (result) {
                        is Result.Loading -> it.copy(isLoading = true)
                        is Result.Success -> it.copy(
                            seguimientos = result.data.second,
                            alumnoNombre = result.data.first,
                            isLoading = false,
                            error = null
                        )
                        is Result.Error -> it.copy(
                            error = result.exception.message ?: "Error desconocido",
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

    fun onSeguimientoSelected(idSeguimiento: Int) {
        viewModelScope.launch {
            _navigationEvents.emit(NavigationEvent.NavigateToDetail(idSeguimiento))
        }
    }

    sealed class NavigationEvent {
        data class NavigateToDetail(val idSeguimiento: Int) : NavigationEvent()
    }
}