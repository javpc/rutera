package utilidades.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import utilidades.basico.MensajeRegistro;

/**
 * Javier 2019.
 * Conexión a la base de datos
 */
public class BDConexion extends SQLiteOpenHelper {


    // conexión a la base de datos de la aplicación
    private ArrayList<Tabla> tablas = new ArrayList<Tabla>();

    public BDConexion(Context context, ArrayList<Tabla> tablas, int version) {
        super(context, "BaseDeDatos", null, version);
        this.tablas = tablas;
        mensajeLog("<<<< constructor >>>");


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // sqLiteDatabase.execSQL(BDRegistro.TABLA);
        mensajeLog(" creando tablas");
        crearTablas(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        mensajeLog(" actualización");
        actualizarTablas (sqLiteDatabase);
    }






    private void crearTablas (SQLiteDatabase sqLiteDatabase) {
        for (Tabla tabla : tablas)
            sqLiteDatabase.execSQL(tabla.estructura);
    }

    private void actualizarTablas (SQLiteDatabase sqLiteDatabase) {

        // for (Tabla tabla : tablas)
//             sqLiteDatabase.execSQL(tabla.actualizacion);
    }



    private void mensajeLog (String texto) {
         MensajeRegistro.msj (this, texto);
    }

}
