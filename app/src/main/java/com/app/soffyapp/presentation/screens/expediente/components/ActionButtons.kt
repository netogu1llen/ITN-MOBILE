package com.app.soffyapp.presentation.screens.expediente.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ActionButtons(
    onPsicologiaClick: () -> Unit,
    //onNutricionClick: () -> Unit,
    onCentroEducativoClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(
            onClick = onPsicologiaClick,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Psicología")
        }
        /*Button(
            onClick = onNutricionClick,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Nutrición")
        }
         */
        Button(
            onClick = onCentroEducativoClick,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text("Centro Educativo")
        }
    }
}
