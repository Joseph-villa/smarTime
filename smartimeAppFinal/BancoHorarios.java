import java.util.ArrayList;

public class BancoHorarios extends BancoAlmacenamiento<Horario> {
    public BancoHorarios() {
        super();
    }

    public boolean existeNombre(String nombre) {
        for (Horario h : getElementos()) {
            if (h.getNombre().equals(nombre)) {
                return true;
            }
        }
        return false;
    }
}