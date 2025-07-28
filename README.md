# smarTime Una aplicaciion de gestion Horaria toda chingona

 SmartTime
 Descripción
 Gestión de Horarios es una aplicación en Java que permite a los usuarios crear, modificar y gestionar horarios semanales.
 Los usuarios pueden definir plantillas con bloques de tiempo, asignar actividades con prioridades y usar las actividades.

 Características:

-Crear y modificar plantillas semanales con bloques de tiempo (Estudio, Clases, Quehacer).
-Asignar actividades con duración y prioridad a bloques específicos.
-Gestionar horarios basados en plantillas.
-Mostrar plantillas y horarios con detalles.
-Validación robusta de entradas y manejo de errores.

 Requisitos

-Java Development Kit (JDK) versión 8 o superior.
-Un entorno de desarrollo (IntelliJ IDEA, Eclipse) o compilador de línea de comandos (javac).
-Sistema operativo: Windows, macOS o Linux.

 Instalación

 Descarga o clona los archivos fuente del proyecto en un directorio.
 Asegúrate de que los siguientes archivos estén en el mismo directorio:
 Main.java
 Plantilla.java
 Horario.java
 Dia.java
 BloqueDeTiempo.java
 Actividad.java
 BancoPlantillas.java
 BancoHorarios.java
 BancoAlmacenamiento.java
 Abre una terminal o consola en el directorio del proyecto.
 Compila los archivos con el comando: javac *.java

 Uso

 Ejecuta la aplicación con: java Main

 Sigue el menú interactivo en la consola:
 1.Crear plantilla: Define una plantilla con bloques de tiempo por día.
 2.Asignar horario: Crea un horario basado en una plantilla y asigna actividades.
 3.Modificar plantilla: Edita bloques de tiempo en una plantilla.
 4.Modificar horario: Edita bloques o actividades en un horario.
 5.Eliminar plantilla: Borra una plantilla existente.
 6.Eliminar horario: Borra un horario existente.
 7.Mostrar plantillas: Muestra todas las plantillas.
 8.Mostrar horarios: Muestra todos los horarios con actividades.
 9.Usar actividad: Inicia una actividad con cronómetro, Pomodoro o en solitario.
 10.Salir: Cierra la aplicación.

Ejemplo de Uso

 -Selecciona la opción 1 y crea una plantilla llamada "SemanaEstudio".
 -Asigna un bloque de tiempo (ej. Lunes, 9:00-11:00, tipo "Estudio").
 -Selecciona la opción 2, crea un horario basado en "SemanaEstudio" y asigna actividades (ej. "Leer capítulo 1", 60 min, prioridad 3).
 -Usa la opción 9 para iniciar "Leer capítulo 1" con Pomodoro.
 -Marca la actividad como completada si lo deseas.
