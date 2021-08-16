package utilidades.basico;


import java.util.Timer;
import java.util.TimerTask;

/**
 * Javier 2019.
 *
 * Temporizador
 * ejecuta una tarea por intervalos de tiempo EN SEGUNDOS
 *
 *     * Cumple el período de retraso.
 *     * Ejecuta la tarea. (ej: contar)
 *     * Cumple el período de intervalo.
 *     * Vuelve a empezar.
 *
 **
 */

public class Temporizador {

    protected Timer temporizador;


    protected long retraso  = 0;
    protected long intervalo = 60;


    public Temporizador () {
        temporizador = new Timer();

    }

    public void iniciar () {
        temporizador.scheduleAtFixedRate(
                new TimerTask() {
                    @Override
                    public void run() {
                        ejecutar ();
                    }
                }, 1000 * retraso, 1000 * (intervalo ));
    }





    public void detener () {
        temporizador.cancel();
    }


    public void ejecutar () {

    }



    public long getRetraso() {
        return retraso;
    }

    public void setRetraso(long segundos) {
        this.retraso = segundos;
    }

    public long getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(long segundos) {
        this.intervalo = segundos;
    }


}
