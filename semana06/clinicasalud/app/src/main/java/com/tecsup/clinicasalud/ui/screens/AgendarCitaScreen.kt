package com.tecsup.clinicasalud.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tecsup.clinicasalud.model.Medico

// Selección de fecha y hora: ambas de selección ÚNICA (mínimo 3 opciones cada una).
// Se usa RadioButton porque es, conceptualmente, un grupo donde solo puede
// haber UNA opción marcada a la vez — igual que pide la rúbrica.
@Composable
fun AgendarCitaScreen(
    medico: Medico,
    onConfirmar: (fecha: String, hora: String) -> Unit
) {
    val fechasDisponibles = listOf("Lun 28 Sep", "Mié 30 Sep", "Vie 02 Oct")
    val horasDisponibles = listOf("09:00 am", "11:30 am", "03:00 pm")

    var fechaSeleccionada by remember { mutableStateOf(fechasDisponibles.first()) }
    var horaSeleccionada by remember { mutableStateOf(horasDisponibles.first()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Cita con ${medico.nombre}", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(20.dp))

        Text("Selecciona fecha", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(4.dp))
        fechasDisponibles.forEach { fecha ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { fechaSeleccionada = fecha }
                    .padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = fechaSeleccionada == fecha,
                    onClick = { fechaSeleccionada = fecha }
                )
                Text(fecha)
            }
        }

        Spacer(Modifier.height(20.dp))
        Text("Selecciona hora", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(4.dp))
        horasDisponibles.forEach { hora ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { horaSeleccionada = hora }
                    .padding(vertical = 4.dp)
            ) {
                RadioButton(
                    selected = horaSeleccionada == hora,
                    onClick = { horaSeleccionada = hora }
                )
                Text(hora)
            }
        }

        Spacer(Modifier.height(28.dp))
        Button(
            onClick = { onConfirmar(fechaSeleccionada, horaSeleccionada) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Confirmar cita")
        }
    }
}