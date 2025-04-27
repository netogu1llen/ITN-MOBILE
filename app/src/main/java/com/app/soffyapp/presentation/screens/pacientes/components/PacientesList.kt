package com.app.soffyapp.presentation.screens.pacientes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.app.soffyapp.domain.model.Paciente
import androidx.compose.ui.Modifier
import androidx.compose.foundation.lazy.grid.rememberLazyGridState


@Suppress("ktlint:standard:function-naming")
@Composable
fun PacientesList(
    pacientesList: List<Paciente>,
    onPacienteClick: (Int) -> Unit,
) {
    val filteredPacientes = pacientesList
        .filter { it.idExpediente != 0 }
        .distinctBy { it.idExpediente }

    val gridState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        state = gridState,
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = filteredPacientes,
            key = { paciente -> paciente.idExpediente }
        ) { paciente ->
            PacienteCard(
                paciente = paciente,
                onClick = { onPacienteClick(paciente.idExpediente) }
            )
        }
    }
}
