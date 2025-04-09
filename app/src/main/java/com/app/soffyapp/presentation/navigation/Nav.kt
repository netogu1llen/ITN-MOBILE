package com.app.soffyapp.presentation.navigation

// presentation/navigation/Nav.kt
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        topBar = { TopAppBar() }, // O bottomBar = { BottomNavBar() }
        content = { padding ->
            NavHost(
                navController = navController,
                startDestination = Screens.Home.route,
                modifier = Modifier.padding(padding)
            ) {
                // Define tus pantallas aquí
                composable(Screens.Home.route) { HomeScreen(navController) }
                composable(Screens.Detail.route) { DetailScreen(navController) }
            }
        }
    )
}

// Define un sealed class para las pantallas
sealed class Screens(val route: String) {
    object Home : Screens("home")
    object Detail : Screens("detail")
}
