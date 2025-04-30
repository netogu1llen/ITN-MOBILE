package com.app.soffyapp.presentation.screens.centroeducativo.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.soffyapp.domain.model.CentroEducativo

@Composable
fun CentroEducativoCardList(
    alumnoNombre: String,
    centroList: List<CentroEducativo>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Alumno: $alumnoNombre",
            style = MaterialTheme.typography.headlineSmall
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(centroList) { centro ->
                CentroEducativoCard(centro)
            }
        }
    }
}
