package com.app.soffyapp.presentation.screens.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.app.soffyapp.R

@Composable
fun HomeScreen(navController: NavHostController
    ) {

        // UI de pantalla de home
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Imagen de fondo principal
            Image(
                painter = painterResource(id = R.drawable.fondo_home),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Texto de bienvenida
            Text(
                text = "¡Transformando vidas, una sesión a la vez!",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 450.dp, bottom = 16.dp),
                color = Color.Black,
                fontSize = 18.sp,
            )



        }
    }
