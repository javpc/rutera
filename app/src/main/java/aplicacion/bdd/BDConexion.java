package aplicacion.bdd;

import android.content.Context;

import java.util.ArrayList;

import utilidades.bdd.Tabla;

/**
 *
 * conexión a la base de datos
 */
public class BDConexion extends utilidades.bdd.BDConexion {


    // conexión a la base de datos de la aplicación

    public BDConexion (Context context, ArrayList<Tabla> tablas, int version)  {
        super(context, tablas,   1);
    }




}
