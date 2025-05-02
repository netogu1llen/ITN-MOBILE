package com.app.soffyapp.presentation.screens.pacientes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
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
    val filteredList =
        remember(pacientesList, searchQuery) {
            val query = searchQuery.trim().lowercase()

            pacientesList.filter { paciente ->
                val nombre = paciente.nombreCompleto.lowercase()
                val fechaFormateada = paciente.fechaNacimiento.formatearFecha().lowercase()

                nombre.contains(query) || fechaFormateada.contains(query)
            }
        }

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
                            items = filteredList,
                            key = { it.idExpediente },
                        ) { paciente ->
                            PacienteCard(
                                paciente = paciente,
                                onClick = { onPacienteClick(paciente.idExpediente) },
                            )
                        }
                    }
                }
            }
        }
    }
}
