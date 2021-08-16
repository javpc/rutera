package utilidades.control;

import android.app.Activity;
import android.content.Intent;

import utilidades.vista.EditorVistas;


/**
 * Javier 2021.
 */


public abstract class Control {
    protected EditorVistas pantalla;
    protected Preferencias preferencias;
    protected Activity actividad;

    public Control (EditorVistas pantalla, Preferencias preferencias) {
        this.setPantalla(pantalla);
        this.setPreferencias(preferencias);
        actividad = pantalla.getActivity();
    }

    public Control (Activity activity) {
        this.actividad = activity;
        // pantalla = new EditorVistas(activity);
        // preferencias = new Preferencias(activity);
    }





    // actividades --------------------------------------------------------



    public Intent iniciarActividad(Class clase) {
        Intent intent = new Intent(actividad, clase);
        actividad.startActivity(intent);
        // contexto.overridePendingTransition(R.anim.zoom_entrada, R.anim.zoom_salida);
        return intent;
    }






    // servicios ------------------------------------------------------

    public void iniciarServicio (Class clase) {
        if (servicioActivo(clase))
            return;
        Intent servicio = new Intent(actividad, clase);
        actividad.startService(servicio);
    }




    public boolean servicioActivo (Class servicio) {
        return (new Servicios(actividad).activo(servicio));
    }


    public void detenerServicio (Intent intent) {
        actividad.stopService(intent);
    }

    public void detenerServicio (Class clase) {
        if (servicioActivo(clase) == false)
            return;
        Intent servicio = new Intent(actividad, clase);
        actividad.stopService(servicio);
    }


    // --------------------------------

    public void cerrarAplicacion () {
        // este codigo CIERRA LA APLICACION
        // fuente: https://jcristoballopez.wordpress.com/2015/03/20/anadir-boton-de-salida-con-android-studio/
        actividad.finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        actividad.startActivity(intent);
    }

    // gets / sets --------------------

    public EditorVistas getPantalla() {
        return pantalla;
    }

    public void setPantalla(EditorVistas pantalla) {
        this.pantalla = pantalla;
    }

    public Preferencias getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(Preferencias preferencias) {
        this.preferencias = preferencias;
    }





}
