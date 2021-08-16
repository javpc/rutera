package aplicacion.vista;

import android.app.Activity;
import android.widget.ImageView;

import pc.javier.menulateralcompatible.R;
import utilidades.vista.EditorVistas;

public class PantallaPrincipal extends EditorVistas {
    public PantallaPrincipal (Activity actividad) {
        super(actividad);
    }

    public void activar_boton (boolean activo) {
        /*
        if (activo)
            setButtonText(R.id.boton_prinicipal_activar, "CONTINUAR");
        else
            setButtonText(R.id.boton_prinicipal_activar, "INICIAR");

         */
    }


    public ImageView getImagenIcono () {
        return getImageView(R.id.imageView);
    }


}
