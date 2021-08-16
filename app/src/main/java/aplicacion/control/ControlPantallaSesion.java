package aplicacion.control;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

import aplicacion.adaptador.AdaptadorMapa;
import aplicacion.adaptador.Coordenada;
import aplicacion.adaptador.Pantalla;
import aplicacion.adaptador.Preferencias;
import aplicacion.bdd.BD;
import aplicacion.vista.PantallaSesion;
import pc.javier.menulateralcompatible.R;
import utilidades.basico.FechaHora;
import utilidades.basico.Intervalo;
import utilidades.control.Control;

import utilidades.imagen.AdaptadorImagen;
import utilidades.unidades.Medida;
import utilidades.unidades.Velocidad;

public class ControlPantallaSesion extends Control {

    private PantallaSesion pantalla;

    private AdaptadorMapa mapa_adaptador;

    private AdaptadorImagen adaptadorImagen;

    private Preferencias preferencias;


    public ControlPantallaSesion(Activity actividad) {
        super(actividad);




        this.preferencias   = new Preferencias(actividad);
        this.pantalla       = new PantallaSesion (actividad);
        this.mapa_adaptador = new AdaptadorMapa(actividad);

        adaptadorImagen = new AdaptadorImagen(pantalla.getImageViewPerfil ());
        if (preferencias.uri().isEmpty() == false)
            adaptadorImagen.uri(preferencias.uri());

        cargar_lista();
        cargarListener();


    }





