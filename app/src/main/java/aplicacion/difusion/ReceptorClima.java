package aplicacion.difusion;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import aplicacion.adaptador.Preferencias;

public class ReceptorClima extends BroadcastReceiver {
    @Override
    public void onReceive(Context contexto, Intent intent) {
        // String cadena_json = intent.getStringExtra("json");

        Preferencias preferencias = new Preferencias(contexto);

        if (intent.hasExtra ("descripcion"))
            preferencias.set_clima_descripcion ( intent.getStringExtra ("descripcion")) ;

        if (intent.hasExtra ("icono"))
            preferencias.set_clima_icono ( intent.getStringExtra ("icono")) ;

        if (intent.hasExtra ("temperatura"))
            preferencias.set_clima_temperatura ( intent.getStringExtra ("temperatura")) ;

        if (intent.hasExtra ("viento"))
            preferencias.set_clima_viento ( intent.getStringExtra ("viento")) ;

        if (intent.hasExtra ("viento_rafaga"))
            preferencias.set_clima_viento_rafaga ( intent.getStringExtra ("viento_rafaga")) ;

        if (intent.hasExtra ("viento_direccion"))
            preferencias.set_clima_viento_direccion ( intent.getStringExtra ("viento_direccion")) ;

        if (intent.hasExtra ("nubosidad"))
            preferencias.set_clima_nubosidad ( intent.getStringExtra ("nubosidad")) ;



    }
}
