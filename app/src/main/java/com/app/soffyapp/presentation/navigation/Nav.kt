package com.app.soffyapp.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.soffyapp.presentation.screens.home.components.HomeScreen
import com.app.soffyapp.presentation.screens.pacientes.PacientesScreen
import com.app.soffyapp.presentation.screens.expediente.ExpedienteScreen
import com.app.soffyapp.presentation.screens.nutricional.NutricionalScreen
import com.app.soffyapp.presentation.screens.centroeducativo.CentroEducativoScreen
import com.app.soffyapp.presentation.screens.login.components.LoginScreen
import com.app.soffyapp.presentation.screens.psicologia.PsicologiaDetailScreen
import com.app.soffyapp.presentation.screens.psicologia.PsicologiaScreen

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
            if (currentDestination?.route != Screens.Login.route) {
                // Barra de navegación inferior solo si no estamos en la pantalla de Login
                NavigationBar(
                    containerColor = Color(0xFFFEA02F) // Aquí aplicamos el color #FEA02F
                ) {
                    // Itera sobre todas las pantallas definidas, excluyendo Expediente y CentroEducativo
                    Screens.values.filter { screen ->
                        screen != Screens.Expediente && screen != Screens.CentroEducativo
                    }.forEach { screen ->
                        NavigationBarItem(
                            icon = {
                                // Verifica si el icono es null, si lo es, usa un ícono predeterminado
                                val iconToUse = screen.icon ?: Icons.Default.Home
                                Icon(
                                    imageVector = iconToUse,
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
        }
    ) { innerPadding ->
        // Host de navegación que contiene las pantallas
        NavHost(
            navController = navController,
            startDestination = Screens.Login.route, // Pantalla inicial
            modifier = Modifier.padding(innerPadding)
        ) {
            // Definición de pantallas/composables
            composable(Screens.Login.route) { LoginScreen(navController) }
            composable(Screens.Home.route) { HomeScreen(navController) }
            composable(Screens.Detail.route) { DetailScreen(navController) }
            composable(Screens.Pacientes.route) {
                PacientesScreen(
                    onPacienteClick = { pacienteId ->
                        // Navega a detalle si lo necesitas
                        navController.navigate("${Screens.Expediente.route}/$pacienteId")
                    }
                )
            }
            composable(
                route = "expediente/{id}",
            ) { backStackEntry ->
                val pacienteId = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: return@composable
                ExpedienteScreen(
                    pacienteId = pacienteId,
                    navController = navController
                )
            }
            composable(
                route = "centroeducativo/{id}"
            ) { backStackEntry ->
                val pacienteId = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: return@composable
                CentroEducativoScreen(
                    pacienteId = pacienteId,
                    navController = navController
                )
            }
            composable(
                route = "nutricion/{id}"
            ) { backStackEntry ->
                val pacienteId = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: return@composable
                NutricionalScreen(
                    pacienteId = pacienteId,
                    navController = navController
                )
            }

            composable(
                route = "psicologia/{id}"
            ) { backStackEntry ->
                val pacienteId = backStackEntry.arguments?.getString("id")?.toIntOrNull() ?: return@composable
                PsicologiaScreen(
                    pacienteId = pacienteId,
                    navController = navController
                )
            }

            composable(
                route = "psicologia_detalle/{idSeguimiento}",
                arguments = listOf(navArgument("idSeguimiento") { type = NavType.IntType })
            ) { backStackEntry ->
                val idSeguimiento = backStackEntry.arguments?.getInt("idSeguimiento") ?: return@composable
                PsicologiaDetailScreen(
                    idSeguimiento = idSeguimiento,
                    navController = navController
                )
            }
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
    val icon: ImageVector? = null // Aquí se garantiza que cada pantalla tenga un ícono asignado
) {
    // Pantalla de login
    object Login : Screens("login", "Iniciar sesión")

    // Pantalla de inicio
    object Home : Screens("home", "Inicio", Icons.Default.Home)

    // Pantalla de pacientes
    object Pacientes : Screens("pacientes", "Pacientes", Icons.Default.Info)

    // Pantalla de expedientes sin ícono en la barra de navegación
    object Expediente : Screens("expediente", "Expediente")

    // Pantalla de Centro Educativo sin ícono en la barra de navegación
    object CentroEducativo : Screens("centroeducativo", "Centro Educativo")

    // Pantalla Nutricional
    object Nutricional : Screens("nutricion", "Nutrición")

    companion object {
        // Lista de todas las pantallas disponibles
        val values = listOf(Home, Pacientes, Expediente, CentroEducativo, Nutricional)
    }
}
