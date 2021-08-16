package aplicacion.control;

import android.app.Activity;

import org.osmdroid.views.MapView;

import java.util.ArrayList;

import aplicacion.adaptador.Coordenada;
import aplicacion.adaptador.Preferencias;
import aplicacion.bdd.BD;
import aplicacion.evento.ReceptorCoordenadasBDD;
import aplicacion.evento.ReceptorCoordenadasDifusion;
import aplicacion.evento.ReceptorCoordenadasMapa;
import aplicacion.vista.PanelTiempo;
import aplicacion.vista.PanelVelocidad;
import pc.javier.menulateralcompatible.R;
import utilidades.basico.FechaHora;
import utilidades.basico.MensajeRegistro;
import utilidades.localizacion_gps.Localizador;
import utilidades.localizacion_mapa.Mapa;
import utilidades.unidades.Velocidad;

public class ControlPantallaPanel {
    private PanelTiempo reloj;
    private PanelVelocidad velocimetro;
    private Activity actividad;
    private Preferencias preferencias;
    private Localizador localizador;
    private ReceptorCoordenadasMapa receptorCoordenadasMapa;
    private Mapa mapa;
    private BD bdd;
    private ReceptorCoordenadasBDD receptorCoordenadasBDD;
    private ReceptorCoordenadasDifusion receptorCoordenadasDifusion;
    private long sesion = -1;


    public ControlPantallaPanel(Activity actividad) {

        this.actividad = actividad;
        preferencias = new Preferencias(actividad);

        localizador = new Localizador(actividad);

        // instrumentos
        reloj = new PanelTiempo(actividad);
        velocimetro = new PanelVelocidad(actividad, localizador);

        // mapa
        mapa = new Mapa((MapView) actividad.findViewById(R.id.componente_mapa));
        receptorCoordenadasMapa = new ReceptorCoordenadasMapa(mapa, preferencias);
        localizador.agregarReceptor(receptorCoordenadasMapa);

        // bdd
        bdd = new BD(actividad);
        receptorCoordenadasBDD = new ReceptorCoordenadasBDD(bdd.getBdCoordenada(), preferencias.sesion_hora_inicio());
        localizador.agregarReceptor(receptorCoordenadasBDD);

        // difusión
        receptorCoordenadasDifusion = new ReceptorCoordenadasDifusion(actividad);
        localizador.agregarReceptor(receptorCoordenadasDifusion);

        sesion = preferencias.sesion_hora_inicio();


    }


    public void iniciar_paneles () {
        MensajeRegistro.msj("MARCAS", preferencias.obtenerFloat("latitud"));



        mapa.marcar (preferencias.obtenerFloat("latitud"), preferencias.obtenerFloat("longitud"));
        mapa.mover (preferencias.obtenerFloat("latitud"), preferencias.obtenerFloat("longitud"));

        localizador.activar();
        reloj.iniciar(preferencias.sesion_hora_inicio());
        velocimetro.iniciar ();
        dibujar_ruta(bdd.getBdCoordenada().coordenadas("sesion=" + String.valueOf(sesion) ));

    }
    public void detener_paneles () {
        if (localizador != null)
            localizador.desactivar();
        if (reloj != null)
            reloj.detener();
        if (velocimetro != null)
            velocimetro.detener ();

    }



    private void dibujar_ruta (ArrayList<Coordenada> listaCoordenadas) {
        FechaHora fechaHora = new FechaHora();
        mapa.borrar_pincel();
        for (Coordenada coordenada: listaCoordenadas ) {
            fechaHora.establecer(coordenada.getFechaHora());
            mapa.dibujar_linea(coordenada.getLatitud(), coordenada.getLongitud(),
                    fechaHora.obtenerHora()
                            + "\n" +
                            Velocidad.kmh_cadena(coordenada.getVelocidad()) + " km/h"
            );
            mapa.mover (coordenada.getLatitud(), coordenada.getLongitud());
        }
    }


}
