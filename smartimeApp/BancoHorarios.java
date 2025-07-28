import java.util.ArrayList;

public class BancoHorarios extends BancoAlmacenamiento {
    private ArrayList<Horario> horarios;

    public BancoHorarios() {
        horarios = new ArrayList<>();
    }

    public void agregar(Horario h) {
        horarios.add(h);
    }

    public void eliminar(String nombre) {
        horarios.removeIf(h -> h.getNombre().equalsIgnoreCase(nombre));
    }

    public Horario obtenerPorNombre(String nombre) {
        for (Horario h : horarios) {
            if (h.getNombre().equalsIgnoreCase(nombre)) {
                return h;
            }
        }
        return null;
    }

    public ArrayList<Horario> obtenerTodos() {
        return horarios;
    }

    public boolean existeNombre(String nombre) {
        for (Horario h : horarios) {
            if (h.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }
}