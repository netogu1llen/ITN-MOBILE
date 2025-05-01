package com.app.soffyapp.presentation.screens.psicologia

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.app.soffyapp.domain.model.ObjetivoPsicologico

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PsicologiaDetailScreen(
    idSeguimiento: Int,
    navController: NavController,
    viewModel: PsicologiaDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(idSeguimiento) {
        viewModel.loadDetalleSeguimiento(idSeguimiento)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Detalle de Seguimiento") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Regresar")
                    }
                }
            )
        }
    ) { padding ->
        PsicologiaDetailContent(
            modifier = Modifier.padding(padding),
            uiState = uiState
        )
    }
}

@Composable
fun PsicologiaDetailContent(
    modifier: Modifier = Modifier,
    uiState: PsicologiaDetailUiState
) {
    Box(modifier = modifier.fillMaxSize()) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            uiState.error != null -> {
                Text(
                    text = "Error: ${uiState.error}",
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.error
                )
            }
            uiState.seguimiento != null -> {
                val seguimiento = uiState.seguimiento
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Información del seguimiento
                    item {
                        Text(
                            text = "Fecha: ${seguimiento.fecha}",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    item { DetailSection("Objetivo", seguimiento.sesionObjetivo) }
                    item { DetailSection("Justificación", seguimiento.sesionJustificacion) }
                    item { DetailSection("Análisis", seguimiento.analisisPsicologico) }
                    item { DetailSection("Recomendaciones", seguimiento.recomendaciones) }
                    item { DetailSection("Bitácora", seguimiento.sesionBitacora) }

                    // Información de los objetivos
                    item {
                        Text(
                            "Objetivos",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    }

                    if (seguimiento.objetivos.isEmpty()) {
                        item {
                            Text(
                                "No hay objetivos registrados",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                            )
                        }
                    } else {
                        items(seguimiento.objetivos) { objetivo ->
                            ObjetivoSimpleView(objetivo = objetivo)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ObjetivoSimpleView(objetivo: ObjetivoPsicologico) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        // Titulo: Objetivos
        Text(
            text = objetivo.objetivo,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 9.dp)
        )


        // Detalles del objetivo
        if (objetivo.actividad.isNotBlank()) {
            Text(
                text = "Actividad",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }

        if (objetivo.actividad.isNotBlank()) {
            Text(
                text = "${objetivo.actividad}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 9.dp)
            )
        }

        if (objetivo.tiempo.isNotBlank()) {
            Text(
                text = "Tiempo",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }

        if (objetivo.tiempo.isNotBlank()) {
            Text(
                text = "${objetivo.tiempo}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 9.dp)
            )
        }

        if (objetivo.metodologia.isNotBlank()) {
            Text(
                text = "Metodología",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }

        if (objetivo.metodologia.isNotBlank()) {
            Text(
                text = "${objetivo.metodologia}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 9.dp)
            )
        }

        if (objetivo.observaciones.isNotBlank()) {
            Text(
                text = "Observaciones",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }

        if (objetivo.observaciones.isNotBlank()) {
            Text(
                text = "${objetivo.observaciones}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 9.dp)
            )
        }
    }
}



@Composable
fun DetailSection(title: String, content: String) {
    if (content.isBlank()) return

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = content,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}