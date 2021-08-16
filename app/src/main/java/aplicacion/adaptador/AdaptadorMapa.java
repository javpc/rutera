package aplicacion.adaptador;

import android.app.Activity;
import android.graphics.Color;

import org.osmdroid.views.MapView;

import java.util.ArrayList;

import pc.javier.menulateralcompatible.R;
import utilidades.basico.FechaHora;
import utilidades.basico.MensajeRegistro;
import utilidades.localizacion_mapa.Mapa;
import utilidades.unidades.Velocidad;

public class AdaptadorMapa {



    private Mapa mapa;


    public AdaptadorMapa (Activity actividad) {

        // Evita bloqueo
        //Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);

        mapa = new Mapa((MapView) actividad.findViewById(R.id.componente_mapa));


    }














    public void dibujar_ruta_velocidad (ArrayList<Coordenada> listaCoordenadas) {
        mapa.borrar_rutas ();

        float velocidad_maxima = 0;
        int velocidad_mayor_a_cero = 0;
        float velocidad_suma = 0;




        double norte = listaCoordenadas.get(0).getLatitud();
        double sur = norte;
        double este = listaCoordenadas.get(0).getLongitud();
        double oeste = este;


        for (Coordenada coordenada : listaCoordenadas) {
            if (coordenada.getVelocidad() > velocidad_maxima)
                velocidad_maxima = coordenada.getVelocidad();
            if (coordenada.getVelocidad()>0) {
                velocidad_mayor_a_cero++;
                velocidad_suma = velocidad_suma + coordenada.getVelocidad();
            }




            if (coordenada.getLongitud() < oeste)
                oeste = coordenada.getLongitud();

            if (coordenada.getLongitud() > este)
                este = coordenada.getLongitud();

            if (coordenada.getLatitud() < norte)
                norte = coordenada.getLatitud();

            if (coordenada.getLatitud() > sur)
                sur= coordenada.getLatitud();

        }

        float velocidad_promedio = velocidad_suma / velocidad_mayor_a_cero;


        int p_vel = 0;
        int color = Color.BLACK;



        for (Coordenada coordenada: listaCoordenadas ) {
            p_vel = (int) (coordenada.getVelocidad() * 255 / velocidad_maxima);

            //color = Color.rgb(150, 255,150);
            //if (coordenada.getVelocidad()> velocidad_promedio)
            //  color = Color.rgb(255,50,50);
            color = Color.rgb(0+p_vel,255 - p_vel,0);

            if (coordenada.getVelocidad() >= velocidad_maxima)
                color = Color.RED;

            mapa.dibujar_linea(coordenada.getLatitud(), coordenada.getLongitud(),
                    coordenada.getTexto(),
                    color

            );
            //mapa.mover_mapa(coordenada.getLatitud(), coordenada.getLongitud());
            //mapa.zoom(max.getLatitude(), max.getLongitude(), min.getLatitude(), min.getLongitude());


        }

        mapa.zoom (norte, este, sur, oeste);
        MensajeRegistro.msj(" zoom:" , " N" + norte  + " E" + este + " S" +  sur + " O" +  oeste );

    }





    public void dibujar_ruta (ArrayList<Coordenada> listaCoordenadas) {

        mapa.borrar_rutas ();





        FechaHora fechaHora = new FechaHora();
        for (Coordenada coordenada: listaCoordenadas ) {

            fechaHora.establecer(coordenada.getFechaHora());

            mapa.dibujar_linea(coordenada.getLatitud(), coordenada.getLongitud(),
                    fechaHora.obtenerHora()
                            + "\n" +
                            Velocidad.kmh_cadena(coordenada.getVelocidad()) + " km/h"

            );
            mapa.mover_mapa(coordenada.getLatitud(), coordenada.getLongitud());
        }
    }







}
