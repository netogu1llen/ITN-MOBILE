package com.app.soffyapp.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun MyAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        // Personalizar colores, tipografía, etc. aquí
        content = content
    )
}