package aplicacion.actividad;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import aplicacion.control.ControlPantallaPanel;
import pc.javier.menulateralcompatible.R;


/**
 *  Javier  2021
 */

public class ActividadPanel extends AppCompatActivity {

    private ControlPantallaPanel control;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panel_mapa_velocidad_tiempo);

        control = new ControlPantallaPanel(this);
    }


    @Override
    public void onResume () {
        super.onResume();
        control.detener_paneles ();
        control.iniciar_paneles ();
    }

    @Override
    public void onDestroy () {
        control.detener_paneles();
        super.onDestroy();
    }



    /*

    // Menú superior ---------------
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.menu_regresiva, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        super.onOptionsItemSelected(item);
        control.menu (item);
        return true;
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        control.onActivityResult (requestCode, resultCode, data);
    }

    public void clickBoton (View view) { control.iniciarCuentaRegresiva(); }

    */

    private void mensajeLog (String texto) {
        Log.d("Actividad Regresiva", texto);
    }


}
