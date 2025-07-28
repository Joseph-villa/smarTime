import java.util.ArrayList;

public class BancoPlantillas extends BancoAlmacenamiento<Plantilla> {
    public BancoPlantillas() {
        super();
    }

    public boolean existeNombre(String nombre) {
        for (Plantilla p : getElementos()) {
            if (p.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }
}