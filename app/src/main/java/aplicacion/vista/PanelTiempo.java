package aplicacion.vista;

import android.app.Activity;
import android.widget.TextView;

import aplicacion.adaptador.Pantalla;
import utilidades.basico.FechaHora;
import utilidades.basico.Intervalo;
import utilidades.basico.Temporizador;
import utilidades.componente.Componente_Indicador;
import utilidades.eventos.Instrumento;

public class PanelTiempo {


    private Instrumento instrumento;
    private Temporizador temporizador;
    private FechaHora fecha_hora;
    private Intervalo tiempo_transcurrido;



    public PanelTiempo (Activity actividad) {
        super();

        Componente_Indicador indicador = new Componente_Indicador(actividad, Pantalla.vista_hora);
        TextView textView = indicador.getVistaValor();

        instrumento = new Instrumento(textView);

        indicador.ocultar_leyenda_unidad();
        //Typeface typeface= Typeface.createFromAsset(actividad.getAssets(), "font/digital_7_mono_italic.ttf");
        //textView.setTypeface(typeface);

    }



    public void iniciar (long milisegundos) {
        fecha_hora = new FechaHora();
        tiempo_transcurrido = new Intervalo(milisegundos);

        temporizador = new Temporizador() {
            @Override
            public void ejecutar () {
                fecha_hora.ahora();
                tiempo_transcurrido.momento_final(fecha_hora.obtener());
                tiempo_transcurrido.resolver();
                instrumento.texto( tiempo_transcurrido.cadena() );
                // instrumento.texto( fecha_hora_inicio.cuenta_regresiva().obtenerHora());


            }
        };
        temporizador.setIntervalo(1);
        temporizador.iniciar();


    }

    public void detener ()  {
        if (temporizador != null)
            temporizador.detener();
    }






}
