package aplicacion.adaptador;

import android.app.Activity;
import android.content.Context;

import java.util.Date;

public class Preferencias extends utilidades.control.Preferencias {
    public Preferencias (Context contexto) {
        super(contexto);
    }


    public void sesion_activar () {
        guardarLong("inicio", ((new Date()).getTime()));
    }

    public void sesion_desactivar () {
        guardarLong("inicio", 0);
    }

    public boolean sesion_activa () {
        return obtenerLong("inicio") != 0;
    }

    public long sesion_hora_inicio () {
        return obtenerLong("inicio");
    }



    public void uri (String valor) { guardar("uri", valor);}
    public String uri () { return obtenerString("uri") ; }



    public void set_clima_descripcion (String descripcion) {guardar ("descripcion", descripcion); }
    public void set_clima_icono (String icono) {guardar ("icono", icono); }
    public void set_clima_temperatura (String temperatura) {guardar ("temperatura", temperatura); }
    public void set_clima_viento (String viento) {guardar ("viento", viento); }
    public void set_clima_viento_rafaga (String viento_rafaga) {guardar ("viento_rafaga", viento_rafaga); }
    public void set_clima_viento_direccion (String viento_direccion) {guardar ("viento_direccion", viento_direccion); }
    public void set_clima_nubosidad (String nubosidad) {guardar ("nubosidad", nubosidad); }


    public String get_clima_descripcion () { return obtenerString ("descripcion"); }
    public String get_clima_icono () { return obtenerString ("icono"); }
    public String get_clima_temperatura () { return obtenerString ("temperatura"); }
    public String get_clima_viento () { return obtenerString ("viento"); }
    public String get_clima_viento_rafaga () { return obtenerString ("viento_rafaga"); }
    public String get_clima_viento_direccion () { return obtenerString ("viento_direccion"); }
    public String get_clima_nubosidad () { return obtenerString ("nubosidad"); }



}
