package com.tecsup.clinicasalud.data

import com.tecsup.clinicasalud.model.Medico

object MedicosData {
    val medicos = listOf(
        Medico(1, "Dra. Ana Torres", "Cardiología", 4.8f),
        Medico(2, "Dr. Luis Ramírez", "Pediatría", 4.6f),
        Medico(3, "Dra. Carla Mendoza", "Dermatología", 4.9f),
        Medico(4, "Dr. Jorge Salazar", "Traumatología", 4.5f),
        Medico(5, "Dra. Paola Rivas", "Ginecología", 4.7f)
    )

    val especialidades = listOf(
        "Todas", "Cardiología", "Pediatría", "Dermatología", "Traumatología", "Ginecología"
    )
}