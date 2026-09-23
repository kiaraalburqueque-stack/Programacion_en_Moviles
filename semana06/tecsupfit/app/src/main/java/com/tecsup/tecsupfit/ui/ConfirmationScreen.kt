package com.tecsup.tecsupfit.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
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
fun ConfirmationScreen(classId: String?, onViewReservations: () -> Unit) {
    val gymClass = sampleClasses.find { it.id == classId } ?: sampleClasses[1]

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.size(80.dp).background(Color(0xFFE0F2E9), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Check, contentDescription = null, tint = Color(0xFF006C4C), modifier = Modifier.size(40.dp))
        }

        Spacer(modifier = Modifier.height(24.dp))
        Text("¡Cupo reservado!", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(gymClass.name, fontSize = 16.sp, fontWeight = FontWeight.Medium)
        Text("Hoy, ${gymClass.time} · ${gymClass.room}", color = Color.Gray, fontSize = 14.sp)

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onViewReservations,
            modifier = Modifier.fillMaxWidth().height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE5E7EB)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Ver mis reservas", color = Color.Black)
        }
    }
}