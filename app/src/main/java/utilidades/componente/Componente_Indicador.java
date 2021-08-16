package utilidades.componente;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import pc.javier.menulateralcompatible.R;
import utilidades.vista.EditorVistas;

public class Componente_Indicador extends EditorVistas {

    public Componente_Indicador (View contenedor) {
        super (contenedor);
        //vistaPrincipal = contenedor;
    }

    public  Componente_Indicador (Activity actividad, int identificador) {
        super (actividad);
        vistaPrincipal = getView(identificador);
    }


    public TextView getVistaValor () {
        return getTextView(R.id.componente_indicador_valor);

    }
    public void setValor (String valor) {
        setTextView(R.id.componente_indicador_valor, valor);
    }

    public TextView getVistaUnidad () {
        return getTextView(R.id.componente_indicador_unidad);
    }

    public void setUnidad (String valor) {
        setTextView(R.id.componente_indicador_unidad, valor);
        setVisible(R.id.componente_indicador_unidad);
        setVisible(R.id.componente_indicador_espacio);
    }


    public TextView getVistaLeyenda () {
        return getTextView(R.id.componente_indicador_leyenda);
    }

    public void setLeyenda (String valor) {
        setTextView(R.id.componente_indicador_leyenda, valor);
        setVisible(R.id.componente_indicador_leyenda);
        setVisible(R.id.componente_indicador_espacio);
    }


    public void ocultar_leyenda_unidad () {
        setOculto(R.id.componente_indicador_leyenda);
        setOculto(R.id.componente_indicador_unidad);
        setOculto(R.id.componente_indicador_espacio);
    }

    public void mostrar_leyenda_unidad () {
        setVisible(R.id.componente_indicador_leyenda);
        setVisible(R.id.componente_indicador_unidad);
        setVisible(R.id.componente_indicador_espacio);
    }

}
