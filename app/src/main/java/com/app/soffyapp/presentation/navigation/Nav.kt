package com.app.soffyapp.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.app.soffyapp.R
import com.app.soffyapp.presentation.screens.detail.components.Component as DetailScreen
import com.app.soffyapp.presentation.screens.home.components.Component as HomeScreen

/**
 * Componente principal de navegación de la aplicación
 *
 * Implementa un patrón de navegación con:
 * - Barra inferior de navegación (Bottom Navigation)
 * - Gestión de estado de navegación
 * - Soporte para múltiples pantallas
 *
 * @OptIn ExperimentalMaterial3Api - Indica uso de APIs experimentales de Material 3
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigation() {
    // Controlador de navegación que maneja el back stack
    val navController = rememberNavController()

    // Estado observables para la ruta actual
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    // Scaffold es el layout principal que incluye la estructura de la app
    Scaffold(
        bottomBar = {
            // Barra de navegación inferior
            NavigationBar(
                containerColor = Color(0xFFFEA02F) // Aquí aplicamos el color #FEA02F
            ) {
                // Itera sobre todas las pantallas definidas
                Screens.values.forEach { screen ->
                    NavigationBarItem(
                        icon = {
                            // Icono del item de navegación
                            Icon(
                                imageVector = screen.icon,
                                contentDescription = screen.route
                            )
                        },
                        label = { Text(screen.title) }, // Texto del item
                        selected = currentDestination?.hierarchy?.any {
                            it.route == screen.route
                        } == true, // Estado seleccionado
                        onClick = {
                            // Navegación con configuración optimizada:
                            navController.navigate(screen.route) {
                                // 1. Limpia back stack hasta el inicio
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true // Conserva estado
                                }
                                // 2. Evita múltiples instancias
                                launchSingleTop = true
                                // 3. Restaura estado previo si existe
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        // Host de navegación que contiene las pantallas
        NavHost(
            navController = navController,
            startDestination = Screens.Home.route, // Pantalla inicial
            modifier = Modifier.padding(innerPadding)
        ) {
            // Definición de pantallas/composables
            composable(Screens.Home.route) { HomeScreen(navController) }
            composable(Screens.Detail.route) { DetailScreen(navController) }
        }
    }
}

/**
 * Clase sellada que define las pantallas de la aplicación
 *
 * @property route Ruta única para la navegación
 * @property title Título mostrado en la UI
 * @property iconRes Recurso del icono para la barra de navegación
 */
sealed class Screens(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    // Pantalla de inicio
    object Home : Screens("home", "Inicio", Icons.Default.Home)

    // Pantalla de detalle
    object Detail : Screens("detail", "Detalle", Icons.Default.Info)

    companion object {
        // Lista de todas las pantallas disponibles
        val values = listOf(Home, Detail)
    }
}