package com.app.soffyapp.presentation.screens.centroeducativo

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.navigation.NavController
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.soffyapp.presentation.screens.centroeducativo.components.CentroEducativoListContent

/**
 * Pantalla Centro Educativo
 *
 * Muestra el historial académico (boletas) de un paciente.
 *
 * Implementa:
 * - TopAppBar con botón de regreso
 * - Carga automática de datos mediante LaunchedEffect
 * - Renderizado condicional con estado del ViewModel
 *
 * @OptIn ExperimentalMaterial3Api - Se usa TopAppBar de Material 3
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CentroEducativoScreen(
    pacienteId: String,
    navController: NavController,
    viewModel: CentroEducativoViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    // Carga las boletas del paciente
    LaunchedEffect(pacienteId) {
        viewModel.loadCentroEducativo(pacienteId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Centro Educativo") },
                navigationIcon = {
                    IconButton(onClick = {
                        // Navegar hacia atrás, en este caso, ir a la pantalla anterior
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Regresar")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            CentroEducativoListContent(
                alumnoNombre = uiState.alumnoNombre,
                boletas = uiState.centroList,
                isLoading = uiState.isLoading,
                error = uiState.error
            )
        }
    }
}
