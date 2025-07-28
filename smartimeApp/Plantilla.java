import java.util.ArrayList;
import java.util.Scanner;

public class Plantilla {
    private String nombre;
    private ArrayList<Dia> semana;

    public Plantilla() {
        semana = new ArrayList<>();
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        for (String dia : dias) {
            semana.add(new Dia(dia));
        }
    }

    public void asignarBloque(Scanner scan) {
        while (true) {
            System.out.println("Días disponibles: Lunes, Martes, Miércoles, Jueves, Viernes, Sábado, Domingo");
            System.out.println("Ingrese el nombre del día para asignar un bloque (o 'salir' para terminar):");
            String nombreDia = scan.nextLine().trim();
            if (nombreDia.equalsIgnoreCase("salir")) {
                break;
            }
            Dia dia = null;
            for (Dia d : semana) {
                if (d.getNombreDia().equalsIgnoreCase(nombreDia)) {
                    dia = d;
                    break;
                }
            }
            if (dia == null) {
                System.out.println("Día inválido. Por favor, ingrese un día válido.");
                continue;
            }
            do {
                dia.asignarBloque(scan);
                System.out.println("¿Desea agregar otro bloque a este día? (s/n)");
                String respuesta;
                try {
                    respuesta = scan.nextLine().trim();
                } catch (Exception e) {
                    System.out.println("Entrada inválida. Asumiendo 'n'.");
                    respuesta = "n";
                }
                if (!respuesta.equalsIgnoreCase("s")) {
                    break;
                }
            } while (true);
        }
        mostrarSemana();
    }

    public void modificarPlantilla(Scanner scan) {
        System.out.println("\n=== Modificando plantilla: " + nombre + " ===");
        mostrarSemana();
        boolean continuar = true;
        while (continuar) {
            System.out.println("Días disponibles: Lunes, Martes, Miércoles, Jueves, Viernes, Sábado, Domingo");
            System.out.println("Ingrese el nombre del día para modificar (o 'salir' para terminar):");
            String nombreDia = scan.nextLine().trim();
            if (nombreDia.equalsIgnoreCase("salir")) {
                break;
            }
            Dia dia = null;
            for (Dia d : semana) {
                if (d.getNombreDia().equalsIgnoreCase(nombreDia)) {
                    dia = d;
                    break;
                }
            }
            if (dia == null) {
                System.out.println("Día inválido. Por favor, ingrese un día válido.");
                continue;
            }
            dia.modificarDia(scan);
            System.out.println("¿Desea modificar otro día? (s/n)");
            String respuesta;
            try {
                respuesta = scan.nextLine().trim();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Asumiendo 'n'.");
                respuesta = "n";
            }
            if (!respuesta.equalsIgnoreCase("s")) {
                continuar = false;
            }
        }
        mostrarSemana();
    }

    public void mostrarSemana() {
        System.out.println("\n=== Semana de la plantilla: " + nombre + " ===");
        boolean tieneBloques = false;
        for (Dia d : semana) {
            if (!d.getBloques().isEmpty()) {
                tieneBloques = true;
                System.out.println("Día: " + d.getNombreDia());
                for (BloqueDeTiempo b : d.getBloques()) {
                    System.out.println("  Bloque: " + b.getNombreBloque() + " [" +
                            b.getHoraIni()[0] + ":" + String.format("%02d", b.getHoraIni()[1]) + " - " +
                            b.getHoraFin()[0] + ":" + String.format("%02d", b.getHoraFin()[1]) + "]");
                }
            }
        }
        if (!tieneBloques) {
            System.out.println("No hay bloques asignados en la plantilla.");
        }
    }

    public void setNombre(String n) {
        nombre = n;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Dia> getSemana() {
        return semana;
    }
}