package aplicacion.evento;

import android.content.Context;

import utilidades.difusion.EmisorDifusion;
import utilidades.localizacion_gps.Coordenada;
import utilidades.localizacion_gps.ReceptorCoordenadas;

public class ReceptorCoordenadasDifusion extends ReceptorCoordenadas {

    private Context contexto;
    private EmisorDifusion difusion;

    public ReceptorCoordenadasDifusion (Context contexto) {
        this.contexto = contexto;
    }

    @Override
    protected void procesar_coordenada(Coordenada coordenada) {

        this.difusion = new EmisorDifusion(contexto, "rutera");


        agregar_informacion("latitud", coordenada.getLatitud());
        agregar_informacion("longitud", coordenada.getLongitud());
        agregar_informacion ("velocidad", coordenada.getVelocidad());

        difusion.emitir("coordenada");

    }

    private void agregar_informacion (String clave, double valor) {
        difusion.extra(clave, String.valueOf(valor));
    }
}
