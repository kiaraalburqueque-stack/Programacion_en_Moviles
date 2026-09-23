package com.kiara.navegacion.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.kiara.navegacion.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navController: NavController) {
    val items = (1..20).toList()
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Directorio de Alumnos",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = primaryBlue,
                    titleContentColor = Color.White,
                ),
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier.padding(padding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            items(items) { id ->
                val studentName = sampleNames[(id - 1) % sampleNames.size]
                val studentCareer = sampleCareers[(id - 1) % sampleCareers.size]

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(Screen.Detail.createRoute(id))
                        },
                    elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = lightBlueContainer,
                    ),
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(46.dp)
                                .clip(CircleShape)
                                .background(primaryBlue),
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = "👤",
                                fontSize = 22.sp,
                            )
                        }
                        Spacer(modifier = Modifier.width(14.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = studentName,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = darkBlueText,
                            )
                            Text(
                                text = studentCareer,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFF424750),
                            )
                            Text(
                                text = "Código: TEC-2026-$id",
                                fontSize = 11.sp,
                                color = primaryBlue,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                        Text(
                            text = "➔",
                            color = primaryBlue,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
            }
        }
    }
}
