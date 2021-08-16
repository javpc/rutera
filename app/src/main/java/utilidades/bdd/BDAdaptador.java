package utilidades.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import utilidades.basico.MensajeRegistro;


/**
 * Javier 2021.
 */

public abstract class BDAdaptador {

    private BDConexion conexion;
    protected SQLiteDatabase sqLiteDatabase;


    // almacena las tablas que componen la base de datos
    private ArrayList<Tabla> tablas = new ArrayList<Tabla>();


    public BDAdaptador (Context contexto) {

        mensajeLog("Constructor adaptador");
        conexion = new BDConexion(contexto, tablas, 1);
        //abrir();

    }


    public BDAdaptador (Context contexto, int version) {

        mensajeLog("Constructor adaptador");
        conexion = new BDConexion(contexto, tablas, version);
        //abrir();

    }




    // Base de datos  -----------------------------------------



    public void abrir () {
        sqLiteDatabase = conexion.getWritableDatabase();
        asignarSqlATablas();
    }

    public void cerrar () {
        sqLiteDatabase.close();
    }



    // asignar a cada uno de los manejadores el objeto sql
    // BDCoordenada.setSql (sql);

    protected void asignarSqlATablas() {
        mensajeLog("asignando sql...");
        for (Tabla tabla: tablas ) {
            tabla.setSql(sqLiteDatabase);
            mensajeLog("SQL ASIGNADO " + tabla.nombre());
        }


    }





    protected void agregarTabla (Tabla tabla) {
        tablas.add(tabla);
    }














    private void mensajeLog (String texto) {
         MensajeRegistro.msj (this, texto);
    }

}
