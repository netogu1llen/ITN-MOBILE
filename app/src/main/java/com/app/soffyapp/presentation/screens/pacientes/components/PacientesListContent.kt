package com.app.soffyapp.presentation.screens.pacientes.components

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.soffyapp.domain.model.Paciente
import com.app.soffyapp.presentation.common.SearchBar

@Suppress("ktlint:standard:function-naming")
@Composable
fun PacientesListContent(
    pacientesList: List<Paciente>,
    isLoading: Boolean,
    error: String?,
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onPacienteClick: (Int) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                )
            }
            error != null -> {
                Text(
                    text = error,
                    modifier = Modifier.align(Alignment.Center),
                    color = MaterialTheme.colorScheme.error,
                )
            }
            else -> {
                Column {
                    SearchBar(
                        query = searchQuery,
                        onQueryChange = onSearchQueryChange,
                    )
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(
                            items = pacientesList.withIndex().toList(),
                            key = { (index, _) -> index }
                        ) { (_, paciente) ->
                            val nombreMostrar = if (paciente.nombre.isNotBlank() || paciente.apellidoPaterno.isNotBlank()) {
                                "${paciente.nombre} ${paciente.apellidoPaterno}".trim()
                            } else {
                                "Paciente sin nombre"
                            }

                            Log.d("PacienteData", "Mostrando Paciente id: ${paciente.idExpediente}, nombreMostrar: '$nombreMostrar'")

                            Text(
                                text = nombreMostrar,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onPacienteClick(paciente.idExpediente)
                                    }
                                    .padding(vertical = 8.dp)
                            )

                        }
                    }
                }
            }
        }
    }
}
