import java.util.ArrayList;

public abstract class BancoAlmacenamiento<T> {
    private ArrayList<T> elementos;

    public BancoAlmacenamiento() {
        elementos = new ArrayList<>();
    }

    public void agregar(T elemento) {
        elementos.add(elemento);
    }

    public ArrayList<T> obtenerTodos() {
        return elementos;
    }

    protected ArrayList<T> getElementos() {
        return elementos;
    }
}