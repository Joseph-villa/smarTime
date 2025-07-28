import java.util.ArrayList;
import java.util.Scanner;

public class Dia {
    private String nombreDia;
    private ArrayList<BloqueDeTiempo> bloques;

    public Dia(String nom) {
        nombreDia = nom;
        bloques = new ArrayList<>();
    }

    public void asignarBloque(Scanner scan) {
        System.out.println("Asignando bloque al día: " + nombreDia);
        System.out.println("Ingrese hora inicial del bloque (HH:MM, ej. 08:30):");
        int[] horaIni = parseHora(scan);
        if (horaIni == null) {
            System.out.println("Entrada inválida. No se pudo asignar el bloque.");
            return;
        }
        System.out.println("Ingrese hora final del bloque (HH:MM, ej. 09:30):");
        int[] horaFin = parseHora(scan);
        if (horaFin == null) {
            System.out.println("Entrada inválida. No se pudo asignar el bloque.");
            return;
        }
        if (horaIni[0] < 0 || horaIni[0] > 23 || horaIni[1] < 0 || horaIni[1] > 59 ||
            horaFin[0] < 0 || horaFin[0] > 23 || horaFin[1] < 0 || horaFin[1] > 59 ||
            (horaFin[0] * 60 + horaFin[1]) <= (horaIni[0] * 60 + horaIni[1])) {
            System.out.println("Horas inválidas o duración no válida.");
            return;
        }
        System.out.println("1.-Estudio; 2.-Clases; 3.-Quehacer");
        int tipo = -1;
        while (tipo < 1 || tipo > 3) {
            try {
                tipo = scan.nextInt();
                scan.nextLine();
                if (tipo < 1 || tipo > 3) {
                    System.out.println("Tipo inválido. Ingrese un número entre 1 y 3:");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Intente de nuevo:");
                scan.nextLine();
            }
        }
        if (noChocaConOtro(horaIni, horaFin)) {
            bloques.add(new BloqueDeTiempo(horaIni, horaFin, tipo));
            System.out.println("Bloque agregado: " + (tipo == 1 ? "Estudio" : tipo == 2 ? "Clases" : "Quehacer"));
        } else {
            System.out.println("El bloque choca con otro existente.");
        }
    }

    private int[] parseHora(Scanner scan) {
        try {
            String input = scan.nextLine().trim();
            String[] parts = input.split(":");
            if (parts.length != 2) {
                return null;
            }
            int hora = Integer.parseInt(parts[0]);
            int minuto = Integer.parseInt(parts[1]);
            if (hora < 0 || hora > 23 || minuto < 0 || minuto > 59) {
                return null;
            }
            return new int[]{hora, minuto};
        } catch (Exception e) {
            return null;
        }
    }

    public void modificarDia(Scanner scan) {
        System.out.println("\n=== Modificando día: " + nombreDia + " ===");
        if (bloques.isEmpty()) {
            System.out.println("No hay bloques en este día.");
            System.out.println("¿Desea agregar un nuevo bloque? (s/n)");
            String respuesta = scan.nextLine().trim();
            if (respuesta.equalsIgnoreCase("s")) {
                asignarBloque(scan);
            }
            return;
        }
        System.out.println("Bloques actuales:");
        for (int i = 0; i < bloques.size(); i++) {
            BloqueDeTiempo b = bloques.get(i);
            System.out.println(i + ". " + b.getNombreBloque() + " [" +
                    b.getHoraIni()[0] + ":" + String.format("%02d", b.getHoraIni()[1]) + " - " +
                    b.getHoraFin()[0] + ":" + String.format("%02d", b.getHoraFin()[1]) + "]");
        }
        System.out.println("\nOpciones:");
        System.out.println("1. Modificar un bloque");
        System.out.println("2. Agregar un nuevo bloque");
        System.out.println("3. Eliminar un bloque");
        System.out.println("4. Volver");
        System.out.print("Seleccione una opción: ");
        int opcion = -1;
        try {
            opcion = scan.nextInt();
            scan.nextLine();
        } catch (Exception e) {
            System.out.println("Entrada inválida. Volviendo...");
            scan.nextLine();
            return;
        }
        switch (opcion) {
            case 1: // Modificar un bloque
                System.out.println("Seleccione el número del bloque a modificar (0-" + (bloques.size() - 1) + "):");
                int indice = -1;
                while (indice < 0 || indice >= bloques.size()) {
                    try {
                        indice = scan.nextInt();
                        scan.nextLine();
                        if (indice < 0 || indice >= bloques.size()) {
                            System.out.println("Número inválido. Ingrese un número entre 0 y " + (bloques.size() - 1) + ":");
                        }
                    } catch (Exception e) {
                        System.out.println("Entrada inválida. Intente de nuevo:");
                        scan.nextLine();
                    }
                }
                System.out.println("Ingrese nueva hora inicial (HH:MM, ej. 08:30):");
                int[] horaIni = parseHora(scan);
                if (horaIni == null) {
                    System.out.println("Entrada inválida. No se pudo modificar el bloque.");
                    return;
                }
                System.out.println("Ingrese nueva hora final (HH:MM, ej. 09:30):");
                int[] horaFin = parseHora(scan);
                if (horaFin == null) {
                    System.out.println("Entrada inválida. No se pudo modificar el bloque.");
                    return;
                }
                if (horaIni[0] < 0 || horaIni[0] > 23 || horaIni[1] < 0 || horaIni[1] > 59 ||
                    horaFin[0] < 0 || horaFin[0] > 23 || horaFin[1] < 0 || horaFin[1] > 59 ||
                    (horaFin[0] * 60 + horaFin[1]) <= (horaIni[0] * 60 + horaIni[1])) {
                    System.out.println("Horas inválidas o duración no válida.");
                    return;
                }
                System.out.println("1.-Estudio; 2.-Clases; 3.-Quehacer");
                int tipo = -1;
                while (tipo < 1 || tipo > 3) {
                    try {
                        tipo = scan.nextInt();
                        scan.nextLine();
                        if (tipo < 1 || tipo > 3) {
                            System.out.println("Tipo inválido. Ingrese un número entre 1 y 3:");
                        }
                    } catch (Exception e) {
                        System.out.println("Entrada inválida. Intente de nuevo:");
                        scan.nextLine();
                    }
                }
                if (noChocaConOtro(horaIni, horaFin, indice)) {
                    BloqueDeTiempo bloque = bloques.get(indice);
                    bloque.setHoraIni(horaIni);
                    bloque.setHoraFin(horaFin);
                    bloque.setTipo(tipo);
                    System.out.println("Bloque modificado: " + bloque.getNombreBloque());
                } else {
                    System.out.println("El nuevo horario choca con otro bloque existente.");
                }
                break;
            case 2: // Agregar un nuevo bloque
                asignarBloque(scan);
                break;
            case 3: // Eliminar un bloque
                System.out.println("Seleccione el número del bloque a eliminar (0-" + (bloques.size() - 1) + "):");
                indice = -1;
                while (indice < 0 || indice >= bloques.size()) {
                    try {
                        indice = scan.nextInt();
                        scan.nextLine();
                        if (indice < 0 || indice >= bloques.size()) {
                            System.out.println("Número inválido. Ingrese un número entre 0 y " + (bloques.size() - 1) + ":");
                        }
                    } catch (Exception e) {
                        System.out.println("Entrada inválida. Intente de nuevo:");
                        scan.nextLine();
                    }
                }
                eliminarBloque(indice);
                System.out.println("Bloque eliminado.");
                break;
            case 4: // Volver
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    private boolean noChocaConOtro(int[] horaIni, int[] horaFin, int indiceExcluido) {
        int iniMin = horaIni[0] * 60 + horaIni[1];
        int finMin = horaFin[0] * 60 + horaFin[1];
        for (int i = 0; i < bloques.size(); i++) {
            if (i == indiceExcluido) continue;
            BloqueDeTiempo b = bloques.get(i);
            int bIniMin = b.getHoraIni()[0] * 60 + b.getHoraIni()[1];
            int bFinMin = b.getHoraFin()[0] * 60 + b.getHoraFin()[1];
            if (!(finMin <= bIniMin || iniMin >= bFinMin)) {
                return false;
            }
        }
        return true;
    }

    private boolean noChocaConOtro(int[] horaIni, int[] horaFin) {
        return noChocaConOtro(horaIni, horaFin, -1);
    }

    public void eliminarBloque(int indice) {
        if (indice >= 0 && indice < bloques.size()) {
            bloques.remove(indice);
        }
    }

    public String getNombreDia() {
        return nombreDia;
    }

    public ArrayList<BloqueDeTiempo> getBloques() {
        return bloques;
    }
}