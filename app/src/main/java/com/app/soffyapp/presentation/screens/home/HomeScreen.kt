package com.app.soffyapp.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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



        }
    }
