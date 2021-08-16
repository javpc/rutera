package aplicacion.control;

import android.app.Activity;
import android.content.Intent;

import aplicacion.adaptador.Preferencias;
import aplicacion.actividad.ActividadPanel;
import aplicacion.vista.PantallaPrincipal;
import pc.javier.menulateralcompatible.R;
import utilidades.basico.MensajeRegistro;
import utilidades.control.Control;
import utilidades.imagen.AdaptadorImagen;
import utilidades.menu.MenuLateral;

public class ControlPantallaPrincipal extends Control {

    private Preferencias preferencias;
    private PantallaPrincipal pantalla;
    private MenuLateral menu;
    private AdaptadorImagen adaptadorImagen ;


    public ControlPantallaPrincipal (Activity actividad, MenuLateral menu) {
        super(actividad);
        preferencias = new Preferencias(actividad);
        pantalla = new PantallaPrincipal(actividad);
        this.menu = menu;

        habilitar_opciones_inicio (preferencias.sesion_activa());
        adaptadorImagen = new AdaptadorImagen(pantalla.getImagenIcono());


        if (preferencias.uri().isEmpty() == false)
            adaptadorImagen.uri(preferencias.uri());

    }



    public void iniciar() {
        if (preferencias.sesion_activa() == false)
            preferencias.sesion_activar();
        iniciarActividad(ActividadPanel.class);
        habilitar_opciones_inicio(true);
    }

    public void detener () {
        preferencias.sesion_desactivar();
        habilitar_opciones_inicio(false);
    }



    private void habilitar_opciones_inicio (boolean sesion_activa) {

        menu.setOpcionVisible(R.id.menu_iniciar, !sesion_activa);
        menu.setOpcionVisible(R.id.menu_detener, sesion_activa);

        pantalla.activar_boton (sesion_activa);
    }




    public void elegir_fotografia () {
        adaptadorImagen.elegirImagen(actividad);
    }


    public void onActivityResult(int codigo, int resultado, Intent dato){
        adaptadorImagen.onActivityResult(codigo, resultado, dato);
        if (adaptadorImagen.uri() != null)
            preferencias.uri(adaptadorImagen.uri().toString());
        MensajeRegistro.msj(adaptadorImagen.uri().toString());
    }
}

