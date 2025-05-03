package com.app.soffyapp.presentation.screens.pacientes.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.app.soffyapp.domain.model.Paciente
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Suppress("ktlint:standard:function-naming")
@Composable
fun PacienteCard(
    paciente: Paciente,
    onClick: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .padding(vertical = 8.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Icono o avatar
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Paciente avatar",
                modifier =
                    Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(12.dp)),
                tint = Color.Gray,
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Datos del paciente
            Column {
                Text(
                    text = paciente.nombreCompleto,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = "Fecha de nacimiento: ${paciente.fechaNacimiento.formatearFecha()}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                )
            }
        }

        Divider(modifier = Modifier.padding(top = 8.dp))
    }
}

fun String.formatearFecha(): String {
    // Espera formato ISO: "2024-06-05"
    return try {
        val parser = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val formatter = DateTimeFormatter.ofPattern("MMMM dd 'de' yyyy", Locale("es"))
        LocalDate.parse(this, parser).format(formatter)
    } catch (e: Exception) {
        this // por si viene mal, se deja igual
    }
}
