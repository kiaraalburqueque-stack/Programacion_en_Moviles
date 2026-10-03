# 🛒 Mi Bodega - Aplicación Móvil (Cliente)

Proyecto desarrollado para la gestión de compras y pedidos en bodegas locales a través de una interfaz moderna, intuitiva y asistida por IA.

---

## 📋 Cuestionario del Proyecto / Preguntas de Evaluación

### 1. ¿Cuál es el propósito de la aplicación y a quién está dirigida?
La aplicación **Mi Bodega** busca digitalizar la experiencia de compra en bodegas de barrio. Está dirigida a clientes finales que desean realizar pedidos de productos de primera necesidad, abarrotes y bebidas desde su smartphone con entrega a domicilio.

### 2. ¿Cuál es el Tech Stack y las librerías principales utilizadas?
* **Lenguaje:** Kotlin.
* **UI Framework:** Jetpack Compose con **Material 3** para un diseño moderno y reactivo.
* **Carga de Imágenes:** **Coil** (`io.coil-kt:coil-compose`) para la descarga e integración asíncrona de imágenes de productos desde URL remota.
* **Navegación:** `androidx.navigation:navigation-compose` para el flujo entre pantallas.
* **Control de Versiones:** Git / GitHub (Ramas `main` y `mejora-con-ia`).

### 3. ¿Cómo está estructurado el proyecto?
El proyecto sigue una arquitectura organizada por componentes y pantallas (`ui/`):
* `ui/cliente/modelo/`: Contiene el modelo de datos `Producto.kt` y el proveedor estático `DatosFake.kt`.
* `ui/componentes/`: Componentes reutilizables como `ProductoCard.kt` para el grid de productos.
* `ui/cliente/screens/`: Pantallas principales del flujo (Bienvenida, Registro, Inicio, Detalle, Carrito, Entrega y Confirmación).

### 4. ¿Cómo se integró la Inteligencia Artificial (IA) en la aplicación?
Se implementó un módulo de recomendación inteligente en la pantalla de inicio ("Sugerencia de IA para ti"). Este banner analiza combinaciones frecuentes de compra (por ejemplo: Coca-Cola + Galletas Oreo con 10% de descuento) para incentivar la venta cruzada e incrementar el valor del ticket promedio.

### 5. ¿Cuáles son las 7 pantallas del flujo completo del cliente?
1. **Registro / Login:** Pantalla de bienvenida con acceso vía número telefónico.
2. **Registro de datos:** Formulario de perfil con nombre, teléfono, dirección y referencia.
3. **Inicio / Productos:** Catálogo dinámico organizado por categorías, buscador y recomendación por IA.
4. **Detalle del producto:** Vista ampliada con descripción, selector de cantidad y botón de agregar al carrito.
5. **Carrito de compras:** Resumen de productos elegidos, cálculo automático de subtotal, delivery y total.
6. **Dirección y pago:** Confirmación de datos de envío y selección de método de pago (Efectivo contraentrega, Yape, Plin).
7. **Pedido confirmado:** Pantalla final de éxito con el resumen del pedido `#1024` y accesos directos al estado del pedido.

### 6. ¿Cómo se gestionaron los datos y el estado de la app?
La gestión de datos se realiza mediante un modelo centralizado `Producto` con parámetros flexibilizados (`id`, `nombre`, `descripcion`, `precio`, `categoria`, `imagenUrl`) y una lista reactiva en `DatosFake.kt`. El estado del carrito y la selección de ítems se maneja mediante estados componibles de Jetpack Compose (`remember`, `mutableStateOf`).

---

## 🧠 VI. Preguntas de Reflexión

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

## 📝 VII. Observaciones y Conclusiones

### Observaciones
1. **Gestión de permisos y dependencias para contenido remoto:** Durante la integración de la librería Coil (`AsyncImage`), Android bloqueaba las peticiones HTTP/HTTPS si no se agregaba explícitamente el permiso `android.permission.INTERNET` en el `AndroidManifest.xml` o si faltaba sincronizar la dependencia en `build.gradle.kts`.
2. **Sincronización de eventos y firmas en componentes:** Al trabajar sobre el esqueleto inicial, fue necesario refactorizar la firma de eventos (lambdas de callback) entre componentes independientes (ej. `onClick: () -> Unit` vs `onProductoClick: (Producto) -> Unit`) para garantizar que la comunicación entre vistas secundarias y pantallas principales fuera consistente.

### Conclusiones
1. **Desarrollo sobre esquema vs. desarrollo desde cero:** Desarrollar a partir de un esqueleto en la Fase 2 acelera la maquetación y mantiene estándares de código. No obstante, exige un análisis previo de la arquitectura existente para comprender el flujo de datos antes de añadir nuevas funcionalidades.
2. **Eficiencia del modelo declarativo en Jetpack Compose:** El paradigma declarativo simplifica la construcción de aplicaciones reactivas. La separación entre modelo (`Producto`), componentes reutilizables (`ProductoCard`) y pantallas asegura un código mantenible donde la UI refleja fielmente el estado de la aplicación.

---

## 🛠️ Instrucciones de Ejecución

1. Clonar el repositorio:
   ```bash
   git clone [https://github.com/kiaraalburqueque-stack/Programacion_en_Moviles.git](https://github.com/kiaraalburqueque-stack/Programacion_en_Moviles.git)
