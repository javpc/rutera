package aplicacion.vista;

import android.app.Activity;
import android.widget.ListView;

import pc.javier.menulateralcompatible.R;
import utilidades.vista.EditorVistas;

public class PantallaRegistro extends EditorVistas {
    public PantallaRegistro (Activity actividad) {
        super(actividad);
    }
    public ListView getLista () { return getListView(R.id.registros_lista); }
}