    // evento de click, cuando el usuario selecciona un elemento de la lista de coordenadas
    private void cargarListener () {
        pantalla.getLista().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adaptador, View view, int posicion, long id) {

                Coordenada coordenada = (Coordenada) adaptador.getItemAtPosition(posicion);
                //seleccionar_sesion (coordenada.getSesion());
                mostrar_detalles(coordenada.getSesion());

            }});
    }




    private void borrar_sesion (long sesion) {
        // base de datos
        BD bd = new BD(actividad);

        // obtiene las coordenadas de la base de datos
        bd.getBdCoordenada().borrar_sesion (sesion);

        bd.cerrar();

        cargar_lista();
        FechaHora fechaHora = new FechaHora(sesion);
        pantalla.bocadillo("Sesión eliminada: " + fechaHora.obtenerFechaNormal());
    }

    /*
    public void seleccionar_sesion () {
        if (sesion <0)
            return;
            Intent intent = new Intent(actividad, ActividadRegistros.class);
            intent.putExtra("sesion", sesion);
            actividad.startActivity(intent);

    }

     */


    public void mostrar_lista () {
        pantalla.mostrar_ocultar_lista();
    }

    private void mostrar_detalles (long sesion) {
        //this.sesion = sesion;
        ArrayList<Coordenada> coordenadas = obtenerSesion (sesion);

        Coordenada primer_coordenada = coordenadas.get (0);
        Coordenada ultima_coordenada = coordenadas.get(coordenadas.size()-1);

        FechaHora fechaHora_ini = new FechaHora(primer_coordenada.getFechaHora());
        FechaHora fechaHora_fin = new FechaHora(ultima_coordenada.getFechaHora());

        Intervalo intervalo = new Intervalo();
        intervalo.momento_inicial(primer_coordenada.getFechaHora());


        float velocidad_maxima = 0;
        int velocidad_mayor_a_cero = 0;
        float velocidad_suma = 0;

        double distancia = 0;
        double latitud_anterior = coordenadas.get(0).getLatitud();
        double longitud_anterior = coordenadas.get(0).getLongitud();
        GeoPoint geo_anterior = new GeoPoint(latitud_anterior, longitud_anterior);
        GeoPoint geo = new GeoPoint(latitud_anterior, longitud_anterior);
        FechaHora fechaHora = new FechaHora();

        for (Coordenada coordenada : coordenadas) {
            if (coordenada.getVelocidad() > velocidad_maxima)
                velocidad_maxima = coordenada.getVelocidad();
            if (coordenada.getVelocidad()>0) {
                velocidad_mayor_a_cero++;
                velocidad_suma = velocidad_suma + coordenada.getVelocidad();
            }

            geo.setLatitude(coordenada.getLatitud());
            geo.setLongitude(coordenada.getLongitud());
            distancia = distancia + geo.distanceToAsDouble(geo_anterior);
            geo_anterior.setLatitude(coordenada.getLatitud());
            geo_anterior.setLongitude(coordenada.getLongitud());

            fechaHora.establecer(coordenada.getFechaHora());
            intervalo.momento_final(coordenada.getFechaHora());

            coordenada.setTexto(
                    fechaHora.obtenerHora()
                            + "\n" +
                            intervalo.cadena()
                            + "\n" +
                            Velocidad.kmh_cadena(coordenada.getVelocidad()) + " km/h"
                            + "\n" +
                            Medida.mts_kms_cadena(distancia) + "km"

            );
        }


        float velocidad_promedio = velocidad_suma / velocidad_mayor_a_cero;


        intervalo.momento_final(ultima_coordenada.getFechaHora());
        pantalla.set_duracion(intervalo.cadena());

        pantalla.set_distancia(Medida.mts_kms_cadena(distancia));


        pantalla.set_velocidad_pro(Velocidad.kmh_cadena(velocidad_promedio));
        pantalla.set_velocidad_max(Velocidad.kmh_cadena(velocidad_maxima));

        //pantalla.mostrar_boton();


        mapa_adaptador.dibujar_ruta_velocidad(coordenadas);

    }






    // carga el list view
    private void cargar_lista() {
        ArrayList<Coordenada> listaCoordenadas = obtenerSesiones();
        ControlPantallaSesion.AdaptadorSesion adaptador = new ControlPantallaSesion.AdaptadorSesion(actividad,  listaCoordenadas);
        pantalla.getLista().setAdapter(adaptador);
    }








    // carga una lista de datos (registros) que servira para el list view
    // obtiene coordenadas de la base de datos
    private ArrayList<Coordenada> obtenerSesiones () {
        // base de datos
        BD bd = new BD(actividad);

        // obtiene las coordenadas de la base de datos
        ArrayList<Coordenada> listaCoordenada = bd.getBdCoordenada().obtenerSesiones();

        bd.cerrar();


        return listaCoordenada;
    }



    private ArrayList<Coordenada> obtenerSesion (long sesion) {
        // base de datos
        BD bd = new BD(actividad);

        // obtiene las coordenadas de la base de datos
        ArrayList<Coordenada> listaCoordenada = bd.getBdCoordenada().obtenerSesion(sesion);

        bd.cerrar();



        return listaCoordenada;
    }














    private class AdaptadorSesion extends ArrayAdapter {
        private ArrayList<Coordenada> listaCoordenadas;

        public AdaptadorSesion (Context context, ArrayList<Coordenada> listaCoordenadas) {
            super(context, R.layout.listable_fecha_hora, listaCoordenadas);
            this.listaCoordenadas = listaCoordenadas;
        }

        @Override
        public View getView(int posicion, View convertView, ViewGroup parent) {

            View elemento = pantalla.getActivity().getLayoutInflater().inflate(R.layout.listable_fecha_hora,null);

            Pantalla e = new Pantalla(elemento);

            final Coordenada coordenada = listaCoordenadas.get(posicion);


            FechaHora fechaHora = new FechaHora(coordenada.getFechaHora());
            e.setTextView(R.id.registros_lista_fecha_hora, fechaHora.obtenerFechaNormal());



            // Velocidad velocidad = new Velocidad(coordenada.getVelocidad());
            //e.setTextView(R.id.registros_velocidad, velocidad.kmhString());



            e.getView(R.id.registros_lista_borrar).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    borrar_sesion (coordenada.getSesion());
                }
            });





            return elemento;
        }

        private String string (double valor) {
            return String.valueOf(valor);
        }

    }

}
