package com.tecsup.clinicasalud.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// Se declara envolviendo el Scaffold (no como parámetro de Scaffold) porque
// el menú lateral debe poder deslizarse POR ENCIMA de toda la pantalla,
// incluida la propia topBar.
@Composable
fun AppDrawer(onDestinoClick: (String) -> Unit) {
    ModalDrawerSheet {
        Spacer(Modifier.height(12.dp))
        Text(
            text = "Clínica Salud+",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = 20.dp)
        )
        Spacer(Modifier.height(8.dp))
        HorizontalDivider()
        NavigationDrawerItem(
            label = { Text("Inicio") },
            selected = false,
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            onClick = { onDestinoClick("inicio") },
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
        )
        NavigationDrawerItem(
            label = { Text("Mis citas") },
            selected = false,
            icon = { Icon(Icons.Default.DateRange, contentDescription = null) },
            onClick = { onDestinoClick("mis_citas") },
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
        )
        NavigationDrawerItem(
            label = { Text("Historial médico") },
            selected = false,
            icon = { Icon(Icons.Default.List, contentDescription = null) },
            onClick = { onDestinoClick("historial") },
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
        )
    }
}