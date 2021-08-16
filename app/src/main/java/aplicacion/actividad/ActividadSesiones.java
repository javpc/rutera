package aplicacion.actividad;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

import aplicacion.control.ControlPantallaSesion;
import pc.javier.menulateralcompatible.R;

public class ActividadSesiones extends AppCompatActivity {

    private ControlPantallaSesion control;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_sesiones);

        control = new ControlPantallaSesion (this);
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroy() {

        super.onDestroy();
    }


    public void mapa (View v) {
        //control.seleccionar_sesion();
    }

    public void lista (View v) {
        control.mostrar_lista();
    }
}