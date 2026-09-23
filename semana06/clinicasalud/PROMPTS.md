\# PROMPTS.md



\## Prompt 1 — Mejora funcional: cancelar cita con AlertDialog



\*\*Prompt usado:\*\*

"Tengo una pantalla MisCitasScreen en Jetpack Compose que muestra una LazyColumn

de citas con estado Confirmada/Completada, usando remember/mutableStateOf (sin

ViewModel). Quiero agregar un botón 'Cancelar' en cada tarjeta de cita

confirmada que, al presionarlo, muestre un AlertDialog pidiendo confirmación.

Si el usuario confirma, la cita debe eliminarse de la lista."



\*\*Lo que tuve que corregir:\*\*

\- La IA generó el AlertDialog y el botón dentro de MisCitasScreen.kt, pero el

&#x20; callback onCancelarCita no venía conectado a la lista real de citas; tuve

&#x20; que agregar yo mismo la línea citas.remove(cita) en AppNavigation.kt,

&#x20; dentro del composable de la ruta "mis\_citas".

\- Ajusté que el botón "Cancelar cita" solo apareciera cuando

&#x20; cita.estado == "Confirmada", para no permitir cancelar citas ya

&#x20; completadas.



\## Prompt 2 — Documentación del proyecto (README)



\*\*Prompt usado:\*\*

"Ayúdame a armar un README.md que documente, requisito por requisito de la

rúbrica, en qué pantalla de mi código está implementado cada uno, para poder

explicarlo en la sustentación oral."



\*\*Lo que tuve que corregir:\*\*

\- Verifiqué manualmente cada afirmación del README contra el código real de

&#x20; cada pantalla (InicioScreen, PerfilMedicoScreen, AgendarCitaScreen,

&#x20; ConfirmacionScreen, MisCitasScreen, HistorialScreen, AppDrawer) antes de

&#x20; aceptar el contenido, para no dejar en el README algo que no estuviera

&#x20; realmente implementado.

\- Detecté que HistorialScreen filtra citas con estado "Completada", pero

&#x20; ninguna parte del código cambia el estado de una cita de "Confirmada" a

&#x20; "Completada", así que dejé esa observación documentada en el README en vez

&#x20; de omitirla.

