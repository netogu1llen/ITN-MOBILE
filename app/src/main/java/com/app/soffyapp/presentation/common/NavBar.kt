package com.app.soffyapp.presentation.common

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

// presentation/common/components/NavBar.kt
@Composable
fun NavBar(navController: NavController) {
    val items = listOf(
        Screens.Home,
        Screens.Detail
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(Icons.Default.Home, contentDescription = null) },
                label = { Text(screen.route) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}