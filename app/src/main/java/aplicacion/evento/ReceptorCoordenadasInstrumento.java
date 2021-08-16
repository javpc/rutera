package aplicacion.evento;

import utilidades.eventos.Instrumento;
import utilidades.localizacion_gps.Coordenada;
import utilidades.unidades.Velocidad;

public class ReceptorCoordenadasInstrumento extends utilidades.localizacion_gps.ReceptorCoordenadas {

    private Instrumento instrumento;
    private Velocidad velocidad = new Velocidad();

    public ReceptorCoordenadasInstrumento(Instrumento instrumento) {
        this.instrumento = instrumento;
    }

        @Override
        public void procesar_coordenada (Coordenada coordenada) {
        // instrumento.texto(String.valueOf(coordenada.getVelocidad()));
            instrumento.texto(
                    Velocidad.kmh_cadena(coordenada.getVelocidad())
            );


    }
}
