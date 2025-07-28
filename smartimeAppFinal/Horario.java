import java.util.ArrayList;
import java.util.Scanner;

public class Horario {
    private String nombre;
    private ArrayList<Dia> semana;
    private ArrayList<Actividad> actividades;

    public Horario(Plantilla p) {
        semana = p.getSemana();
        actividades = new ArrayList<>();
    }

    public void setNombre(String a) {
        nombre = a;
    }

    public void asignarActividad(Scanner scan) {
        boolean continuar = true;
        while (continuar) {
            mostrarHorario(); // Mostrar el horario completo
            System.out.println("Ingrese el nombre del día para asignar actividades (o 'salir' para terminar):");
            String nombreDia = scan.nextLine().trim();
            if (nombreDia.equalsIgnoreCase("salir")) {
                break;
            }
            Dia dia = null;
            int diaIndex = -1;
            for (int i = 0; i < semana.size(); i++) {
                if (semana.get(i).getNombreDia().equalsIgnoreCase(nombreDia)) {
                    dia = semana.get(i);
                    diaIndex = i;
                    break;
                }
            }
            if (dia == null) {
                System.out.println("Día inválido. Por favor, ingrese un día válido.");
                continue;
            }
            ArrayList<BloqueDeTiempo> bloques = dia.getBloques();
            if (bloques.isEmpty()) {
                System.out.println("No hay bloques en este día.");
                continue;
            }
            BloqueDeTiempo bloq = selecBloquesDeTiempo(bloques, scan);
            if (bloq == null) {
                continue;
            }
            do {
                Actividad act = crearActividad(bloq, scan);
                bloq.agregarActividad(act);
                actividades.add(act);
                System.out.println("¿Desea agregar otra actividad a este bloque? (s/n)");
                String respuesta;
                try {
                    respuesta = scan.nextLine();
                } catch (Exception e) {
                    System.out.println("Entrada inválida. Asumiendo 'n'.");
                    respuesta = "n";
                }
                if (!respuesta.equalsIgnoreCase("s")) {
                    break;
                }
            } while (true);
            System.out.println("¿Desea asignar actividades a otro día? (s/n)");
            String respuesta;
            try {
                respuesta = scan.nextLine();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Asumiendo 'n'.");
                respuesta = "n";
            }
            if (!respuesta.equalsIgnoreCase("s")) {
                continuar = false;
            }
        }
        mostrarHorario();
    }

