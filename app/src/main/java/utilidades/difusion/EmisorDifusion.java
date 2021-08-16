package utilidades.difusion;

import android.content.Context;
import android.content.Intent;
/**
 * Javier 2021.
 *
 */

public final class EmisorDifusion {

    protected Context contexto;
    protected String firma = "pc.javier";
    protected String prefijo = "aplicacion";
    protected Intent intent = new Intent ();

    public EmisorDifusion (Context contexto, String aplicacion) {
        this.contexto = contexto;
        this.prefijo = aplicacion;
    }

    public void emitir (String nombre) {
        intent.setAction(firma + "." + prefijo + "." + nombre);
        contexto.sendBroadcast(intent);
    }

    public void extra (String nombre, String dato) {
        if (intent.hasExtra(nombre))
            intent.removeExtra(nombre);
        intent.putExtra (nombre, dato);
    }



    public void setPrefijo (String prefijo) {
        this.prefijo =  prefijo ;
    }

    public Intent getIntent () { return intent;}
}
