import java.util.ArrayList;

public class BloqueDeTiempo {
    private int[] horaIni;
    private int[] horaFin;
    private String nombreBloque;
    private ArrayList<Actividad> actividades;

    public BloqueDeTiempo(int[] ini, int[] fin, int tipo) {
        horaIni = ini;
        horaFin = fin;
        actividades = new ArrayList<>();
        setTipo(tipo);
    }

    public void agregarActividad(Actividad act) {
        actividades.add(act);
    }

    public void eliminarActividad(int indice) {
        if (indice >= 0 && indice < actividades.size()) {
            actividades.remove(indice);
        }
    }

    public int duracMin() {
        return ((horaFin[0] * 60 + horaFin[1]) - (horaIni[0] * 60 + horaIni[1]));
    }

    public void setHoraIni(int[] horaIni) {
        this.horaIni = horaIni;
    }

    public void setHoraFin(int[] horaFin) {
        this.horaFin = horaFin;
    }

    public void setTipo(int tipo) {
        switch (tipo) {
            case 1:
                nombreBloque = "Estudio";
                break;
            case 2:
                nombreBloque = "Clases";
                break;
            case 3:
                nombreBloque = "Quehacer";
                break;
            default:
                nombreBloque = "Desconocido";
        }
    }

    public String getNombreBloque() {
        return nombreBloque;
    }

    public int[] getHoraIni() {
        return horaIni;
    }

    public int[] getHoraFin() {
        return horaFin;
    }

    public ArrayList<Actividad> getActividades() {
        return actividades;
    }

    public String getHorarioTexto() {
        return String.format("%02d:%02d - %02d:%02d", horaIni[0], horaIni[1], horaFin[0], horaFin[1]);
    }
}