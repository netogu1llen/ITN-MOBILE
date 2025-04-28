package com.app.soffyapp.presentation.screens.pacientes

import android.util.Log
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.compose.runtime.collectAsState
import com.app.soffyapp.presentation.screens.pacientes.components.PacientesListContent

@Composable
fun PacientesListScreen(
    navController: NavController,
    viewModel: PacientesViewModel = hiltViewModel()
) {
    var searchQuery by remember { mutableStateOf("") }
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.pacientesList) {
        uiState.pacientesList.forEachIndexed { index, paciente ->
            Log.d("PacienteData", "Paciente $index -> id: ${paciente.idExpediente}, nombre: '${paciente.nombre}', apellidoPaterno: '${paciente.apellidoPaterno}'")
        }
    }

    Log.d("PacientesListScreen", "isLoading: ${uiState.isLoading}, pacientesList: ${uiState.pacientesList.size}")

    PacientesListContent(
        pacientesList = uiState.pacientesList,
        isLoading = uiState.isLoading,
        error = uiState.error,
        searchQuery = searchQuery,
        onSearchQueryChange = { searchQuery = it },
        onPacienteClick = { pacienteId ->
            navController.navigate("detallePaciente/$pacienteId")
        }
    )
}

