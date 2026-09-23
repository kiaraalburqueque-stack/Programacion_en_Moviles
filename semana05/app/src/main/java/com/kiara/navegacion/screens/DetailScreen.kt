package com.kiara.navegacion.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(itemId: Int, navController: NavController) {
    val primaryBlue = Color(0xFF0052A5)
    val lightBlueContainer = Color(0xFFEDF4FC)
    val darkBlueText = Color(0xFF002B5B)

    val sampleNames = listOf(
        "Ana García", "Carlos Mendoza", "Kiara Quispe", "Luis Flores",
        "María Torres", "Diego Ramos", "Sofía Vargas", "Gabriel Castro",
        "Valeria Paredes", "Mateo Benítez", "Camila Navarro", "Jorge Rojas",
        "Lucía Morales", "Andrés Silva", "Elena Gutiérrez", "Renzo Castillo",
        "Daniela Medina", "Fernando Cruz", "Paula Herrera", "Ricardo Vega",
    )

    val sampleCareers = listOf(
        "Diseño y Desarrollo de Software",
        "Ingeniería de Sistemas",
        "Arquitectura de Entornos Digitales",
        "Redes y Comunicaciones",
        "Big Data e Inteligencia Artificial",
    )

    val studentName = sampleNames[(itemId - 1).coerceAtLeast(0) % sampleNames.size]
    val studentCareer = sampleCareers[(itemId - 1).coerceAtLeast(0) % sampleCareers.size]

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Expediente Académico",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    )
                },
                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .padding(start = 12.dp, end = 8.dp)
                            .clickable { navController.popBackStack() },
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "←",
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = primaryBlue,
                    titleContentColor = Color.White,
                ),
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Cabecera con avatar, nombre y carrera
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = lightBlueContainer,
                ),
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Box(
                        modifier = Modifier
                            .size(68.dp)
                            .clip(CircleShape)
                            .background(primaryBlue),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = "👤",
                            fontSize = 36.sp,
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = studentName,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = darkBlueText,
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = studentCareer,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = primaryBlue,
                    )
                }
            }

            // Datos del Expediente
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
            ) {
                Column(
                    modifier = Modifier.padding(18.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Text(
                        text = "Datos Principales",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = darkBlueText,
                    )

                    DetailFieldItem(
                        label = "ID Estudiante",
                        value = "TEC-2026-$itemId (Argumento Int: $itemId)",
                        primaryColor = primaryBlue,
                    )
                    DetailFieldItem(
                        label = "Correo Electrónico",
                        value = "alumno$itemId@tecsup.edu.pe",
                        primaryColor = primaryBlue,
                    )
                    DetailFieldItem(
                        label = "Facultad",
                        value = "Facultad de Tecnología e Informática Tecsup",
                        primaryColor = primaryBlue,
                    )
                }
            }

            // Sección Biografía
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White,
                ),
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Text(
                        text = "Biografía",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = darkBlueText,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Estudiante destacado en la especialidad de $studentCareer. Apasionado por la innovación tecnológica, el desarrollo de aplicaciones nativas para Android y la resolución de problemas mediante software de alto impacto.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color(0xFF424750),
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryBlue,
                    contentColor = Color.White,
                ),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "←", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Volver al Directorio", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}

@Composable
private fun DetailFieldItem(label: String, value: String, primaryColor: Color) {
    Column {
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = primaryColor,
        )
        Text(
            text = value,
            fontSize = 14.sp,
            color = Color(0xFF1D1B20),
        )
    }
}
