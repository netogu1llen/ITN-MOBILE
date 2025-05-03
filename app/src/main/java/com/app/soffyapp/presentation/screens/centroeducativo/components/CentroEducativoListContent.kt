package com.app.soffyapp.presentation.screens.centroeducativo.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.soffyapp.domain.model.CentroEducativo

@Composable
fun CentroEducativoListContent(
    alumnoNombre: String,
    boletas: List<CentroEducativo>,
    isLoading: Boolean,
    error: String?,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            error != null -> {
                Text(
                    text = "Error: $error",
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.error
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
                        text = "Alumno: $alumnoNombre",
                        style = MaterialTheme.typography.titleLarge
                    )

                    boletas.forEach { boleta ->
                        CentroEducativoCard(boleta = boleta)
                    }
                }
            }
        }
    }
}
