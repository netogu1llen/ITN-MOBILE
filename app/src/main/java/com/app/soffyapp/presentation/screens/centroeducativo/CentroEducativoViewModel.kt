package com.app.soffyapp.presentation.screens.centroeducativo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.soffyapp.domain.usecase.GetCentroEducativoListUseCase
import com.app.soffyapp.presentation.common.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CentroEducativoViewModel @Inject constructor(
    private val getCentroEducativoListUseCase: GetCentroEducativoListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CentroEducativoUiState())
    val uiState: StateFlow<CentroEducativoUiState> = _uiState.asStateFlow()

    fun loadCentroEducativo(idExpediente: Int) {
        viewModelScope.launch {
            getCentroEducativoListUseCase(idExpediente).collect { result ->
                _uiState.update {
                    when (result) {
                        is Result.Loading -> it.copy(isLoading = true)
                        is Result.Success -> it.copy(
                            centroList = result.data.second,
                            alumnoNombre = result.data.first,
                            isLoading = false,
                            error = null
                        )
                        is Result.Error -> it.copy(
                            error = result.exception.message,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }
}
