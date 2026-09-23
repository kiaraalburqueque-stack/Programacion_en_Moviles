package com.kiara.navegacion.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.kiara.navegacion.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(navController: NavController) {
    val items = (1..20).toList()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Lista de ítems") }) }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(items) { id ->
                ListItem(
                    headlineContent = { Text("Ítem $id") },
                    modifier = Modifier.clickable {
                        navController.navigate(Screen.Detail.createRoute(id))
                    }
                )
            }
        }
    }
}