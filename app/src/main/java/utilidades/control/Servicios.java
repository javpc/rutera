package utilidades.control;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;


import java.util.List;

/**
 * Javier 2021
 */

public class Servicios {

    private Context contexto;
    private Activity actividad;

    // verifica si el servicio esta activo
    public Servicios (Context contexto) {
        this.contexto = contexto;
    }

    public Servicios (Activity actividad) {
        this.actividad = actividad;
        this.contexto = actividad;
    }


    public List<ActivityManager.RunningServiceInfo> listaServicios () {
        // obtiene una lista de servicios
        return ((ActivityManager) contexto.getSystemService(contexto.ACTIVITY_SERVICE)).getRunningServices(Integer.MAX_VALUE);
    }

    public boolean activo(Class servicio) {
        // recorre la lista buscando la clase
        for (ActivityManager.RunningServiceInfo s: listaServicios())
            if(s.service.getClassName().equals(servicio.getName()))
                return true;

        return false;
    }



    public void detenerServicio (Intent intent) {
        actividad.stopService(intent);
    }

    public void detenerServicio (Class clase) {
        if (activo (clase) == false)
            return;
        Intent servicio = new Intent(actividad, clase);
        actividad.stopService(servicio);
    }



    public void iniciarServicio (Class clase) {
        if (activo(clase))
            return;
        Intent servicio = new Intent(actividad, clase);
        actividad.startService(servicio);
    }



}
