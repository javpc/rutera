package aplicacion.evento;

import aplicacion.adaptador.Preferencias;
import utilidades.localizacion_gps.Coordenada;
import utilidades.localizacion_gps.ReceptorCoordenadas;
import utilidades.localizacion_mapa.Mapa;

public class ReceptorCoordenadasMapa extends ReceptorCoordenadas {
    private Mapa mapa;
    private Preferencias preferencias;
    public ReceptorCoordenadasMapa(Mapa mapa, Preferencias preferencias) {
        this.mapa = mapa;
        this.preferencias = preferencias;
    }
    @Override
    public void procesar_coordenada (Coordenada coordenada) {
        mapa.dibujar_linea(coordenada.getLatitud(), coordenada.getLongitud() );
        mapa.mover (coordenada.getLatitud(), coordenada.getLongitud() );

        preferencias.guardar ("latitud", (float) mapa.getPunto_marca().getLatitude());
        preferencias.guardar ("longitud", (float) mapa.getPunto_marca().getLongitude());

    }
}
