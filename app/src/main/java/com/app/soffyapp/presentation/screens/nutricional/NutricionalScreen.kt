package com.app.soffyapp.presentation.screens.nutricional

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.app.soffyapp.presentation.screens.expediente.components.InfoLine
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NutricionalScreen(
    pacienteId: String,
    navController: NavController,
    viewModel: NutricionalViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(pacienteId) {
        viewModel.loadNutricionalData(pacienteId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nutricional") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Regresar")
                    }
                },
            )
        },
    ) { padding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {

            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                uiState.error != null -> {
                    Text(
                        text = "Error: ${uiState.error}",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                uiState.data != null -> {
                    val data = uiState.data!!

                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text("Última sesión: ${data.nutricional?.numSesion ?: "No disponible"}", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.height(12.dp))

                        Text("Diagnóstico: ${data.diagnostico ?: "No registrado"}")
                        Text("Objetivo Nutricional: ${data.objetivo ?: "No registrado"}")

                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Antropometría", style = MaterialTheme.typography.titleSmall)
                        data.antropometria?.let {
                            Text("Peso: ${it.peso}")
                            Text("Talla: ${it.talla}")
                            Text("Edad: ${it.edad}")
                            Text("Cintura: ${it.circunferenciaCintura}")
                            Text("Cadera: ${it.circunferenciaCadera}")
                        } ?: Text("Sin datos")

                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Actividad Diaria", style = MaterialTheme.typography.titleSmall)
                        data.actividad?.let {
                            Text("Ejercicio: ${it.ejercicioFisico}")
                            Text("Frecuencia: ${it.frecuencia}")
                        } ?: Text("Sin datos")

                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Trastornos", style = MaterialTheme.typography.titleSmall)
                        data.transtornos?.let {
                            Text("Reflujo: ${it.reflujo}")
                            Text("Vómito: ${it.vomito}")
                            Text("Disfagia: ${it.disfagia}")
                            Text("Diarrea: ${it.diarrea}")
                            Text("Flatulencias: ${it.flatulencias}")
                            Text("Estreñimiento: ${it.estrenimiento}")
                            Text("Distensión: ${it.distencion}")
                            Text("Colitis: ${it.colitis}")
                            Text("Pirosis: ${it.pirosis}")
                            Text("Gastritis: ${it.gastritis}")
                            Text("Otro: ${it.otro}")
                        } ?: Text("Sin datos")

                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Indicadores clínicos", style = MaterialTheme.typography.titleSmall)
                        data.clinicos?.let {
                            Text("Cabello: ${it.cabello}")
                            Text("Dientes: ${it.dientes}")
                            Text("Piel: ${it.piel}")
                            Text("Uñas: ${it.unias}")
                            Text("Conjunto: ${it.conjunto}")
                            Text("Boca: ${it.boca}")
                            Text("Edema: ${it.edema}")
                        } ?: Text("Sin datos")

                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Indicadores Bioquímicos", style = MaterialTheme.typography.titleSmall)
                        data.bioquim?.let {
                            Text("Parámetro: ${it.parametro}")
                            Text("Valor Referencia: ${it.valorReferencia}")
                        } ?: Text("Sin datos")

                        Spacer(modifier = Modifier.height(16.dp))
                        Text("Manejo Nutricional", style = MaterialTheme.typography.titleSmall)
                        data.manejo?.let {
                            Text("Energía: ${it.energia}")
                            Text("H. Carbono: ${it.hidratosDeCarbono}")
                            Text("Lípidos: ${it.lipidos}")
                            Text("Proteínas: ${it.proteinas}")
                            Text("Fibra: ${it.fibra}")
                            Text("Agua: ${it.agua}")
                        } ?: Text("Sin datos")
                    }
                }
            }
        }
    }
}
