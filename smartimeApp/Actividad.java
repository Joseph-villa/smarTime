public class Actividad {
    private int durac;
    private String nombre;
    private boolean completado;
    private int prioridad;
    private boolean activarPomodoro;
    private boolean notificacion;
    private boolean mensApoyo;

    public Actividad(String n, int dur, int prio) {
        nombre = n;
        durac = dur;
        prioridad = prio;
        completado = activarPomodoro = notificacion = mensApoyo = false;
    }

    public void cambiarEstado() {
        completado = !completado;
    }

    public void activarNotificacion() {
        notificacion = !notificacion;
    }

    public void activarPomodoro() {
        activarPomodoro = !activarPomodoro;
    }

    public void activarMensApoyo() {
        mensApoyo = !mensApoyo;
    }

    public void setNombre(String n) {
        nombre = n;
    }

    public void setDuracion(int dur) {
        durac = dur;
    }

    public void setPrioridad(int prio) {
        prioridad = prio;
    }

    public void iniciarPomodoro(String[] mensajesMotivacionales) {
        System.out.println("Iniciando Pomodoro para " + nombre + " (" + durac + " minutos).");
        int ciclos = durac / 25; // Número de ciclos completos de 25 minutos
        int resto = durac % 25; // Minutos restantes
        boolean detenido = false;

        for (int i = 0; i < ciclos && !detenido; i++) {
            System.out.println("Ciclo Pomodoro " + (i + 1) + ": 25 minutos de trabajo.");
            detenido = ejecutarTemporizador(25 * 60, mensajesMotivacionales);
            if (!detenido) {
                System.out.println("¡Tiempo de trabajo terminado! 5 minutos de descanso.");
                detenido = ejecutarTemporizador(5 * 60, mensajesMotivacionales);
            }
        }
        if (!detenido && resto > 0) {
            System.out.println("Último ciclo Pomodoro: " + resto + " minutos de trabajo.");
            detenido = ejecutarTemporizador(resto * 60, mensajesMotivacionales);
        }
        if (!detenido) {
            cambiarEstado();
            System.out.println("¡Actividad " + nombre + " completada con Pomodoro!");
        } else {
            System.out.println("Actividad " + nombre + " no completada (Pomodoro detenido).");
        }
    }

    private boolean ejecutarTemporizador(int segundosTotales, String[] mensajesMotivacionales) {
        boolean pausado = false;
        boolean detenido = false;
        int segundosTranscurridos = 0;
        System.out.println("Comandos: p (pausar/reanudar), d (detener), cualquier otra tecla para continuar.");
        while (segundosTranscurridos < segundosTotales && !detenido) {
            try {
                if (System.in.available() > 0) {
                    char comando = (char) System.in.read();
                    if (comando == 'p') {
                        pausado = !pausado;
                        System.out.println(pausado ? "Temporizador pausado." : "Temporizador reanudado.");
                    } else if (comando == 'd') {
                        detenido = true;
                        System.out.println("Temporizador detenido.");
                        break;
                    }
                }
                if (!pausado) {
                    System.out.println("Tiempo transcurrido: " + (segundosTranscurridos / 60) + ":" + String.format("%02d", (segundosTranscurridos % 60)));
                    segundosTranscurridos++;
                    if (segundosTranscurridos % 60 == 0 && mensApoyo) {
                        System.out.println("💬 Motivación: " + mensajesMotivacionales[new java.util.Random().nextInt(mensajesMotivacionales.length)]);
                    }
                }
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("Error en el temporizador: " + e.getMessage());
                detenido = true;
            }
        }
        return detenido;
    }

    public int getDurac() {
        return durac;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public boolean isCompletado() {
        return completado;
    }

    public boolean isActivarPomodoro() {
        return activarPomodoro;
    }

    public boolean isNotificacion() {
        return notificacion;
    }

    public boolean isMensApoyo() {
        return mensApoyo;
    }
}