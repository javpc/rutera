package utilidades.basico;

import java.util.Date;

public class Intervalo {

    private final long SEGUNDOS_EN_MILISEGUNDOS = 1000;
    private final long MINUTOS_EN_MILISEGUNDOS = 60000; // 60 * segundos
    private final long HORAS_EN_MILISEGUNDOS = 3600000; // 60 * minutos
    private final long DIAS_EN_MILISEGUNDOS = 86400000; // 24 * horas


    private Date momento_inicial;
    private Date momento_final;
    private long diferencia = 0;

    public Intervalo () {
        momento_inicial = new Date();
    }

    public Intervalo (Date momento_inicial) {
        momento_inicial(momento_inicial);
    }

    public Intervalo (long milisegundos) {
        momento_inicial = new Date();
        momento_inicial(milisegundos);
    }




    public void momento_inicial (long milisegundos) {
        momento_inicial.setTime(milisegundos);
    }

    public void momento_inicial(Date fecha_hora) {
        momento_inicial = fecha_hora;
    }

    public void momento_final (long milisegundos) {
        momento_final.setTime(milisegundos);
    }

    public void momento_final (Date fecha_hora) {
        momento_final = fecha_hora;
        if (momento_inicial != null)
            resolver();
    }








    public long milisegundos;
    public long segundos;
    public long minutos;
    public long horas;
    public long dias;

    public Resolucion calcular = Resolucion.HORAS;

    public  void resolver () {
        this.diferencia = momento_final.getTime() - momento_inicial.getTime();

        long diferencia = this.diferencia;

        switch (calcular) {
            case DIAS:
                dias = diferencia / DIAS_EN_MILISEGUNDOS;
                diferencia = diferencia % DIAS_EN_MILISEGUNDOS;

            case HORAS:
                horas = diferencia / HORAS_EN_MILISEGUNDOS;
                diferencia = diferencia % HORAS_EN_MILISEGUNDOS;

            case MINUTOS:
                minutos = diferencia / MINUTOS_EN_MILISEGUNDOS;
                diferencia = diferencia % MINUTOS_EN_MILISEGUNDOS;

            case SEGUNDOS:
                segundos = diferencia / SEGUNDOS_EN_MILISEGUNDOS;
                diferencia = diferencia % SEGUNDOS_EN_MILISEGUNDOS;

            case MILISEGUNDOS:
                milisegundos = diferencia;
        }
    }


    public String cadena () {
        StringBuilder cadena = new StringBuilder();
        switch (calcular) {
            case DIAS:
                cadena.append(dias);
                cadena.append("d ");
            case HORAS:
                if (horas < 10)
                    cadena.append("0");
                cadena.append(horas);
                cadena.append(":");
            case MINUTOS:
                if (minutos < 10)
                    cadena.append("0");
                cadena.append(minutos);
                cadena.append(":");
            case SEGUNDOS:
                if (segundos < 10)
                    cadena.append("0");
                cadena.append(segundos);
        }
        return cadena.toString();
    }


    enum Resolucion {MILISEGUNDOS, SEGUNDOS, MINUTOS, HORAS, DIAS}

}
