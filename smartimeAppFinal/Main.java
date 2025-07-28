import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BancoPlantillas bancoPlantillas = new BancoPlantillas();
        BancoHorarios bancoHorarios = new BancoHorarios();
        ArrayList<Actividad> completadas = new ArrayList<>();
        Horario horario = null;
        Plantilla plantilla = null;
        boolean salir = false;

        String[] mensajesMotivacionales = {
            "¡Tú puedes con esto! Sigue adelante.",
            "¡Gran trabajo! Mantén el enfoque.",
            "¡Estás haciendo un progreso increíble!",
            "¡Sigue así, eres imparable!",
            "¡Un paso más cerca de tus metas!"
        };
        Random random = new Random();

        while (!salir) {
            System.out.println("\n=== Gestión de Horarios ===");
            System.out.println("1. Crear plantilla");
            System.out.println("2. Asignar Horario");
            System.out.println("3. Modificar plantilla");
            System.out.println("4. Modificar horario");
            System.out.println("5. Eliminar plantilla");
            System.out.println("6. Eliminar horario");
            System.out.println("7. Mostrar plantillas");
            System.out.println("8. Mostrar horarios");
            System.out.println("9. Usar actividad");
            System.out.println("10. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = -1;
            try {
                opcion = scan.nextInt();
                scan.nextLine(); // Consumir el salto de línea después de nextInt()
            } catch (Exception e) {
                System.out.println("Error: Entrada inválida. Intente de nuevo.");
                scan.nextLine(); // Limpiar el buffer
                continue;
            }

            switch (opcion) {
                case 1: // Crear plantilla
                    plantilla = new Plantilla();
                    System.out.println("Creando una nueva plantilla...");
                    plantilla.asignarBloque(scan);
                    System.out.print("Ingrese el nombre de la plantilla: ");
                    String nombrePlantilla = "";
                    try {
                        nombrePlantilla = scan.nextLine().trim();
                    } catch (Exception e) {
                        System.out.println("Error al leer el nombre de la plantilla: " + e.getMessage());
                        scan.nextLine();
                        break;
                    }
                    if (nombrePlantilla.isEmpty()) {
                        System.out.println("Error: El nombre de la plantilla no puede estar vacío.");
                        break;
                    }
                    try {
                        if (bancoPlantillas.existeNombre(nombrePlantilla)) {
                            System.out.println("Error: Ya existe una plantilla con el nombre '" + nombrePlantilla + "'.");
                        } else {
                            plantilla.setNombre(nombrePlantilla);
                            bancoPlantillas.agregar(plantilla);
                            System.out.println("Plantilla creada: " + nombrePlantilla);
                        }
                    } catch (Exception e) {
                        System.out.println("Error al crear la plantilla: " + e.getMessage());
                        break;
                    }
                    break;

                case 2: // Crear horario y asignar actividades
                    System.out.print("Ingrese el nombre de la plantilla: ");
                    nombrePlantilla = "";
                    try {
                        nombrePlantilla = scan.nextLine().trim();
                    } catch (Exception e) {
                        System.out.println("Error al leer el nombre de la plantilla: " + e.getMessage());
                        scan.nextLine();
                        break;
                    }
                    if (nombrePlantilla.isEmpty()) {
                        System.out.println("Error: El nombre de la plantilla no puede estar vacío.");
                        break;
                    }
                    try {
                        plantilla = bancoPlantillas.obtenerPorNombre(nombrePlantilla);
                        if (plantilla == null) {
                            System.out.println("Error: Plantilla no encontrada: " + nombrePlantilla);
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("Error al buscar la plantilla: " + e.getMessage());
                        break;
                    }
                    System.out.print("Ingrese el nombre del horario: ");
                    String nombreHorario = "";
                    try {
                        nombreHorario = scan.nextLine().trim();
                    } catch (Exception e) {
                        System.out.println("Error al leer el nombre del horario: " + e.getMessage());
                        scan.nextLine();
                        break;
                    }
                    if (nombreHorario.isEmpty()) {
                        System.out.println("Error: El nombre del horario no puede estar vacío.");
                        break;
                    }
                    try {
                        if (bancoHorarios.existeNombre(nombreHorario)) {
                            System.out.println("Error: Ya existe un horario con el nombre '" + nombreHorario + "'.");
                        } else {
                            horario = new Horario(plantilla);
                            horario.setNombre(nombreHorario);
                            System.out.println("Creando horario: " + nombreHorario);
                            System.out.println("Asignando actividades...");
                            horario.asignarActividad(scan);
                            bancoHorarios.agregar(horario);
                            System.out.println("Horario creado y actividades asignadas: " + nombreHorario);
                        }
                    } catch (Exception e) {
                        System.out.println("Error al crear el horario: " + e.getMessage());
                        break;
                    }
                    break;

                case 3: // Modificar plantilla
                    System.out.print("Ingrese el nombre de la plantilla: ");
                    nombrePlantilla = "";
                    try {
                        nombrePlantilla = scan.nextLine().trim();
                    } catch (Exception e) {
                        System.out.println("Error al leer el nombre de la plantilla: " + e.getMessage());
                        scan.nextLine();
                        break;
                    }
                    if (nombrePlantilla.isEmpty()) {
                        System.out.println("Error: El nombre de la plantilla no puede estar vacío.");
                        break;
                    }
                    try {
                        plantilla = bancoPlantillas.obtenerPorNombre(nombrePlantilla);
                        if (plantilla == null) {
                            System.out.println("Error: Plantilla no encontrada: " + nombrePlantilla);
                            break;
                        }
                        plantilla.modificarPlantilla(scan);
                        System.out.println("Plantilla modificada: " + nombrePlantilla);
                    } catch (Exception e) {
                        System.out.println("Error al modificar la plantilla: " + e.getMessage());
                        break;
                    }
                    break;

                case 4: // Modificar horario
                    System.out.print("Ingrese el nombre del horario: ");
                    nombreHorario = "";
                    try {
                        nombreHorario = scan.nextLine().trim();
                    } catch (Exception e) {
                        System.out.println("Error al leer el nombre del horario: " + e.getMessage());
                        scan.nextLine();
                        break;
                    }
                    if (nombreHorario.isEmpty()) {
                        System.out.println("Error: El nombre del horario no puede estar vacío.");
                        break;
                    }
                    try {
                        horario = bancoHorarios.obtenerPorNombre(nombreHorario);
                        if (horario == null) {
                            System.out.println("Error: Horario no encontrado: " + nombreHorario);
                            break;
                        }
                        horario.modificarHorario(scan);
                        System.out.println("Horario modificado: " + nombreHorario);
                    } catch (Exception e) {
                        System.out.println("Error al modificar el horario: " + e.getMessage());
                        break;
                    }
                    break;

                case 5: // Eliminar plantilla
                    System.out.print("Ingrese el nombre de la plantilla a eliminar: ");
                    nombrePlantilla = "";
                    try {
                        nombrePlantilla = scan.nextLine().trim();
                    } catch (Exception e) {
                        System.out.println("Error al leer el nombre de la plantilla: " + e.getMessage());
                        scan.nextLine();
                        break;
                    }
                    if (nombrePlantilla.isEmpty()) {
                        System.out.println("Error: El nombre de la plantilla no puede estar vacío.");
                        break;
                    }
                    try {
                        if (bancoPlantillas.obtenerPorNombre(nombrePlantilla) == null) {
                            System.out.println("Error: Plantilla no encontrada: " + nombrePlantilla);
                            break;
                        }
                        System.out.println("¿Está seguro de eliminar la plantilla '" + nombrePlantilla + "'? (s/n)");
                        String confirmacion = scan.nextLine().trim();
                        if (confirmacion.equalsIgnoreCase("s")) {
                            bancoPlantillas.eliminar(nombrePlantilla);
                            System.out.println("Plantilla eliminada: " + nombrePlantilla);
                        } else {
                            System.out.println("Eliminación cancelada.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error al eliminar la plantilla: " + e.getMessage());
                        break;
                    }
                    break;

                case 6: // Eliminar horario
                    System.out.print("Ingrese el nombre del horario a eliminar: ");
                    nombreHorario = "";
                    try {
                        nombreHorario = scan.nextLine().trim();
                    } catch (Exception e) {
                        System.out.println("Error al leer el nombre del horario: " + e.getMessage());
                        scan.nextLine();
                        break;
                    }
                    if (nombreHorario.isEmpty()) {
                        System.out.println("Error: El nombre del horario no puede estar vacío.");
                        break;
                    }
                    try {
                        if (bancoHorarios.obtenerPorNombre(nombreHorario) == null) {
                            System.out.println("Error: Horario no encontrado: " + nombreHorario);
                            break;
                        }
                        System.out.println("¿Está seguro de eliminar el horario '" + nombreHorario + "'? (s/n)");
                        String confirmacion = scan.nextLine().trim();
                        if (confirmacion.equalsIgnoreCase("s")) {
                            bancoHorarios.eliminar(nombreHorario);
                            System.out.println("Horario eliminado: " + nombreHorario);
                        } else {
                            System.out.println("Eliminación cancelada.");
                        }
                    } catch (Exception e) {
                        System.out.println("Error al eliminar el horario: " + e.getMessage());
                        break;
                    }
                    break;

                case 7: // Mostrar plantillas
                    ArrayList<Plantilla> plantillas = bancoPlantillas.obtenerTodos();
                    if (plantillas.isEmpty()) {
                        System.out.println("No hay plantillas.");
                    } else {
                        System.out.println("\n=== Plantillas ===");
                        for (Plantilla p : plantillas) {
                            System.out.println("Plantilla: " + p.getNombre());
                            for (Dia d : p.getSemana()) {
                                if (!d.getBloques().isEmpty()) {
                                    System.out.println("  Día: " + d.getNombreDia());
                                    for (BloqueDeTiempo b : d.getBloques()) {
                                        System.out.println("    Bloque: " + b.getNombreBloque() + " [" +
                                                b.getHoraIni()[0] + ":" + String.format("%02d", b.getHoraIni()[1]) + " - " +
                                                b.getHoraFin()[0] + ":" + String.format("%02d", b.getHoraFin()[1]) + "]");
                                    }
                                }
                            }
                        }
                    }
                    break;

                case 8: // Mostrar horarios
                    ArrayList<Horario> horarios = bancoHorarios.obtenerTodos();
                    if (horarios.isEmpty()) {
                        System.out.println("No hay horarios.");
                    } else {
                        System.out.println("\n=== Horarios ===");
                        for (Horario h : horarios) {
                            System.out.println("Horario: " + h.getNombre());
                            for (Dia d : h.getSemana()) {
                                if (!d.getBloques().isEmpty()) {
                                    System.out.println("  Día: " + d.getNombreDia());
                                    for (BloqueDeTiempo b : d.getBloques()) {
                                        System.out.println("    Bloque: " + b.getNombreBloque() + " [" +
                                                b.getHoraIni()[0] + ":" + String.format("%02d", b.getHoraIni()[1]) + " - " +
                                                b.getHoraFin()[0] + ":" + String.format("%02d", b.getHoraFin()[1]) + "]");
                                        for (Actividad a : b.getActividades()) {
                                            System.out.println("      Actividad: " + a.getNombre() + " (" + a.getDurac() +
                                                    " min, Prioridad: " + a.getPrioridad() +
                                                    ", Completado: " + a.isCompletado() +
                                                    ", Pomodoro: " + a.isActivarPomodoro() +
                                                    ", Notificación: " + a.isNotificacion() +
                                                    ", Mensajes de apoyo: " + a.isMensApoyo() + ")");
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;

                case 9: // Usar actividad
                    System.out.print("Ingrese el nombre del horario: ");
                    nombreHorario = "";
                    try {
                        nombreHorario = scan.nextLine().trim();
                    } catch (Exception e) {
                        System.out.println("Error al leer el nombre del horario: " + e.getMessage());
                        scan.nextLine();
                        break;
                    }
                    if (nombreHorario.isEmpty()) {
                        System.out.println("Error: El nombre del horario no puede estar vacío.");
                        break;
                    }
                    try {
                        horario = bancoHorarios.obtenerPorNombre(nombreHorario);
                        if (horario == null) {
                            System.out.println("Error: Horario no encontrado: " + nombreHorario);
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("Error al buscar el horario: " + e.getMessage());
                        break;
                    }
                    horario.mostrarHorario(); // Mostrar el horario completo
                    System.out.println("Ingrese el nombre del día:");
                    String nombreDia = "";
                    try {
                        nombreDia = scan.nextLine().trim();
                    } catch (Exception e) {
                        System.out.println("Error al leer el nombre del día: " + e.getMessage());
                        scan.nextLine();
                        break;
                    }
                    if (nombreDia.isEmpty()) {
                        System.out.println("Error: El nombre del día no puede estar vacío.");
                        break;
                    }
                    Dia dia = null;
                    try {
                        for (Dia d : horario.getSemana()) {
                            if (d.getNombreDia().equalsIgnoreCase(nombreDia)) {
                                dia = d;
                                break;
                            }
                        }
                        if (dia == null) {
                            System.out.println("Error: Día no encontrado: " + nombreDia);
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("Error al buscar el día: " + e.getMessage());
                        break;
                    }
                    ArrayList<BloqueDeTiempo> bloques = dia.getBloques();
                    if (bloques.isEmpty()) {
                        System.out.println("Error: No hay bloques en este día.");
                        break;
                    }
                    System.out.println("Bloques disponibles:");
                    for (int i = 0; i < bloques.size(); i++) {
                        System.out.println(i + ". " + bloques.get(i).getNombreBloque() + " [" + bloques.get(i).getHorarioTexto() + "]");
                    }
                    int numBlo = -1;
                    while (numBlo < 0 || numBlo >= bloques.size()) {
                        try {
                            System.out.print("Seleccione un bloque (0-" + (bloques.size() - 1) + "): ");
                            numBlo = scan.nextInt();
                            scan.nextLine();
                            if (numBlo < 0 || numBlo >= bloques.size()) {
                                System.out.println("Error: Número inválido. Ingrese un número entre 0 y " + (bloques.size() - 1) + ".");
                            }
                        } catch (Exception e) {
                            System.out.println("Error: Entrada inválida. Intente de nuevo.");
                            scan.nextLine();
                        }
                    }
                    BloqueDeTiempo bloq = bloques.get(numBlo);
                    ArrayList<Actividad> actividades = bloq.getActividades();
                    if (actividades.isEmpty()) {
                        System.out.println("Error: No hay actividades en este bloque.");
                        break;
                    }
                    System.out.println("Actividades disponibles:");
                    for (int i = 0; i < actividades.size(); i++) {
                        System.out.println(i + ". " + actividades.get(i).getNombre());
                    }
                    int numAct = -1;
                    while (numAct < 0 || numAct >= actividades.size()) {
                        try {
                            System.out.print("Seleccione una actividad (0-" + (actividades.size() - 1) + "): ");
                            numAct = scan.nextInt();
                            scan.nextLine();
                            if (numAct < 0 || numAct >= actividades.size()) {
                                System.out.println("Error: Número inválido. Ingrese un número entre 0 y " + (actividades.size() - 1) + ".");
                            }
                        } catch (Exception e) {
                            System.out.println("Error: Entrada inválida. Intente de nuevo.");
                            scan.nextLine();
                        }
                    }
                    Actividad actividad = actividades.get(numAct);
                    boolean continuar = true;
                    while (continuar) {
                        System.out.println("\nOpciones para la actividad: " + actividad.getNombre());
                        System.out.println("1. Iniciar actividad (cronómetro)");
                        System.out.println("2. Iniciar actividad (Pomodoro)");
                        System.out.println("3. Usar actividad");
                        System.out.println("4. Activar/Desactivar notificación");
                        System.out.println("5. Activar/Desactivar Pomodoro");
                        System.out.println("6. Activar/Desactivar mensajes de apoyo");
                        System.out.println("7. Volver");
                        System.out.print("Seleccione una opción: ");
                        int subOpcion = -1;
                        try {
                            subOpcion = scan.nextInt();
                            scan.nextLine();
                        } catch (Exception e) {
                            System.out.println("Error: Entrada inválida. Volviendo...");
                            scan.nextLine();
                            break;
                        }
                        switch (subOpcion) {
                            case 1: // Iniciar actividad (cronómetro)
                                System.out.println("Estás usando " + actividad.getNombre() + "...");
                                if (actividad.isNotificacion()) {
                                    System.out.println("📢 Notificación: ¡Hora de " + actividad.getNombre() + "!");
                                }
                                if (actividad.isMensApoyo()) {
                                    System.out.println("💬 Motivación: " + mensajesMotivacionales[random.nextInt(mensajesMotivacionales.length)]);
                                }
                                System.out.println("Cronómetro iniciado para " + actividad.getNombre() + " (" + actividad.getDurac() + " minutos).");
                                int duracionSegundos = actividad.getDurac() * 60;
                                boolean pausado = false;
                                boolean detenido = false;
                                int segundosTranscurridos = 0;
                                System.out.println("Comandos: p (pausar/reanudar), d (detener), cualquier otra tecla para continuar.");
                                while (segundosTranscurridos < duracionSegundos && !detenido) {
                                    try {
                                        if (System.in.available() > 0) {
                                            char comando = (char) System.in.read();
                                            if (comando == 'p') {
                                                pausado = !pausado;
                                                System.out.println(pausado ? "Cronómetro pausado." : "Cronómetro reanudado.");
                                            } else if (comando == 'd') {
                                                detenido = true;
                                                System.out.println("Cronómetro detenido.");
                                                break;
                                            }
                                        }
                                        if (!pausado) {
                                            System.out.println("Tiempo transcurrido: " + (segundosTranscurridos / 60) + ":" + String.format("%02d", (segundosTranscurridos % 60)));
                                            segundosTranscurridos++;
                                            if (segundosTranscurridos % 60 == 0 && actividad.isMensApoyo()) {
                                                System.out.println("💬 Motivación: " + mensajesMotivacionales[random.nextInt(mensajesMotivacionales.length)]);
                                            }
                                        }
                                        Thread.sleep(1000);
                                    } catch (Exception e) {
                                        System.out.println("Error en el cronómetro: " + e.getMessage());
                                        detenido = true;
                                    }
                                }
                                if (!detenido) {
                                    System.out.println("¿Desea marcar la actividad como completada? (s/n)");
                                    String respuesta = scan.nextLine().trim();
                                    if (respuesta.equalsIgnoreCase("s")) {
                                        actividad.cambiarEstado();
                                        completadas.add(actividad);
                                        System.out.println("¡Actividad " + actividad.getNombre() + " marcada como completada!");
                                        System.out.println("¡Actividad " + actividad.getNombre() + " finalizada!");
                                    } else {
                                        System.out.println("Actividad " + actividad.getNombre() + " no marcada como completada.");
                                    }
                                    if (actividad.isActivarPomodoro()) {
                                        System.out.println("Pomodoro activado para " + actividad.getNombre() + ".");
                                    }
                                } else {
                                    System.out.println("Actividad " + actividad.getNombre() + " no completada (cronómetro detenido).");
                                    System.out.println("¿Desea marcar la actividad como completada? (s/n)");
                                    String respuesta = scan.nextLine().trim();
                                    if (respuesta.equalsIgnoreCase("s")) {
                                        actividad.cambiarEstado();
                                        completadas.add(actividad);
                                        System.out.println("¡Actividad " + actividad.getNombre() + " marcada como completada!");
                                        System.out.println("¡Actividad " + actividad.getNombre() + " finalizada!");
                                    } else {
                                        System.out.println("Actividad " + actividad.getNombre() + " no marcada como completada.");
                                    }
                                }
                                break;
                            case 2: // Iniciar actividad (Pomodoro)
                                System.out.println("Estás usando " + actividad.getNombre() + "...");
                                if (actividad.isNotificacion()) {
                                    System.out.println("📢 Notificación: ¡Hora de " + actividad.getNombre() + "!");
                                }
                                actividad.iniciarPomodoro(mensajesMotivacionales);
                                System.out.println("¿Desea marcar la actividad como completada? (s/n)");
                                String respuesta = scan.nextLine().trim();
                                if (respuesta.equalsIgnoreCase("s")) {
                                    actividad.cambiarEstado();
                                    completadas.add(actividad);
                                    System.out.println("¡Actividad " + actividad.getNombre() + " marcada como completada!");
                                    System.out.println("¡Actividad " + actividad.getNombre() + " finalizada!");
                                } else {
                                    System.out.println("Actividad " + actividad.getNombre() + " no marcada como completada.");
                                }
                                break;
                            case 3: // Usar actividad
                                System.out.println("Estás usando " + actividad.getNombre() + "...");
                                if (actividad.isNotificacion()) {
                                    System.out.println("📢 Notificación: ¡Hora de " + actividad.getNombre() + "!");
                                }
                                if (actividad.isMensApoyo()) {
                                    System.out.println("💬 Motivación: " + mensajesMotivacionales[random.nextInt(mensajesMotivacionales.length)]);
                                }
                                System.out.println("¿Desea marcar la actividad como completada? (s/n)");
                                respuesta = scan.nextLine().trim();
                                if (respuesta.equalsIgnoreCase("s")) {
                                    actividad.cambiarEstado();
                                    completadas.add(actividad);
                                    System.out.println("¡Actividad " + actividad.getNombre() + " marcada como completada!");
                                    System.out.println("¡Actividad " + actividad.getNombre() + " finalizada!");
                                } else {
                                    System.out.println("Actividad " + actividad.getNombre() + " no marcada como completada.");
                                }
                                break;
                            case 4: // Activar/Desactivar notificación
                                actividad.activarNotificacion();
                                System.out.println("Notificación cambiada: " + actividad.isNotificacion());
                                break;
                            case 5: // Activar/Desactivar Pomodoro
                                actividad.activarPomodoro();
                                System.out.println("Pomodoro cambiado: " + actividad.isActivarPomodoro());
                                break;
                            case 6: // Activar/Desactivar mensajes de apoyo
                                actividad.activarMensApoyo();
                                System.out.println("Mensajes de apoyo cambiados: " + actividad.isMensApoyo());
                                break;
                            case 7: // Volver
                                continuar = false;
                                break;
                            default:
                                System.out.println("Error: Opción inválida.");
                        }
                    }
                    break;

                case 10: // Salir
                    salir = true;
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Error: Opción inválida.");
            }
        }
        scan.close();
    }
}