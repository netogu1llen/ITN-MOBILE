package com.app.soffyapp.presentation.screens.expediente

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpedienteScreen(
    pacienteId: Int,
    viewModel: ExpedienteViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Cargar el expediente al entrar a la pantalla
    LaunchedEffect(pacienteId) {
        viewModel.loadExpediente(pacienteId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Expediente") }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                uiState.error != null -> {
                    Text(
                        text = "Error: ${uiState.error}",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                uiState.expediente != null -> {
                    val expediente = uiState.expediente!!
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(text = "Nombre: ${expediente.nombreCompleto}")
                        Text(text = "Número de expediente: ${expediente.numExpediente}")
                        Text(text = "Fecha de nacimiento: ${expediente.fechaNacimiento}")
                        Text(text = "Contacto: ${expediente.contacto}")
                        Text(text = "Estado: ${expediente.estado}")
                        Text(text = "Ciudad: ${expediente.ciudad}")
                        Text(text = "Calle: ${expediente.calle}")
                        Text(text = "Código Postal: ${expediente.cp}")
                        Text(text = "Localidad: ${expediente.localidad}")
                        Text(text = "Número de casa: ${expediente.numCasa}")
                        Text(text = "Enfermedades: ${expediente.enfermedades}")
                        Text(text = "Medicamentos: ${expediente.medicamentos}")
                        Text(text = "Estudio Socioeconómico: ${expediente.estudioSocioeconomico}")
                        Text(text = "Grado: ${expediente.grado}")
                        Text(text = "Nivel Escolar: ${expediente.nvEscolar}")
                        Text(text = "Tipo de Sangre: ${expediente.sangre}")
                    }
                }
            }
        }
    }
}
