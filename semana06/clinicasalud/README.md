\# Clínica Salud+ — Opción A



App de reserva de citas médicas en Jetpack Compose. Sin ViewModel/MVVM, todo el

estado se maneja con remember y mutableStateOf.



\## Requisitos funcionales — Opción A



\- Inicio: LazyRow con chips de especialidad (mínimo 2) y LazyColumn con lista de

&#x20; médicos (mínimo 3), cada tarjeta con ícono, nombre, especialidad y calificación.

&#x20; Implementado en la pantalla de Inicio.



\- Perfil del médico: recibe el médico elegido por parámetro de navegación (el id

&#x20; del médico viaja por la ruta y se resuelve al objeto completo antes de mostrar

&#x20; la pantalla); botón "Agendar cita".



\- Agendar cita: selección de fecha (3 opciones) y hora (3 opciones), ambas con

&#x20; RadioButton de selección única.



\- Confirmación: resumen de la cita agendada (médico, especialidad, fecha, hora);

&#x20; botón "Volver al inicio" que limpia el historial de navegación.



\- Menú lateral (drawer): ícono de menú en la barra superior de Inicio; tres

&#x20; destinos: Inicio, Mis citas e Historial médico.



\- Mis citas: lista con las citas agendadas, cada una con su estado (Confirmada

&#x20; o Completada) diferenciado con color.



\- Historial médico: lista que filtra y muestra solo las citas con estado

&#x20; Completada. Actualmente ninguna cita cambia su estado de Confirmada a

&#x20; Completada, por lo que esta pantalla se muestra vacía en la demo; no hay

&#x20; lógica implementada para ese cambio de estado, quedaría como mejora futura.



\## Mejora Fase 2 (rama mejora-ia)



\- Cancelar cita: botón visible solo en citas Confirmadas; muestra un cuadro de

&#x20; diálogo de confirmación antes de eliminar la cita de la lista.



\## Manejo de estado (sin ViewModel)



La lista de citas vive en el componente principal de navegación, guardada con

remember y una lista mutable, y se pasa por parámetros a cada pantalla que la

necesita. Cada pantalla maneja su propio estado de interfaz de forma local: el

filtro de especialidad en Inicio, la fecha y hora seleccionadas en Agendar

cita, y qué cita se está por cancelar en Mis citas.



\## Cómo llega el dato de Inicio a Confirmación



Desde Inicio, al tocar una tarjeta se navega al Perfil del médico pasando solo

el id del médico como argumento de la ruta. El Perfil busca el médico completo

usando ese id. Al presionar "Agendar cita" se navega a Agendar cita, pasando

otra vez el id, que también se vuelve a resolver ahí. Al confirmar fecha y

hora, se crea la cita, se agrega a la lista general, y se navega a

Confirmación pasando el id del médico, la fecha y la hora como argumentos de

la ruta. Confirmación vuelve a resolver el médico completo con ese id antes de

mostrar el resumen.



Solo se pasa el id del médico y no el objeto completo porque la navegación en

Compose solo admite tipos simples como texto o números como argumentos de

ruta; no se puede pasar un objeto completo directamente.



\## Por qué el drawer envuelve el Scaffold



El menú lateral se declara envolviendo el Scaffold, y no como un parámetro más

del Scaffold, porque necesita poder deslizarse por encima de toda la pantalla,

incluida la propia barra superior. Si estuviera dentro del Scaffold quedaría

limitado solo al área de contenido.



\## Por qué fecha y hora usan RadioButton aunque parezcan chips



Aunque visualmente cada opción se ve como una fila seleccionable, conceptualmente

es un grupo donde solo puede haber una opción marcada a la vez, tanto para la

fecha como para la hora. Ese comportamiento es exactamente el de un

RadioButton, por eso se usó ese componente en vez de un Checkbox, que sí

permitiría marcar varias opciones al mismo tiempo.

