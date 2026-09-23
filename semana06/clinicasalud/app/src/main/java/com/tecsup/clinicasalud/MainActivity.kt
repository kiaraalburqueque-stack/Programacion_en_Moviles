package com.tecsup.clinicasalud

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.tecsup.clinicasalud.navigation.ClinicaSaludApp
import com.tecsup.clinicasalud.ui.theme.ClinicasaludTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClinicasaludTheme {
                ClinicaSaludApp()
            }
        }
    }
}