package com.app.soffyapp.presentation.screens.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.soffyapp.R
import com.app.soffyapp.presentation.screens.login.LoginUiState
import com.app.soffyapp.presentation.screens.login.LoginViewModel

/**
 * Pantalla de inicio de sesión integrada con ViewModel y Hilt
 *
 * @param navController Controlador de navegación para navegar entre pantallas
 * @param viewModel ViewModel que maneja la lógica de autenticación
 */
@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    // Obtener estado actual de la UI desde el ViewModel
    val uiState by viewModel.uiState.collectAsState()

    // Configurar launcher para el resultado de Google Sign-In
    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Pasar resultado al ViewModel para procesamiento
        viewModel.handleGoogleSignInResult(result.data)
    }

    // Efecto para navegar a la pantalla home después de login exitoso
    LaunchedEffect(uiState) {
        if (uiState is LoginUiState.Success) {
            navController.navigate("home") {
                // Borrar historial de navegación para evitar volver al login con botón atrás
                popUpTo("login") { inclusive = true }
            }
        }
    }

    // UI de pantalla de login
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Imagen de fondo principal
        Image(
            painter = painterResource(id = R.drawable.fondo_login),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Imagen decorativa "circulo" centrada detrás del logo
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 75.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.circulo),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(290.dp)
            )
        }

        // Logo sobre el círculo y contenido adicional
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.soffy),
                contentDescription = "Logo de la empresa",
                modifier = Modifier
                    .width(300.dp)
                    .height(200.dp)
            )

            Spacer(modifier = Modifier.height(200.dp))

            // Mostrar contenido según el estado actual
            when (uiState) {
                is LoginUiState.Loading -> {
                    // Indicador de carga
                    CircularProgressIndicator(
                        modifier = Modifier.size(50.dp)
                    )
                }
                is LoginUiState.Error -> {
                    // Mensaje de error
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(horizontal = 32.dp)
                    ) {
                        Text(
                            text = (uiState as LoginUiState.Error).message,
                            textAlign = TextAlign.Center,
                            fontSize = 16.sp,
                            color = androidx.compose.ui.graphics.Color.Red
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Botón para reintentar
                        Image(
                            painter = painterResource(id = R.drawable.google),
                            contentDescription = "Iniciar sesión con Google",
                            modifier = Modifier
                                .width(250.dp)
                                .height(60.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .clickable {
                                    val signInIntent = viewModel.getGoogleSignInIntent()
                                    googleSignInLauncher.launch(signInIntent)
                                }
                        )
                    }
                }
                else -> {
                    // Botón de Google Sign-In (estado idle o default)
                    Image(
                        painter = painterResource(id = R.drawable.google),
                        contentDescription = "Iniciar sesión con Google",
                        modifier = Modifier
                            .width(250.dp)
                            .height(60.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .clickable {
                                val signInIntent = viewModel.getGoogleSignInIntent()
                                googleSignInLauncher.launch(signInIntent)
                            }
                    )
                }
            }
        }
    }
}