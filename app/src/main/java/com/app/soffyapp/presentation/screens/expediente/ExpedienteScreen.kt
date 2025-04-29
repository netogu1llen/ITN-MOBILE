package com.app.soffyapp.presentation.screens.expediente

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.app.soffyapp.presentation.screens.expediente.components.InfoLine
import com.app.soffyapp.presentation.screens.expediente.components.ActionButtons

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpedienteScreen(
    pacienteId: Int,
    navController: NavController,
    viewModel: ExpedienteViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(pacienteId) {
        viewModel.loadExpediente(pacienteId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Expediente") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate("pacientes") {
                            popUpTo("expediente") { inclusive = true }
                        }
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Regresar")
                    }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier
            .padding(padding)
            .fillMaxSize()
        ) {
            when {
                uiState.isLoading -> CircularProgressIndicator(Modifier.align(Alignment.Center))
                uiState.error != null -> Text(
                    text = "Error: ${uiState.error}",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.Center)
                )
                uiState.expediente != null -> {
                    val expediente = uiState.expediente!!
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // 🔵 Botones de navegación arriba
                        ActionButtons(
                            onPsicologiaClick = { navController.navigate("psicologia/$pacienteId") },
                            onNutricionClick = { navController.navigate("nutricion/$pacienteId") },
                            onBoletaClick = { navController.navigate("boleta/$pacienteId") }
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        Text("Información del paciente", style = MaterialTheme.typography.titleMedium)
                        InfoLine("Nombre", expediente.nombreCompleto)
                        InfoLine("Número de expediente", expediente.numExpediente)
                        InfoLine("Fecha de nacimiento", expediente.fechaNacimiento)
                        InfoLine("Contacto", expediente.contacto)
                        InfoLine("Estado", expediente.estado)
                        InfoLine("Ciudad", expediente.ciudad)
                        InfoLine("Calle", expediente.calle)
                        InfoLine("Código Postal", expediente.cp)
                        InfoLine("Localidad", expediente.localidad)
                        InfoLine("Número de casa", expediente.numCasa)
                        InfoLine("Enfermedades", expediente.enfermedades)
                        InfoLine("Medicamentos", expediente.medicamentos)
                        InfoLine("Estudio Socioeconómico", expediente.estudioSocioeconomico)
                        InfoLine("Grado", expediente.grado)
                        InfoLine("Nivel Escolar", expediente.nvEscolar)
                        InfoLine("Tipo de Sangre", expediente.sangre)
                    }
                }
            }
        }
    }
}
