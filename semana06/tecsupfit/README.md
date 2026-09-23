# TECSUP Fit - Sistema de Reserva de Clases

**Estudiante:** Kiara Alburqueque  
**Curso:** Programacion en Moviles  
**Tecnologias:** Kotlin, Jetpack Compose, Navigation Component, Material Design 3  

---

## Requisitos Implementados en el Codigo

Este proyecto cumple con los requerimientos tecnicos de la Opcion B sin el uso de ViewModel:

### 1. Arquitectura y Manejo de Estado
- **Sin ViewModel:** El estado de la interfaz se gestiona directamente en las funciones Composable mediante `remember` y `mutableStateOf`.
- **Estructura modular:** Codigo organizado en los paquetes `com.tecsup.tecsupfit.data` y `com.tecsup.tecsupfit.ui`.

### 2. Componentes UI de Jetpack Compose Utilizados
- **Scaffold + NavigationBar (BottomBar):** Navegacion principal entre las pantallas de Inicio, Mis Reservas y Perfil con resaltado dinamico de la pestana activa (`MainScreen.kt`).
- **LazyRow:** Desplazamiento horizontal para las categorias de clases (Cardio, Fuerza, Yoga, Spin) en `HomeScreen.kt`.
- **LazyColumn:** Listas verticales dinamicas para mostrar las clases disponibles (`HomeScreen.kt`) y las reservas agendadas (`ReservationAndProfileScreens.kt`).
- **Card / ElevatedCard:** Tarjetas de diseno para presentar los detalles de cada clase de gimnasio.
- **AlertDialog:** Dialogo modal interactivo para la confirmacion al cancelar una reserva (implementado en la rama `mejora-ia`).
- **Material Icons Extended:** Biblioteca de iconos extendida para iconos de navegacion e interfaz.

### 3. Flujo de Navegacion (NavHost & NavController)
- **HomeScreen:** Lista de clases. Al hacer clic en una clase, navega a `DetailScreen/{classId}`.
- **DetailScreen:** Recibe el parametro `classId`, busca la clase correspondiente en los datos y permite confirmar la reserva.
- **ConfirmationScreen:** Muestra la pantalla de exito tras reservar una clase.
- **ReservationsScreen:** Muestra la lista de clases reservadas por Kiara Alburqueque con opcion de cancelacion.
- **ProfileScreen:** Muestra la informacion del usuario Kiara Alburqueque, estadisticas de clases tomadas y rachas.

---

## Control de Versiones (Git)

- **main:** Contiene la base completa de la aplicacion estructurada en commits funcionales individuales.
- **mejora-ia:** Incluye la funcionalidad adicional desarrollada con asistencia de IA (AlertDialog para cancelacion) y el archivo PROMPTS.md.
