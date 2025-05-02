package com.app.soffyapp.presentation.screens.pacientes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.app.soffyapp.domain.model.Paciente

@Suppress("ktlint:standard:function-naming")
@Composable
fun PacientesList(
    pacientesList: List<Paciente>,
    onPacienteClick: (Int) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(
            items = pacientesList,
            key = { it.idExpediente },
        ) { paciente ->
            PacienteCard(
                paciente = paciente,
                onClick = { onPacienteClick(paciente.idExpediente) },
            )
        }
    }
}
