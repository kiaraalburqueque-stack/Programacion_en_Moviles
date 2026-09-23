package com.tecsup.clinicasalud.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tecsup.clinicasalud.model.Cita

// LazyColumn con las citas agendadas; el estado (Confirmada/Completada) se
// diferencia visualmente con el color del AssistChip.
@Composable
fun MisCitasScreen(
    citas: List<Cita>,
    onCancelarCita: (Cita) -> Unit
) {
    // Estado local: qué cita se está por cancelar (para mostrar el AlertDialog)
    var citaACancelar by remember { mutableStateOf<Cita?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Mis citas", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(16.dp))

        if (citas.isEmpty()) {
            Text(
                "Aún no tienes citas agendadas. Ve a Inicio y agenda una.",
                style = MaterialTheme.typography.bodyMedium
            )
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(citas) { cita ->
                    Card(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(cita.medico.nombre, style = MaterialTheme.typography.titleMedium)
                                    Text(cita.medico.especialidad)
                                    Text("${cita.fecha} · ${cita.hora}")
                                }
                                AssistChip(
                                    onClick = {},
                                    label = { Text(cita.estado) },
                                    colors = AssistChipDefaults.assistChipColors(
                                        containerColor = if (cita.estado == "Confirmada")
                                            Color(0xFFDFF5E1) else Color(0xFFE3E3E3)
                                    )
                                )
                            }

                            // Solo se puede cancelar una cita que aún está Confirmada
                            if (cita.estado == "Confirmada") {
                                Spacer(Modifier.height(8.dp))
                                OutlinedButton(onClick = { citaACancelar = cita }) {
                                    Text("Cancelar cita")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // AlertDialog de confirmación antes de cancelar
    citaACancelar?.let { cita ->
        AlertDialog(
            onDismissRequest = { citaACancelar = null },
            title = { Text("Cancelar cita") },
            text = {
                Text("¿Seguro que deseas cancelar la cita con ${cita.medico.nombre} el ${cita.fecha} a las ${cita.hora}?")
            },
            confirmButton = {
                TextButton(onClick = {
                    onCancelarCita(cita)
                    citaACancelar = null
                }) {
                    Text("Sí, cancelar")
                }
            },
            dismissButton = {
                TextButton(onClick = { citaACancelar = null }) {
                    Text("No")
                }
            }
        )
    }
}