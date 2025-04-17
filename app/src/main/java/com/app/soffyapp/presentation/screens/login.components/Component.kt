package com.app.soffyapp.presentation.screens.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.app.soffyapp.R

/**
 * Pantalla de inicio de sesión de la aplicación
 *
 * Esta pantalla muestra:
 * - Un fondo de imagen completa
 * - El logo de la empresa centrado en la parte superior
 * - (Espacio reservado para campos de formulario y botones)
 *
 * Diseñada siguiendo las pautas de Material Design para pantallas de autenticación.
 */
@Composable
fun Component(navController: NavController) {
    // Contenedor principal que ocupa toda la pantalla
    Box(
        modifier = Modifier
            .fillMaxSize() // Ocupa todo el espacio disponible
    ) {
        // Fondo de pantalla con imagen
        Image(
            painter = painterResource(id = R.drawable.fondo_login), // Recurso de imagen de fondo
            contentDescription = null, // Decorativo, no necesita descripción
            contentScale = ContentScale.Crop, // Escala y recorta la imagen para llenar el espacio
            modifier = Modifier.fillMaxSize() // La imagen ocupa todo el Box
        )

        // Columna para organizar los elementos verticalmente
        Column(
            modifier = Modifier
                .fillMaxSize() // Ocupa todo el espacio disponible
                .padding(top = 80.dp), // Margen superior de 80dp
            horizontalAlignment = Alignment.CenterHorizontally // Centra los elementos horizontalmente
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth() // La imagen ocupa el ancho completo del contenedor
                    .heightIn(min = 100.dp, max = 200.dp) // Restringir la altura entre 100dp y 200dp
            ) {
                Image(
                    painter = painterResource(id = R.drawable.soffy), // Recurso de imagen del logo
                    contentDescription = "Logo de la empresa",
                    modifier = Modifier.fillMaxSize(), // Ocupa todo el espacio del Box
                    contentScale = ContentScale.Crop // Recorta la imagen si es necesario para llenar el espacio
                )
            }

            // ESPACIO RESERVADO PARA FUTUROS ELEMENTOS:
            // - Campos de texto para usuario/contraseña
            // - Botón de inicio de sesión
            // - Opciones de "Olvidé mi contraseña" y "Registrarse"
        }
    }
}