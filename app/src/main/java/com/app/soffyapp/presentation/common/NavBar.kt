package com.app.soffyapp.presentation.common

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.app.soffyapp.presentation.navigation.Screens
/**
 * Componente reutilizable de barra de navegación inferior
 *
 * Este componente muestra una barra de navegación con íconos y etiquetas para cada pantalla,
 * permitiendo la navegación entre las diferentes secciones de la aplicación.
 *
 * @param navController Controlador de navegación que gestiona el stack de pantallas
 */
@Composable
fun NavBar(navController: NavController) {
    // Lista de pantallas/screens disponibles en la navegación
    val items = listOf(
        Screens.Home,  // Pantalla principal
        Screens.Detail // Pantalla de detalle
    )

    // Componente Material Design para barra de navegación inferior
    NavigationBar {
        // Obtiene la ruta actual para resaltar el ítem seleccionado
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        // Itera sobre cada pantalla para crear los ítems de navegación
        items.forEach { screen ->
            NavigationBarItem(
                // Ícono del ítem (usando ícono por defecto como ejemplo)
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = screen.route // Descripción para accesibilidad
                    )
                },
                // Etiqueta/texto del ítem
                label = { Text(text = screen.route) },
                // Estado de selección (resaltado visual)
                selected = currentRoute == screen.route,
                // Comportamiento al hacer clic
                onClick = {
                    navController.navigate(screen.route) {
                        // Configuración de navegación optimizada:
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true // Conserva el estado al hacer pop
                        }
                        launchSingleTop = true // Evita múltiples instancias
                        restoreState = true // Restaura estado si existe
                    }
                }
            )
        }
    }
}