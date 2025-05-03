package com.app.soffyapp.presentation.screens.psicologia.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.soffyapp.domain.model.Psicologia
import com.app.soffyapp.presentation.screens.psicologia.PsicologiaUiState

@Composable
fun PsicologiaListContent(
    modifier: Modifier = Modifier,
    uiState: PsicologiaUiState,
    onSeguimientoClick: (Psicologia) -> Unit
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
            uiState.seguimientos.isEmpty() -> {
                Text(
                    text = "No hay seguimientos registrados",
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "Alumno: ${uiState.alumnoNombre}",
                        style = MaterialTheme.typography.titleLarge
                    )

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(uiState.seguimientos) { seguimiento ->
                            PsicologiaCard(
                                boleta = seguimiento,
                                onClick = { onSeguimientoClick(seguimiento) }
                            )
                        }
                    }
                }
            }
        }
    }
}