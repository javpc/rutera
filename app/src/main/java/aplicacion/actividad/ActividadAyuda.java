package aplicacion.actividad;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;

import pc.javier.aplicacion.Aplicacion;
import pc.javier.menulateralcompatible.BuildConfig;
import pc.javier.menulateralcompatible.R;
import utilidades.basico.EnlaceExterno;

public class ActividadAyuda extends AppCompatActivity {


    public final static String version = BuildConfig.VERSION_NAME ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_ayuda);

        TextView texto_version = (TextView) findViewById(R.id.version);
        texto_version.setText("Version: " + this.version);


    }


    @Override
    public void onResume () {
        super.onResume();

    }

    @Override
    public void onDestroy () {

        super.onDestroy();
    }



    public void sitio (View v) {
        abrir_enlace(Aplicacion.telegram);
    }



    private void abrir_enlace (String enlace){
        (new EnlaceExterno(this)).abrirEnlace(enlace);
    }

}
