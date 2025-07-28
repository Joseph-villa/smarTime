import java.util.ArrayList;

public class BancoPlantillas extends BancoAlmacenamiento<Plantilla> {
    public BancoPlantillas() {
        super();
    }

    public boolean existeNombre(String nombre) {
        for (Plantilla p : getElementos()) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    public Plantilla obtenerPorNombre(String nombre) {
        for (Plantilla p : getElementos()) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    public void eliminar(String nombre) {
        for (int i = 0; i < getElementos().size(); i++) {
            if (getElementos().get(i).getNombre().equalsIgnoreCase(nombre)) {
                getElementos().remove(i);
                return;
            }
        }
    }
}