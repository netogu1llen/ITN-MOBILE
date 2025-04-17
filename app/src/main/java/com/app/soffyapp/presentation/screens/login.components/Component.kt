package com.app.soffyapp.presentation.screens.login.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    Box(
        modifier = Modifier
            .fillMaxSize()
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
                .padding(top = 55.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.circulo),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(290.dp) // Ajusta tamaño del círculo
            )
        }

        // Logo sobre el círculo
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.soffy),
                contentDescription = "Logo de la empresa",
                modifier = Modifier
                    .width(300.dp)
                    .height(200.dp)
            )

            // ESPACIO RESERVADO PARA FUTUROS ELEMENTOS:
            // - Campos de texto para usuario/contraseña
            // - Botón de inicio de sesión
            // - Opciones de "Olvidé mi contraseña" y "Registrarse"

        }
    }
}
