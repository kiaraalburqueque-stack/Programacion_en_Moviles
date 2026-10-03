---

## VI. Preguntas de Reflexión

### 1. ¿Por qué `Producto.kt` y `MainActivity.kt` se entregaron completos, y las pantallas no? ¿Qué tienen en común los archivos que sí se dejaron como esqueleto?
`Producto.kt` y `MainActivity.kt` se entregaron completos porque constituyen el **contrato de datos global** y el **punto de entrada base** del proyecto, asegurando una estructura uniforme en el modelo entidad y la configuración del tema. 

Los archivos que se dejaron como esqueleto tienen en común que representan **componentes de interfaz de usuario (UI) y lógica de presentación** (pantallas y tarjetas). Incluían bloques de marcador de posición (`// TODO`), layouts estáticos o iconos fijos (`ShoppingBasket`), requiriendo implementar la maquetación, el manejo de estados reactivos y la navegación.

### 2. ¿Cómo lograste que el filtro de categoría (`LazyRow`) y el cálculo del carrito reaccionen automáticamente sin que tú "actualices" nada a mano?
Se logró mediante el sistema de **Gestión de Estado y Recomposición automática** de Jetpack Compose. 

Al almacenar variables clave como `categoriaSeleccionada` o `itemsCarrito` con `mutableStateOf` (o listas reactivas observadas), Compose rastrea qué componibles leen dicho estado. Cuando el valor cambia, Compose ejecuta automáticamente una recomposición de los elementos afectados, recalculando valores derivados como `subtotal` y `total` sin manipulación manual de vistas.

### 3. ¿Qué diferencia notaste entre `navigate()` normal (Inicio → Detalle) y el que usa `popUpTo` (Datos de entrega → Confirmación)?
* **`navigate("detalle")` estándar:** Agrega la nueva pantalla a la pila de navegación (*backstack*). Al presionar "Atrás", el usuario regresa limpiamente a la pantalla anterior (`Inicio`).
* **`navigate("confirmacion")` con `popUpTo`:** Remueve pantallas intermedias de la pila antes de abrir el nuevo destino. Se usa en el flujo de compra para eliminar pantallas transaccionales (`Datos de entrega` y `Carrito`), evitando que el usuario regrese a modificar una orden ya finalizada.

### 4. ¿Qué tuviste que corregir del código que te generó la IA para el buscador en tiempo real?
Al integrar la lógica sugerida por la IA para el buscador, se ajustaron tres puntos clave:
1. **Normalización de texto:** Conversión a minúsculas (`lowercase()`) y eliminación de espacios (`trim()`) para evitar búsquedas sensibles a mayúsculas.
2. **Elevación de estado (*State Hoisting*):** Elevar el texto de búsqueda hacia el componible padre para filtrar la lista global sin reiniciar el estado en cada pulsación.
3. **Manejo de estados vacíos:** Agregar vistas de contingencia (*"No se encontraron productos"*) cuando la lista filtrada no devuelve resultados.

### 5. Compara el `NavigationDrawer` del Laboratorio 6 con el `NavigationBar` de esta tarea: ¿en qué caso usarías cada uno en un proyecto propio?
* **`NavigationBar` (Barra inferior):** Para destinos principales de acceso frecuente (3 a 5 secciones clave como *Inicio*, *Categorías*, *Carrito*, *Perfil*). Facilita el uso con una sola mano en e-commerce móviles.
* **`NavigationDrawer` (Menú lateral):** Para secciones secundarias o de configuración (ej: *Historial de pedidos*, *Ajustes*, *Soporte*, *Cerrar sesión*) que no requieren acceso continuo, evitando sobrecargar la pantalla principal.

---

## VII. Observaciones y Conclusiones

### Observaciones
1. **Gestión de permisos y dependencias para contenido remoto:** Durante la integración de la librería Coil (`AsyncImage`), Android bloqueaba las peticiones HTTP/HTTPS si no se agregaba explícitamente el permiso `android.permission.INTERNET` en el `AndroidManifest.xml` o si faltaba sincronizar la dependencia en `build.gradle.kts`.
2. **Sincronización de eventos y firmas en componentes:** Al trabajar sobre el esqueleto inicial, fue necesario refactorizar la firma de eventos (lambdas de callback) entre componentes independientes (ej. `onClick: () -> Unit` vs `onProductoClick: (Producto) -> Unit`) para garantizar que la comunicación entre vistas secundarias y pantallas principales fuera consistente.

### Conclusiones
1. **Desarrollo sobre esquema vs. desarrollo desde cero:** Desarrollar a partir de un esqueleto en la Fase 2 acelera la maquetación y mantiene estándares de código. No obstante, exige un análisis previo de la arquitectura existente para comprender el flujo de datos antes de añadir nuevas funcionalidades.
2. **Eficiencia del modelo declarativo en Jetpack Compose:** El paradigma declarativo simplifica la construcción de aplicaciones reactivas. La separación entre modelo (`Producto`), componentes reutilizables (`ProductoCard`) y pantallas asegura un código mantenible donde la UI refleja fielmente el estado de la aplicación.
