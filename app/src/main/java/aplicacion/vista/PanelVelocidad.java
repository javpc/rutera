package aplicacion.vista;

import android.app.Activity;
import android.graphics.Typeface;
import android.widget.TextView;

import aplicacion.adaptador.Pantalla;
import aplicacion.evento.ReceptorCoordenadasInstrumento;
import utilidades.componente.Componente_Indicador;
import utilidades.eventos.Instrumento;
import utilidades.localizacion_gps.Localizador;


public class PanelVelocidad {
    private Instrumento instrumento;

    private ReceptorCoordenadasInstrumento receptorCoordenadas;

    public PanelVelocidad (Activity actividad, Localizador localizador) {
        super();


        Componente_Indicador indicador = new Componente_Indicador(actividad, Pantalla.vista_velocidad);
        TextView textView = indicador.getVistaValor();
        indicador.setUnidad("km/h");

        instrumento = new Instrumento(textView);


        instrumento = new Instrumento(textView);

        receptorCoordenadas = new ReceptorCoordenadasInstrumento(instrumento);
        localizador.agregarReceptor(receptorCoordenadas);



        Typeface typeface= Typeface.createFromAsset(actividad.getAssets(), "font/digital_7_mono_italic.ttf");
        textView.setTypeface(typeface);
        textView.setTextSize(100);

    }




    public void iniciar () {

    }

    public void detener ()  {

    }





}
