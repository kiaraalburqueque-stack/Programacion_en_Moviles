package com.tecsup.tecsupfit.data

data class GymClass(
    val id: String,
    val name: String,
    val time: String,
    val room: String,
    val duration: String,
    val description: String,
    val availableSpots: Int,
    val totalSpots: Int,
    val category: String
)

data class Reservation(
    val id: String,
    val className: String,
    val schedule: String,
    val status: String
)

val sampleClasses = listOf(
    GymClass("1", "Yoga funcional", "7:00 am", "Sala 2", "50 min", "Ejercicios de movilidad, flexibilidad y respiración guiada.", 5, 15, "Hoy"),
    GymClass("2", "Cross Training", "6:00 pm", "Sala 1", "45 min", "Entrenamiento funcional de alta intensidad. Cupos limitados.", 8, 12, "Hoy"),
    GymClass("3", "Spinning", "7:30 pm", "Sala 3", "45 min", "Ciclismo bajo techo con música y diferentes intensidades.", 3, 20, "Hoy"),
    GymClass("4", "Pilates", "8:00 am", "Sala 2", "50 min", "Fortalecimiento de core, flexibilidad y control corporal.", 10, 15, "Esta semana")
)

val sampleReservations = mutableListOf(
    Reservation("101", "Cross Training", "Hoy, 6:00 pm · Sala 1", "Confirmada"),
    Reservation("102", "Yoga funcional", "Ayer, 7:00 am · Sala 2", "Completada")
)