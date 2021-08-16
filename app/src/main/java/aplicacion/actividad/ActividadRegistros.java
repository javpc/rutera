package aplicacion.actividad;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import aplicacion.control.ControlPantallaRegistro;
import pc.javier.menulateralcompatible.R;

public class ActividadRegistros extends AppCompatActivity {

    private ControlPantallaRegistro control;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_registro_datos);



        long sesion = getIntent().getLongExtra("sesion", -1);

        control = new ControlPantallaRegistro(this, sesion);
    }


    @Override
    public void onResume () {
        super.onResume();

    }

    @Override
    public void onDestroy () {

        super.onDestroy();
    }







}
