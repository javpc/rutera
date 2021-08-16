package aplicacion.control;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import org.osmdroid.views.MapView;

import java.util.ArrayList;

import aplicacion.adaptador.Coordenada;
import aplicacion.adaptador.Pantalla;
import aplicacion.bdd.BD;
import aplicacion.vista.PantallaRegistro;
import pc.javier.menulateralcompatible.R;
import utilidades.basico.FechaHora;
import utilidades.control.Control;
import utilidades.control.Preferencias;
import utilidades.localizacion_mapa.Mapa;
import utilidades.unidades.Velocidad;

public class ControlPantallaRegistro extends Control {


    private PantallaRegistro pantalla;
    private Mapa mapa;
    private Context contexto;
    private long sesion = -1;


    public ControlPantallaRegistro(Activity activity, long sesion) {
        super(activity);

        this.sesion = sesion;
        this.contexto = activity.getApplicationContext();

        this.preferencias = new Preferencias(activity);
        this.pantalla = new PantallaRegistro (activity);


        // Evita bloqueo
        //Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);

        mapa = new Mapa((MapView) actividad.findViewById(R.id.componente_mapa));

        mapa.marcar ();
        cargarVista();
        // cargarListener();


    }





    // evento de click, cuando el usuario selecciona un elemento de la lista de coordenadas
    private void cargarListener () {
        pantalla.getLista().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adaptador, View view, int posicion, long id) {

                Coordenada coordenada = (Coordenada) adaptador.getItemAtPosition(posicion);
                mapa.mover (
                        coordenada.getLatitud(),
                        coordenada.getLongitud());

            }});
    }












    // carga el list view
    private void cargarVista () {
        ArrayList<Coordenada> listaCoordenadas = obtenerCoordenadas();
        // AdaptadorRegistro adaptador = new AdaptadorRegistro(contexto,  listaCoordenadas);
        // pantalla.getLista().setAdapter(adaptador);
        dibujar_ruta(listaCoordenadas);
    }








    // carga una lista de datos (registros) que servira para el list view
    // obtiene coordenadas de la base de datos
    private ArrayList<Coordenada> obtenerCoordenadas () {
        // base de datos
        BD bd = new BD(contexto);

        // obtiene las coordenadas de la base de datos
        ArrayList<Coordenada> listaCoordenada;
        if (sesion > 0)
            listaCoordenada = bd.getBdCoordenada().coordenadas("sesion=" + String.valueOf(sesion));
        else
            listaCoordenada = bd.getBdCoordenada().coordenadaObtenerTodas();


        bd.cerrar();

        // inicialmente
        // mueve la marca a la posicion conocida


        return listaCoordenada;
    }


    private void dibujar_ruta (ArrayList<Coordenada> listaCoordenadas) {
        float velocidad_maxima = 0;
        int velocidad_mayor_a_cero = 0;
        float velocidad_suma = 0;


        for (Coordenada coordenada : listaCoordenadas) {
            if (coordenada.getVelocidad() > velocidad_maxima)
                velocidad_maxima = coordenada.getVelocidad();
            if (coordenada.getVelocidad()>0) {
                velocidad_mayor_a_cero++;
                velocidad_suma = velocidad_suma + coordenada.getVelocidad();
            }
        }

        float velocidad_promedio = velocidad_suma / velocidad_mayor_a_cero;


        int p_vel = 0;
        int color = Color.BLACK;


        FechaHora fechaHora = new FechaHora();
        for (Coordenada coordenada: listaCoordenadas ) {
            p_vel = (int) (coordenada.getVelocidad() * 255 / velocidad_maxima);

            fechaHora.establecer(coordenada.getFechaHora());
            //color = Color.rgb(150, 255,150);
            //if (coordenada.getVelocidad()> velocidad_promedio)
              //  color = Color.rgb(255,50,50);
            color = Color.rgb(0+p_vel,255 - p_vel,0);

            if (coordenada.getVelocidad() >= velocidad_maxima)
                color = Color.RED;

            mapa.dibujar_linea(coordenada.getLatitud(), coordenada.getLongitud(),
                    fechaHora.obtenerHora()
                            + "\n" +
                    Velocidad.kmh_cadena(coordenada.getVelocidad()) + " km/h",
                    color

            );
            mapa.mover_mapa(coordenada.getLatitud(), coordenada.getLongitud());
        }
    }















    private class AdaptadorRegistro extends ArrayAdapter {
        private ArrayList<Coordenada> listaCoordenadas;

        public AdaptadorRegistro(Context context, ArrayList<Coordenada> listaCoordenadas) {
            super(context, R.layout.listable_coordenada, listaCoordenadas);
            this.listaCoordenadas = listaCoordenadas;
        }

        @Override
        public View getView(int posicion, View convertView, ViewGroup parent) {

            View elemento = pantalla.getActivity().getLayoutInflater().inflate(R.layout.listable_coordenada,null);

            Pantalla e = new Pantalla(elemento);

            Coordenada coordenada = listaCoordenadas.get(posicion);

            e.setTextView(R.id.registros_latitud, string(coordenada.getLatitud()));
            e.setTextView(R.id.registros_longitud, string(coordenada.getLongitud()));
            e.setTextView(R.id.registros_velocidad,string(coordenada.getVelocidad()));
            e.setTextView(R.id.registros_altitud,string(coordenada.getAltitud()));
            e.setTextView(R.id.registros_precision,coordenada.getPrecision());


            FechaHora fechaHora = new FechaHora(coordenada.getFechaHora());
            e.setTextView(R.id.registros_lista_fecha_hora, fechaHora.obtenerFechaHoraNormal());



            // Velocidad velocidad = new Velocidad(coordenada.getVelocidad());
            //e.setTextView(R.id.registros_velocidad, velocidad.kmhString());






            return elemento;
        }

        private String string (double valor) {
            return String.valueOf(valor);
        }

    }

}
