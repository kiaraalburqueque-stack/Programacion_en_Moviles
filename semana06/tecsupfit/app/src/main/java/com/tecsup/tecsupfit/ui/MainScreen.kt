package com.tecsup.tecsupfit.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument

sealed class BottomNavItem(val title: String, val route: String, val icon: ImageVector) {
    object Inicio : BottomNavItem("Inicio", "inicio", Icons.Default.Home)
    object Reservas : BottomNavItem("Reservas", "reservas", Icons.Default.DateRange)
    object Rutinas : BottomNavItem("Rutinas", "rutinas", Icons.Default.FitnessCenter)
    object Perfil : BottomNavItem("Perfil", "perfil", Icons.Default.Person)
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val items = listOf(
        BottomNavItem.Inicio,
        BottomNavItem.Reservas,
        BottomNavItem.Rutinas,
        BottomNavItem.Perfil
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val showBottomBar = currentRoute in items.map { it.route }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(containerColor = Color.White) {
                    items.forEach { item ->
                        val selected = currentRoute == item.route
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = { Icon(item.icon, contentDescription = item.title) },
                            label = { Text(item.title) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color(0xFF006C4C),
                                selectedTextColor = Color(0xFF006C4C),
                                indicatorColor = Color(0xFFE0F2E9)
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Inicio.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Inicio.route) {
                HomeScreen(onClassSelected = { classId -> navController.navigate("detalle/$classId") })
            }
            composable(BottomNavItem.Reservas.route) { ReservationsScreen() }
            composable(BottomNavItem.Rutinas.route) { RoutinesScreen() }
            composable(BottomNavItem.Perfil.route) { ProfileScreen() }
            composable(
                route = "detalle/{classId}",
                arguments = listOf(navArgument("classId") { type = NavType.StringType })
            ) { backStackEntry ->
                val classId = backStackEntry.arguments?.getString("classId")
                DetailScreen(
                    classId = classId,
                    onBackClick = { navController.popBackStack() },
                    onReserveClick = { id -> navController.navigate("confirmacion/$id") }
                )
            }
            composable(
                route = "confirmacion/{classId}",
                arguments = listOf(navArgument("classId") { type = NavType.StringType })
            ) { backStackEntry ->
                val classId = backStackEntry.arguments?.getString("classId")
                ConfirmationScreen(
                    classId = classId,
                    onViewReservations = {
                        navController.navigate(BottomNavItem.Reservas.route) {
                            popUpTo(BottomNavItem.Inicio.route)
                        }
                    }
                )
            }
        }
    }
}