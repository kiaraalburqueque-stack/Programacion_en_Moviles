package com.tecsup.clinicasalud.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tecsup.clinicasalud.model.Cita

@Composable
fun HistorialScreen(citas: List<Cita>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Historial médico", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))

        val completadas = citas.filter { it.estado == "Completada" }

        if (completadas.isEmpty()) {
            Text(
                "No tienes citas completadas todavía.",
                style = MaterialTheme.typography.bodyMedium
            )
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(completadas) { cita ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(cita.medico.nombre, style = MaterialTheme.typography.titleMedium)
                            Text(cita.medico.especialidad)
                            Text("${cita.fecha} · ${cita.hora}")
                        }
                    }
                }
            }
        }
    }
}