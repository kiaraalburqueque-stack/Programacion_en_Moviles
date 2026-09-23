package com.tecsup.clinicasalud.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tecsup.clinicasalud.data.MedicosData
import com.tecsup.clinicasalud.model.Cita
import com.tecsup.clinicasalud.ui.components.AppDrawer
import com.tecsup.clinicasalud.ui.components.AppTopBar
import com.tecsup.clinicasalud.ui.screens.AgendarCitaScreen
import com.tecsup.clinicasalud.ui.screens.ConfirmacionScreen
import com.tecsup.clinicasalud.ui.screens.HistorialScreen
import com.tecsup.clinicasalud.ui.screens.InicioScreen
import com.tecsup.clinicasalud.ui.screens.MisCitasScreen
import com.tecsup.clinicasalud.ui.screens.PerfilMedicoScreen
import kotlinx.coroutines.launch

// Rutas de navegación secuencial: Inicio -> Perfil -> Agendar -> Confirmación
// Rutas de navegación secundaria (drawer): Inicio, Mis citas, Historial médico
private const val RUTA_INICIO = "inicio"
private const val RUTA_PERFIL = "perfil/{medicoId}"
private const val RUTA_AGENDAR = "agendar/{medicoId}"
private const val RUTA_CONFIRMACION = "confirmacion/{medicoId}/{fecha}/{hora}"
private const val RUTA_MIS_CITAS = "mis_citas"
private const val RUTA_HISTORIAL = "historial"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClinicaSaludApp() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Estado de citas "elevado" (hoisted) a la raíz de la app, SIN ViewModel,
    // para que Inicio->Agendar pueda escribir y Mis citas/Historial puedan leer.
    val citas = remember { mutableStateListOf<Cita>() }

    val backStackEntry by navController.currentBackStackEntryAsState()
    val rutaActual = backStackEntry?.destination?.route

    val titulo = when {
        rutaActual == RUTA_INICIO -> "Clínica Salud+"
        rutaActual == RUTA_PERFIL -> "Perfil del médico"
        rutaActual == RUTA_AGENDAR -> "Agendar cita"
        rutaActual == RUTA_CONFIRMACION -> "Confirmación"
        rutaActual == RUTA_MIS_CITAS -> "Mis citas"
        rutaActual == RUTA_HISTORIAL -> "Historial médico"
        else -> "Clínica Salud+"
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            AppDrawer(
                onDestinoClick = { ruta ->
                    scope.launch { drawerState.close() }
                    navController.navigate(ruta) {
                        popUpTo(RUTA_INICIO)
                        launchSingleTop = true
                    }
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                AppTopBar(
                    titulo = titulo,
                    mostrarMenu = rutaActual == RUTA_INICIO,
                    onMenuClick = { scope.launch { drawerState.open() } },
                    onBackClick = { navController.navigateUp() }
                )
            }
        ) { paddingValues ->
            NavHost(
                navController = navController,
                startDestination = RUTA_INICIO,
                modifier = Modifier.padding(paddingValues)
            ) {
                composable(RUTA_INICIO) {
                    InicioScreen(
                        medicos = MedicosData.medicos,
                        especialidades = MedicosData.especialidades,
                        onMedicoClick = { medico ->
                            navController.navigate("perfil/${medico.id}")
                        }
                    )
                }

                composable(
                    route = RUTA_PERFIL,
                    arguments = listOf(navArgument("medicoId") { type = NavType.IntType })
                ) { entry ->
                    val medicoId = entry.arguments?.getInt("medicoId") ?: 0
                    val medico = MedicosData.medicos.find { it.id == medicoId }
                    if (medico != null) {
                        PerfilMedicoScreen(
                            medico = medico,
                            onAgendarClick = { navController.navigate("agendar/${medico.id}") }
                        )
                    }
                }

                composable(
                    route = RUTA_AGENDAR,
                    arguments = listOf(navArgument("medicoId") { type = NavType.IntType })
                ) { entry ->
                    val medicoId = entry.arguments?.getInt("medicoId") ?: 0
                    val medico = MedicosData.medicos.find { it.id == medicoId }
                    if (medico != null) {
                        AgendarCitaScreen(
                            medico = medico,
                            onConfirmar = { fecha, hora ->
                                citas.add(Cita(medico, fecha, hora, "Confirmada"))
                                navController.navigate("confirmacion/${medico.id}/$fecha/$hora")
                            }
                        )
                    }
                }

                composable(
                    route = RUTA_CONFIRMACION,
                    arguments = listOf(
                        navArgument("medicoId") { type = NavType.IntType },
                        navArgument("fecha") { type = NavType.StringType },
                        navArgument("hora") { type = NavType.StringType }
                    )
                ) { entry ->
                    val medicoId = entry.arguments?.getInt("medicoId") ?: 0
                    val fecha = entry.arguments?.getString("fecha") ?: ""
                    val hora = entry.arguments?.getString("hora") ?: ""
                    val medico = MedicosData.medicos.find { it.id == medicoId }
                    if (medico != null) {
                        ConfirmacionScreen(
                            medico = medico,
                            fecha = fecha,
                            hora = hora,
                            onVolverInicio = {
                                navController.navigate(RUTA_INICIO) {
                                    popUpTo(RUTA_INICIO) { inclusive = true }
                                }
                            }
                        )
                    }
                }

                composable(RUTA_MIS_CITAS) {
                    MisCitasScreen(citas = citas)
                }

                composable(RUTA_HISTORIAL) {
                    HistorialScreen(citas = citas)
                }
            }
        }
    }
}