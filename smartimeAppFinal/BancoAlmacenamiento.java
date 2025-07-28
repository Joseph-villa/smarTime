import java.util.ArrayList;

public abstract class BancoAlmacenamiento<T> {
    private ArrayList<T> elementos;

    public BancoAlmacenamiento() {
        elementos = new ArrayList<>();
    }

    public ArrayList<T> getElementos() {
        return elementos;
    }

    public void agregar(T elemento) {
        elementos.add(elemento);
    }

    public void eliminar(String nombre) {
        for (int i = 0; i < elementos.size(); i++) {
            if (elementos.get(i) instanceof Plantilla) {
                if (((Plantilla) elementos.get(i)).getNombre().equals(nombre)) {
                    elementos.remove(i);
                    return;
                }
            } else if (elementos.get(i) instanceof Horario) {
                if (((Horario) elementos.get(i)).getNombre().equals(nombre)) {
                    elementos.remove(i);
                    return;
                }
            }
        }
    }

    public T obtenerPorNombre(String nombre) {
        for (T elemento : elementos) {
            if (elemento instanceof Plantilla) {
                if (((Plantilla) elemento).getNombre().equals(nombre)) {
                    return elemento;
                }
            } else if (elemento instanceof Horario) {
                if (((Horario) elemento).getNombre().equals(nombre)) {
                    return elemento;
                }
            }
        }
        return null;
    }

    public ArrayList<T> obtenerTodos() {
        return elementos;
    }
}