    private BloqueDeTiempo selecBloquesDeTiempo(ArrayList<BloqueDeTiempo> b, Scanner scan) {
        if (b.isEmpty()) {
            System.out.println("No hay bloques en este día.");
            return null;
        }
        System.out.println("Bloques disponibles:");
        for (int i = 0; i < b.size(); i++) {
            System.out.println(i + ". " + b.get(i).getNombreBloque() + " [" + b.get(i).getHorarioTexto() + "]");
        }
        int numBlo = -1;
        while (numBlo < 0 || numBlo >= b.size()) {
            try {
                numBlo = scan.nextInt();
                scan.nextLine();
                if (numBlo < 0 || numBlo >= b.size()) {
                    System.out.println("Número inválido. Ingrese un número entre 0 y " + (b.size() - 1) + ":");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Intente de nuevo:");
                scan.nextLine();
            }
        }
        return b.get(numBlo);
    }

    private Actividad crearActividad(BloqueDeTiempo b, Scanner scan) {
        int dur = b.duracMin();
        int durUsada = b.getActividades().stream().mapToInt(Actividad::getDurac).sum();
        System.out.println("El bloque de " + b.getNombreBloque() + " tiene " + (dur - durUsada) + " minutos disponibles");
        System.out.println("Ingrese duración de la actividad en minutos:");
        int durAct = -1;
        while (durAct < 0 || durAct > (dur - durUsada)) {
            try {
                durAct = scan.nextInt();
                scan.nextLine();
                if (durAct < 0 || durAct > (dur - durUsada)) {
                    System.out.println("Duración inválida. Ingrese un número entre 0 y " + (dur - durUsada) + ":");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Intente de nuevo:");
                scan.nextLine();
            }
        }
        System.out.println("¿Qué quiere hacer durante este tiempo?");
        String res = scan.nextLine();
        System.out.println("¿Qué nivel de prioridad le asigna? (1-5)");
        int prio = -1;
        while (prio < 1 || prio > 5) {
            try {
                prio = scan.nextInt();
                scan.nextLine();
                if (prio < 1 || prio > 5) {
                    System.out.println("Prioridad inválida. Ingrese un número entre 1 y 5:");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Intente de nuevo:");
                scan.nextLine();
            }
        }
        return new Actividad(res, durAct, prio);
    }

    public void modificarHorario(Scanner scan) {
        System.out.println("\n=== Modificando horario: " + nombre + " ===");
        mostrarHorario();
        boolean continuar = true;
        while (continuar) {
            System.out.println("Ingrese el nombre del día para modificar (o 'salir' para terminar):");
            String nombreDia = scan.nextLine().trim();
            if (nombreDia.equalsIgnoreCase("salir")) {
                break;
            }
            Dia dia = null;
            int diaIndex = -1;
            for (int i = 0; i < semana.size(); i++) {
                if (semana.get(i).getNombreDia().equalsIgnoreCase(nombreDia)) {
                    dia = semana.get(i);
                    diaIndex = i;
                    break;
                }
            }
            if (dia == null) {
                System.out.println("Día inválido. Por favor, ingrese un día válido.");
                continue;
            }
            System.out.println("\nOpciones para el día: " + dia.getNombreDia());
            System.out.println("1. Modificar bloques");
            System.out.println("2. Modificar actividades");
            System.out.println("3. Agregar actividades");
            System.out.println("4. Volver");
            System.out.print("Seleccione una opción: ");
            int opcion = -1;
            try {
                opcion = scan.nextInt();
                scan.nextLine();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Volviendo...");
                scan.nextLine();
                continue;
            }
            switch (opcion) {
                case 1: // Modificar bloques
                    dia.modificarDia(scan);
                    break;
                case 2: // Modificar actividades
                    ArrayList<BloqueDeTiempo> bloques = dia.getBloques();
                    if (bloques.isEmpty()) {
                        System.out.println("No hay bloques en este día.");
                        break;
                    }
                    BloqueDeTiempo bloq = selecBloquesDeTiempo(bloques, scan);
                    if (bloq == null) {
                        break;
                    }
                    if (bloq.getActividades().isEmpty()) {
                        System.out.println("No hay actividades en este bloque.");
                        break;
                    }
                    System.out.println("Actividades disponibles:");
                    for (int i = 0; i < bloq.getActividades().size(); i++) {
                        Actividad a = bloq.getActividades().get(i);
                        System.out.println(i + ". " + a.getNombre() + " (" + a.getDurac() + " min, Prioridad: " + a.getPrioridad() + ")");
                    }
                    System.out.println("\nOpciones:");
                    System.out.println("1. Modificar actividad");
                    System.out.println("2. Eliminar actividad");
                    System.out.println("3. Volver");
                    System.out.print("Seleccione una opción: ");
                    int subOpcion = -1;
                    try {
                        subOpcion = scan.nextInt();
                        scan.nextLine();
                    } catch (Exception e) {
                        System.out.println("Entrada inválida. Volviendo...");
                        scan.nextLine();
                        break;
                    }
                    switch (subOpcion) {
                        case 1: // Modificar actividad
                            System.out.println("Seleccione el número de la actividad (0-" + (bloq.getActividades().size() - 1) + "):");
                            int actIndex = -1;
                            while (actIndex < 0 || actIndex >= bloq.getActividades().size()) {
                                try {
                                    actIndex = scan.nextInt();
                                    scan.nextLine();
                                    if (actIndex < 0 || actIndex >= bloq.getActividades().size()) {
                                        System.out.println("Número inválido. Ingrese un número entre 0 y " + (bloq.getActividades().size() - 1) + ":");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Entrada inválida. Intente de nuevo:");
                                    scan.nextLine();
                                }
                            }
                            Actividad act = bloq.getActividades().get(actIndex);
                            System.out.println("Modificando actividad: " + act.getNombre());
                            System.out.println("Ingrese nuevo nombre (o presione Enter para mantener '" + act.getNombre() + "'):");
                            String nuevoNombre = scan.nextLine().trim();
                            if (!nuevoNombre.isEmpty()) {
                                act.setNombre(nuevoNombre);
                            }
                            System.out.println("Ingrese nueva duración en minutos (o -1 para mantener " + act.getDurac() + "):");
                            int nuevaDur = -1;
                            try {
                                nuevaDur = scan.nextInt();
                                scan.nextLine();
                            } catch (Exception e) {
                                System.out.println("Entrada inválida. Manteniendo duración actual.");
                                scan.nextLine();
                            }
                            if (nuevaDur >= 0) {
                                int durDisponible = bloq.duracMin() - bloq.getActividades().stream().mapToInt(Actividad::getDurac).sum() + act.getDurac();
                                if (nuevaDur <= durDisponible) {
                                    act.setDuracion(nuevaDur);
                                } else {
                                    System.out.println("Duración inválida. No hay suficiente tiempo en el bloque.");
                                }
                            }
                            System.out.println("Ingrese nueva prioridad (1-5, o -1 para mantener " + act.getPrioridad() + "):");
                            int nuevaPrio = -1;
                            try {
                                nuevaPrio = scan.nextInt();
                                scan.nextLine();
                            } catch (Exception e) {
                                System.out.println("Entrada inválida. Manteniendo prioridad actual.");
                                scan.nextLine();
                            }
                            if (nuevaPrio >= 1 && nuevaPrio <= 5) {
                                act.setPrioridad(nuevaPrio);
                            }
                            System.out.println("Actividad modificada: " + act.getNombre());
                            break;
                        case 2: // Eliminar actividad
                            System.out.println("Seleccione el número de la actividad a eliminar (0-" + (bloq.getActividades().size() - 1) + "):");
                            actIndex = -1;
                            while (actIndex < 0 || actIndex >= bloq.getActividades().size()) {
                                try {
                                    actIndex = scan.nextInt();
                                    scan.nextLine();
                                    if (actIndex < 0 || actIndex >= bloq.getActividades().size()) {
                                        System.out.println("Número inválido. Ingrese un número entre 0 y " + (bloq.getActividades().size() - 1) + ":");
                                    }
                                } catch (Exception e) {
                                    System.out.println("Entrada inválida. Intente de nuevo:");
                                    scan.nextLine();
                                }
                            }
                            bloq.eliminarActividad(actIndex);
                            actividades.remove(actIndex);
                            System.out.println("Actividad eliminada.");
                            break;
                        case 3: // Volver
                            break;
                        default:
                            System.out.println("Opción inválida.");
                    }
                    break;
                case 3: // Agregar actividades
                    asignarActividad(scan, diaIndex);
                    break;
                case 4: // Volver
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
            System.out.println("¿Desea modificar otro día? (s/n)");
            String respuesta;
            try {
                respuesta = scan.nextLine();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Asumiendo 'n'.");
                respuesta = "n";
            }
            if (!respuesta.equalsIgnoreCase("s")) {
                continuar = false;
            }
        }
        mostrarHorario();
    }

    private void asignarActividad(Scanner scan, int diaIndex) {
        ArrayList<BloqueDeTiempo> bloques = semana.get(diaIndex).getBloques();
        if (bloques.isEmpty()) {
            System.out.println("No hay bloques en este día.");
            return;
        }
        BloqueDeTiempo bloq = selecBloquesDeTiempo(bloques, scan);
        if (bloq == null) {
            return;
        }
        do {
            Actividad act = crearActividad(bloq, scan);
            bloq.agregarActividad(act);
            actividades.add(act);
            System.out.println("¿Desea agregar otra actividad a este bloque? (s/n)");
            String respuesta;
            try {
                respuesta = scan.nextLine();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Asumiendo 'n'.");
                respuesta = "n";
            }
            if (!respuesta.equalsIgnoreCase("s")) {
                break;
            }
        } while (true);
    }

    public void mostrarHorario() {
        System.out.println("\n=== Horario: " + nombre + " ===");
        boolean tieneBloques = false;
        for (Dia d : semana) {
            if (!d.getBloques().isEmpty()) {
                tieneBloques = true;
                System.out.println("Día: " + d.getNombreDia());
                for (BloqueDeTiempo b : d.getBloques()) {
                    System.out.println("  Bloque: " + b.getNombreBloque() + " [" + b.getHorarioTexto() + "]");
                    for (Actividad a : b.getActividades()) {
                        System.out.println("    Actividad: " + a.getNombre() + " (" + a.getDurac() +
                                " min, Prioridad: " + a.getPrioridad() +
                                ", Completado: " + a.isCompletado() +
                                ", Pomodoro: " + a.isActivarPomodoro() +
                                ", Notificación: " + a.isNotificacion() +
                                ", Mensajes de apoyo: " + a.isMensApoyo() + ")");
                    }
                }
            }
        }
        if (!tieneBloques) {
            System.out.println("No hay bloques asignados en el horario.");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Actividad> getActividades() {
        return actividades;
    }

    public ArrayList<Dia> getSemana() {
        return semana;
    }

    public ArrayList<BloqueDeTiempo> obtenerBloques(String diaActual) {
        for (Dia dia : semana) {
            if (dia.getNombreDia().equalsIgnoreCase(diaActual)) {
                return dia.getBloques();
            }
        }
        return new ArrayList<>();
    }
}