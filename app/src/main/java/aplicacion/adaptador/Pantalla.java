package aplicacion.adaptador;

import android.app.Activity;
import android.view.View;

import pc.javier.menulateralcompatible.R;
import utilidades.vista.EditorVistas;

public class Pantalla extends EditorVistas {


    public static final int vista_hora = R.id.fechaHora;
    public static final int vista_velocidad = R.id.velocidad;

    public Pantalla(Activity activity) {
        super(activity);
    }

    public Pantalla (View vista) {
        super(vista);
    }




}
