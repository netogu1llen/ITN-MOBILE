package com.app.soffyapp.presentation.screens.pacientes.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.soffyapp.presentation.screens.pacientes.PacientesDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PacienteDetailScreen(
    pacienteId: Int,
    navController: NavController,
    viewModel: PacientesDetailViewModel = hiltViewModel() // ✅ Clase correcta
) {
    val paciente by viewModel.paciente.collectAsState()

    LaunchedEffect(pacienteId) {
        viewModel.cargarPacientePorId(pacienteId.toString()) // ✅ pacienteId a String
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Detalle del Paciente",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        paciente?.let {
            SectionTitle(title = "Datos Personales")

            DisabledTextField(label = "Nombre", value = it.nombre)
            DisabledTextField(label = "Apellido Paterno", value = it.apellidoPaterno)
            DisabledTextField(label = "Apellido Materno", value = it.apellidoMaterno)
            DisabledTextField(label = "Fecha de Nacimiento", value = it.fechaNacimiento)
            DisabledTextField(label = "Teléfono", value = it.telefono)

            SectionTitle(title = "Dirección de Vivienda")

            DisabledTextField(label = "Estado", value = it.estado)
            DisabledTextField(label = "Ciudad", value = it.ciudad)
            DisabledTextField(label = "Calle", value = it.calle)
            DisabledTextField(label = "Código Postal", value = it.codigoPostal)
            DisabledTextField(label = "Localidad", value = it.localidad)
            DisabledTextField(label = "Número de Casa", value = it.numeroCasa)

            SectionTitle(title = "Datos Médicos")

            DisabledTextField(label = "Enfermedades", value = it.enfermedades)
            DisabledTextField(label = "Medicamentos", value = it.medicamentos)
            DisabledTextField(label = "Tipo de Sangre", value = it.tipoSangre)

            SectionTitle(title = "Datos Académicos")

            DisabledTextField(label = "Estudio Socioeconómico", value = it.estudioSocioeconomico)
            DisabledTextField(label = "Grado", value = it.grado)
            DisabledTextField(label = "Nivel Escolar", value = it.nivelEscolar)

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Regresar")
            }
        }
    }
}

@Composable
fun DisabledTextField(label: String, value: String) {
    TextField(
        value = value,
        onValueChange = {},
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        enabled = false
    )
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
    )
}
