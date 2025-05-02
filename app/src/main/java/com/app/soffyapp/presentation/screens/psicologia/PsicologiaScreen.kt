package com.app.soffyapp.presentation.screens.psicologia

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.navigation.NavController
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.soffyapp.presentation.screens.psicologia.components.PsicologiaListContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PsicologiaScreen(
    pacienteId: Int,
    navController: NavController,
    viewModel: PsicologiaViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Carga los seguimientos del paciente
    LaunchedEffect(pacienteId) {
        viewModel.loadPsicologia(pacienteId)

        viewModel.navigationEvents.collect { event ->
            when (event) {
                is PsicologiaViewModel.NavigationEvent.NavigateToDetail -> {
                    navController.navigate("psicologia_detalle/${event.idSeguimiento}")
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Seguimientos Psicológicos") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Regresar")
                    }
                }
            )
        }
    ) { padding ->
        PsicologiaListContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            uiState = uiState,
            onSeguimientoClick = { viewModel.onSeguimientoSelected(it.idSeguimiento) }
        )
    }
}