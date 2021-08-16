package utilidades.basico;

import java.util.Timer;
import java.util.TimerTask;

/*

    Javier 2021
    Temporizador de dos tiempos
    * Cumple el período de retraso.
    * Ejecuta la tarea. (ej: encender)
    * Cumple el período de duración
    * Ejecuta la tarea "finalizar" (Ej: apagar)
    * Cumple el período de intervalo.
    * Vuelve a empezar.

 */
public class Intermitencia extends Temporizador {
    private long duracion = 0;
    private Timer temporizadorDuracion;

    public Intermitencia () {
        super ();
        temporizadorDuracion = new Timer();

    }

    @Override


    public void iniciar () {
        temporizador.scheduleAtFixedRate(
                new TimerTask() {
                    @Override
                    public void run() {
                        ejecutar_inicio ();
                    }
                }, 1000 * retraso, 1000 * (intervalo));

        if (duracion > 0)
            temporizadorDuracion.scheduleAtFixedRate(
                    new TimerTask() {
                        @Override
                        public void run() {
                            ejecutar_fin ();
                        }
                    }, 1000 * (retraso + duracion), 1000 * (intervalo));
    }



    public void detener () {
        super.detener ();
        temporizadorDuracion.cancel();
    }

    public void ejecutar_inicio () {

    }

    public void ejecutar_fin () {

    }



    public long getDuracion() {
        return duracion;
    }

    public void setDuracion(long segundos) {
        this.duracion = segundos;
    }}


