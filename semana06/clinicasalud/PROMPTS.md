# PROMPTS.md

## Prompt 1 — Mejora funcional: cancelar cita con AlertDialog

**Prompt usado:**

"Tengo una pantalla MisCitasScreen en Jetpack Compose que muestra una LazyColumn de citas con estado Confirmada/Completada, usando remember/mutableStateOf (sin ViewModel). Quiero agregar un botón 'Cancelar' en cada tarjeta de cita confirmada que, al presionarlo, muestre un AlertDialog pidiendo confirmación. Si el usuario confirma, la cita debe eliminarse de la lista."

**Lo que tuve que corregir:**

- La IA generó el `AlertDialog` y el botón dentro de `MisCitasScreen.kt`, pero el callback `onCancelarCita` no venía conectado a la lista real de citas. Tuve que agregar yo mismo la línea `citas.remove(cita)` en `AppNavigation.kt`, dentro del composable de la ruta `"mis_citas"`.
- Ajusté que el botón "Cancelar cita" solo apareciera cuando `cita.estado == "Confirmada"`, para no permitir cancelar citas ya completadas.

## Prompt 2 — Documentación del proyecto (README)

**Prompt usado:**

"Ayúdame a armar un README.md que documente, requisito por requisito de la rúbrica, en qué pantalla de mi código está implementado cada uno, para poder explicarlo en la sustentación oral."

**Lo que tuve que corregir:**

- Verifiqué manualmente cada afirmación del README contra el código real de cada pantalla (`InicioScreen`, `PerfilMedicoScreen`, `AgendarCitaScreen`, `ConfirmacionScreen`, `MisCitasScreen`, `HistorialScreen`, `AppDrawer`) antes de aceptar el contenido, para no dejar en el README algo que no estuviera realmente implementado.
- Detecté que `HistorialScreen`