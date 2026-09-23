package com.tecsup.tecsupfit.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tecsup.tecsupfit.data.sampleReservations

@Composable
fun ReservationsScreen() {
    var selectedReservationId by remember { mutableStateOf<String?>(null) }
    var reservationsList by remember { mutableStateOf(sampleReservations) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Mis reservas", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(reservationsList) { res ->
                val isConfirmed = res.status == "Confirmada"
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF3F4F6))
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier.width(4.dp).height(50.dp)
                                .background(if (isConfirmed) Color(0xFF006C4C) else Color.Gray)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(res.className, fontWeight = FontWeight.Bold)
                            Text(res.schedule, fontSize = 12.sp, color = Color.Gray)
                            Spacer(modifier = Modifier.height(6.dp))
                            Surface(
                                color = if (isConfirmed) Color(0xFFE0F2E9) else Color(0xFFE5E7EB),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = res.status,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    fontSize = 11.sp,
                                    color = if (isConfirmed) Color(0xFF006C4C) else Color.DarkGray
                                )
                            }
                        }
                        if (isConfirmed) {
                            TextButton(onClick = { selectedReservationId = res.id }) {
                                Text("Cancelar", color = Color.Red, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }
    }

    if (selectedReservationId != null) {
        AlertDialog(
            onDismissRequest = { selectedReservationId = null },
            title = { Text("Cancelar reserva") },
            text = { Text("¿Estás seguro de que deseas cancelar esta reserva?") },
            confirmButton = {
                TextButton(onClick = {
                    reservationsList = reservationsList.filter { it.id != selectedReservationId }.toMutableList()
                    selectedReservationId = null
                }) {
                    Text("Sí, cancelar", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { selectedReservationId = null }) {
                    Text("Volver")
                }
            }
        )
    }
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Mi perfil", fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Start))
        Spacer(modifier = Modifier.height(24.dp))

        Box(
            modifier = Modifier.size(80.dp).background(Color(0xFFE0F2E9), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text("KA", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF006C4C))
        }

        Spacer(modifier = Modifier.height(12.dp))
        Text("Kiara Alburqueque", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Text("Plan Premium", color = Color.Gray, fontSize = 14.sp)

        Spacer(modifier = Modifier.height(24.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Card(modifier = Modifier.width(130.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFF3F4F6))) {
                Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("14", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    Text("Clases", fontSize = 12.sp, color = Color.Gray)
                }
            }
            Card(modifier = Modifier.width(130.dp), colors = CardDefaults.cardColors(containerColor = Color(0xFFF3F4F6))) {
                Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("3", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                    Text("Rachas", fontSize = 12.sp, color = Color.Gray)
                }
            }
        }
    }
}

@Composable
fun RoutinesScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Sección de Rutinas")
    }
}