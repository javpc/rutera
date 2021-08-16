package aplicacion.vista;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.ListView;

import aplicacion.adaptador.Pantalla;
import pc.javier.menulateralcompatible.R;
import utilidades.vista.EditorVistas;

public class PantallaSesion extends EditorVistas {
    public PantallaSesion (Activity actividad) {
        super (actividad);
    }

    public ListView getLista () { return getListView(R.id.registros_lista); }

    public void mostrar_ocultar_lista () {
        intercambiar_visibilidad(R.id.registros_lista);
    }

    public void set_duracion (String valor) {
        Pantalla p = new Pantalla(getView(R.id.detale_duracion));
        p.setTextView(R.id.componente_indicador_valor, valor);
        leyenda(p,"dur");
    }


    public void set_distancia(String valor) {
        Pantalla p = new Pantalla(getView(R.id.detalle_distancia));
        p.setTextView(R.id.componente_indicador_valor, valor);
        leyenda(p,"km ");
    }

    public void set_fin (String valor) {
        Pantalla p = new Pantalla(getView(R.id.detale_fin));
        p.setTextView(R.id.componente_indicador_valor, valor);
        leyenda(p,"fin");
    }



    public void set_velocidad_max (String valor) {
        Pantalla p = new Pantalla(getView(R.id.detalle_velocidad_maxima));
        p.setTextView(R.id.componente_indicador_valor, valor);
        leyenda(p,"max");
        unidad(p, "km/h");
    }


    public void set_velocidad_pro (String valor) {
        Pantalla p = new Pantalla(getView(R.id.detalle_velocidad_promedio));
        p.setTextView(R.id.componente_indicador_valor, valor);
        leyenda(p,"med");
        unidad(p, "km/h");

    }



    private void leyenda (Pantalla componente, String valor) {
        componente.setTextView(R.id.componente_indicador_leyenda, valor);
        componente.setVisible(R.id.componente_indicador_leyenda);
        componente.setVisible(R.id.componente_indicador_espacio);
    }

    private void unidad (Pantalla componente, String valor) {
        componente.setTextView(R.id.componente_indicador_unidad, valor);
        componente.setVisible(R.id.componente_indicador_unidad);
        componente.setVisible(R.id.componente_indicador_espacio);
    }


    /*
    public void mostrar_boton () {
        //setVisible(R.id.boton_sesion_flotante);
    }

     */

    public ImageView getImageViewPerfil () { return getImageView(R.id.imageView); }
}
