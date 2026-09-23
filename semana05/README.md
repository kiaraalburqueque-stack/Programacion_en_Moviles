\# Semana 05 - Navegación en Jetpack Compose



Actividad de navegación con Jetpack Compose. La app simula un "Portal Académico" de Tecsup con 5 pantallas conectadas mediante NavController: Login, Home, Directorio de Alumnos, Expediente Académico y Configuración de Perfil.



\## Pantallas y navegación



\- \*\*LoginScreen\*\*: pantalla de acceso, navega a Home.

\- \*\*HomeScreen\*\*: menú de bienvenida con accesos a Directorio y Perfil.

\- \*\*ListScreen\*\*: Directorio de Alumnos, cada ítem navega a Detail con su ID.

\- \*\*DetailScreen\*\*: Expediente Académico, recibe el itemId como argumento tipado (Int).

\- \*\*ProfileScreen\*\*: Configuración de Perfil, botón de Cerrar Sesión regresa a Login.



\## Prompt utilizado en Gemini para mejorar la presentación



Actúa como desarrollador experto en Jetpack Compose y Material3. Tengo una app "Portal Académico" de Tecsup con 5 pantallas ya conectadas por navegación (LoginScreen.kt, HomeScreen.kt, ListScreen.kt, DetailScreen.kt, ProfileScreen.kt), actualmente con un tema morado. Quiero cambiar la paleta de color de TODA la app de morado a un esquema azul académico, sin tocar las rutas ni la lógica de navegación (NavController, argumentos, popBackStack, onClick), solo los colores.



1\. Si existe un archivo de colores centralizado (Color.kt o Theme.kt en ui/theme), actualiza ahí las variables de color moradas (fondos degradados, contenedores, texto) a tonos azules equivalentes, manteniendo el mismo contraste y legibilidad. Si los colores están definidos directamente dentro de cada pantalla (hardcodeados), cámbialos ahí mismo.



2\. LoginScreen: actualmente tiene un esquema de color distinto (beige/oliva) al resto de la app. Aplícale el mismo estilo visual (tipografía, formas, tarjetas) y la nueva paleta azul que usarán las demás pantallas, para que se vea consistente desde la primera pantalla.



3\. HomeScreen, ListScreen, DetailScreen, ProfileScreen: reemplaza únicamente los colores morados (degradados, contenedores de tarjetas, avatares, texto de énfasis) por la nueva paleta azul. Mantén el botón de "Cerrar Sesión" en tono rojo/rosado suave, sin cambiarlo.



4\. Usa una paleta azul coherente: un azul principal (para cabeceras y botones), un azul más claro (para fondos de tarjetas) y un fondo general claro que combine bien.



No cambies imports de navegación, rutas, firmas de funciones @Composable, ni la lógica de estado (remember, mutableStateOf, etc.) — solo colores y, en el caso de LoginScreen, el estilo visual para que combine con el resto.



\## Autora



Kiara Paola Alburqueque Arzapalo

