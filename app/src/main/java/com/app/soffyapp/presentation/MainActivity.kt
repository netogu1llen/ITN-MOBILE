package com.app.soffyapp.presentation

import com.app.soffyapp.presentation.navigation.AppNavigation
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

/**
 * Actividad principal de la aplicación Android.
 *
 * Esta actividad:
 * 1. Es el punto de entrada principal de la aplicación
 * 2. Configura el contenido usando Jetpack Compose
 * 3. Aplica el tema personalizado de la aplicación
 * 4. Inicializa el sistema de navegación principal
 *
 * Hereda de ComponentActivity para soportar Jetpack Compose.
 */
class MainActivity : ComponentActivity() {
    /**
     * Método llamado cuando la actividad es creada.
     *
     * @param savedInstanceState Estado previo de la actividad (si existe)
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) // Llama a la implementación base

        // Configura el contenido usando Jetpack Compose
        setContent {
            // Aplica el tema personalizado de la aplicación
            MyAppTheme {
                // Inicializa el componente de navegación principal
                AppNavigation()
            }
        }
    }
}