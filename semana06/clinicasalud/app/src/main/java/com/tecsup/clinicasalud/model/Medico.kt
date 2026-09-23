package com.tecsup.clinicasalud.model

data class Medico(
    val id: Int,
    val nombre: String,
    val especialidad: String,
    val calificacion: Float
)

data class Cita(
    val medico: Medico,
    val fecha: String,
    val hora: String,
    val estado: String // "Confirmada" o "Completada"
)