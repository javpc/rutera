package aplicacion.evento;

import aplicacion.bdd.BDCoordenada;
import utilidades.basico.MensajeRegistro;
import utilidades.localizacion_gps.Coordenada;
import utilidades.localizacion_gps.ReceptorCoordenadas;

public class ReceptorCoordenadasBDD extends ReceptorCoordenadas {


    private  BDCoordenada bdCoordenada;
    private long sesion;



    public ReceptorCoordenadasBDD(BDCoordenada bdCoordenada, long sesion) {
        super();
        this.bdCoordenada = bdCoordenada;
        this.sesion = sesion;
    }
    @Override
    protected void procesar_coordenada(Coordenada coordenada) {

        coordenada.setFechaHora();
        bdCoordenada.insertar(coordenada, sesion);
        MensajeRegistro.msj( "RECEPTOR BD:", "Nueva coordenada " + sesion);
    }

}
