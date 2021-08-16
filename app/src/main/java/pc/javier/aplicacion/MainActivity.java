package pc.javier.aplicacion;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import aplicacion.actividad.ActividadAyuda;
import aplicacion.actividad.ActividadSesiones;
import aplicacion.control.ControlPantallaPrincipal;
import pc.javier.menulateralcompatible.R;
import utilidades.basico.EnlaceExterno;
import utilidades.imagen.AdaptadorImagen;
import utilidades.menu.MenuLateral;
import utilidades.menu.PantallaPrincipalConMenuLateral;


public class MainActivity extends PantallaPrincipalConMenuLateral {


    private ControlPantallaPrincipal control;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setContentView(R.layout.principal_pantalla);



        menuLateral = new MenuLateral(this, R.id.panel_layout, R.id.panel_view);

        control = new ControlPantallaPrincipal(this, menuLateral);

    }

    @Override
    public void onResume () {
        super.onResume();



    }

    @Override
    public void onDestroy () {
        super.onDestroy();

    }






    public void clic_iniciar (View v) {
        // control.iniciar_detener();
        control.iniciar();

    }



    // Menú lateral -----------------------------------------------------------------

    @Override
    public void clicMenu (MenuItem item) {
        super.clicMenu(item);


        switch (item.getItemId()) {

            case R.id.menu_iniciar:
                control.iniciar();
                break;


            case R.id.menu_detener:
                control.detener();
                break;

            case R.id.menu_registros:
                control.iniciarActividad(ActividadSesiones.class);
                break;

            case R.id.menu_fotografia:
                control.elegir_fotografia ();
                break;

            case R.id.menu_proyectos:
                abrir_enlace(Aplicacion.telegram);
                break;

            case R.id.menu_donar:
                abrir_enlace(Aplicacion.donacion);
                break;


            case R.id.menu_ayuda:
                control.iniciarActividad(ActividadAyuda.class);
                break;
        }


    }


    public void clicBoton (View v) {
        menuLateral.alternar();
    }


    private void abrir_enlace (String enlace){
         (new EnlaceExterno(this)).abrirEnlace(enlace);
    }



    public void fab (View v) {
        abrir_enlace(Aplicacion.donacion);
    }



    @Override
    public void onActivityResult(int codigo, int resultado, Intent dato) {

        control.onActivityResult(codigo, resultado, dato);
        super.onActivityResult(codigo, resultado, dato);
    }

}
