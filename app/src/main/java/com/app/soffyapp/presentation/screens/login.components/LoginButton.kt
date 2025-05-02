package com.app.soffyapp.presentation.screens.login.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.app.soffyapp.R

/**
 * Componente de botón para autenticación con Google
 * Mantiene el estilo visual original usando la imagen de Google
 *
 * @param viewModel ViewModel que maneja la lógica de autenticación
 */
@Composable
fun GoogleLoginButton(viewModel: LoginViewModel) {
    // Configura el lanzador para el resultado de la actividad de login
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // Pasa el resultado directamente al ViewModel
        viewModel.handleGoogleSignInResult(result.data)
    }

    // Resetear contador de reintentos al hacer clic en el botón
    // para asegurar que siempre tengamos intentos disponibles cuando
    // el usuario inicia una nueva sesión de autenticación

    // Botón de Google (usa la imagen original del diseño)
    Image(
        painter = painterResource(id = R.drawable.google),
        contentDescription = "Iniciar sesión con Google",
        modifier = Modifier
            .width(250.dp)
            .height(60.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable {
                // Resetear contador de reintentos
                viewModel.resetRetryCount()

                // Obtiene el intent para iniciar sesión desde el ViewModel
                val signInIntent = viewModel.getGoogleSignInIntent()
                launcher.launch(signInIntent)
            }
    )
}