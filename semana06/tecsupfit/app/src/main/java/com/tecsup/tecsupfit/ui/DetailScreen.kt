package com.tecsup.tecsupfit.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tecsup.tecsupfit.data.sampleClasses

@Composable
fun DetailScreen(classId: String?, onBackClick: () -> Unit, onReserveClick: (String) -> Unit) {
    val gymClass = sampleClasses.find { it.id == classId } ?: sampleClasses[1]

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBackClick) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
            }
            Text("Detalle de clase", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier.fillMaxWidth().height(160.dp).background(Color(0xFFE0F2E9), RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.FitnessCenter, contentDescription = null, modifier = Modifier.size(70.dp), tint = Color(0xFF006C4C))
        }

        Spacer(modifier = Modifier.height(20.dp))
        Text(gymClass.name, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text("${gymClass.time} · ${gymClass.room} · ${gymClass.duration}", color = Color.Gray, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(gymClass.description, fontSize = 14.sp, color = Color.DarkGray)
        Spacer(modifier = Modifier.height(16.dp))
        Text("${gymClass.availableSpots} de ${gymClass.totalSpots} cupos disponibles", color = Color(0xFF006C4C), fontWeight = FontWeight.SemiBold)

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { onReserveClick(gymClass.id) },
            modifier = Modifier.fillMaxWidth().height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF006C4C)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Reservar cupo", color = Color.White, fontSize = 16.sp)
        }
    }
}