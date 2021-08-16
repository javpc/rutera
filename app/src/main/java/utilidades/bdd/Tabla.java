package utilidades.bdd;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import utilidades.basico.MensajeRegistro;

/**
 * Javier 2019.
 *
 *  Posee metodos comunes para simplificar las clases que manejan bases de datos
 */

public abstract class Tabla {




    // -------------------------------------------------------------------------------

    // Base de datos

    // -------------------------------------------------------------------------------



    // nombre de la tabla
    protected String nombre;

    // columna identificadora
    protected String columnaIdentificadora = "id";


    protected String estructura = "";

    protected String actualizacion = "";



    protected SQLiteDatabase sql;



    public Tabla (String nombre, SQLiteDatabase sqLiteDatabase) {
        this.nombre = nombre;
        this.sql = sqLiteDatabase;
    }

    public void setSql (SQLiteDatabase sqLiteDatabase) {
        sql = sqLiteDatabase;
    }


    public Tabla(String nombre) {
        this.nombre = nombre;
    }

    protected Cursor cursor;



    public void columnaIdentificadora (String valor) {
        columnaIdentificadora = valor;
    }

    public String nombre () { return nombre; }

    public String getEstructura() {
        return estructura;
    }

    public void setEstructura(String estructura) {
        this.estructura = estructura;
    }

    public String getActualizacion() {
        return actualizacion;
    }

    public void setActualizacion(String actualizacion) {
        this.actualizacion = actualizacion;
    }


    public Cursor cursor () { return  cursor; }


    protected ContentValues contenedor = new ContentValues();





    // ------------ Métodos comunes ----------------------------------------------


    // -------------------------------------------------------------------------------
    // Insertar
    // -------------------------------------------------------------------------------

    public boolean insertar(String parametro, int valor) {
        ContentValues valores = new ContentValues();
        valores.put(parametro, valor);
        return  insertar(valores);
    }

    public boolean insertar(String parametro, String valor) {
        ContentValues valores = new ContentValues();
        valores.put(parametro, valor);
        return  insertar(valores);
    }

    public boolean insertar(ContentValues valores) {
        if (sql == null)
            mensajeLog("¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡¡ SQL NULO !!!!!!!!!!!!!!!!!!!!!!!!!!!");
        int respuesta = (int) sql.insert(nombre,null, valores);
        valores.clear();
        return  (respuesta > 0);
    }





    // -------------------------------------------------------------------------------
    // Actualizar
    // -------------------------------------------------------------------------------




    public void actualizar (int id, String columna, String valor) {
        ContentValues valores = new ContentValues();
        valores.put(columna, valor);
        actualizar(id, valores);
    }

    public void actualizar (int id, String columna, int valor) {
        ContentValues valores = new ContentValues();
        valores.put(columna, valor);
        actualizar(id, valores);
    }



    public void actualizar (int id, ContentValues valores) {
        actualizar(columnaIdentificadora + " = " + id, valores);
        valores.clear();
    }


    public void actualizar (String condicion, ContentValues valores) {
        sql.update(nombre, valores,condicion, null);
        mensajeLog ("Tupla Actualizada  " + condicion );
        valores.clear();
    }


    // -------------------------------------------------------------------------------
    // Eliminar
    // -------------------------------------------------------------------------------
    public boolean eliminar (String condicion) {
        return (sql.delete(nombre,condicion, null) > 0);
    }

    public boolean eliminar (int id) {
        return (sql.delete(nombre,columnaIdentificadora + " = " + id, null) > 0);
    }

    //elimina ttodo el contenido
    public boolean eliminar () {
        return (sql.delete(nombre,null, null) > 0);
    }





    // -------------------------------------------------------------------------------
    // Seleccionar
    // -------------------------------------------------------------------------------



    public Cursor seleccionar () {
        //cursor = sql.rawQuery("*",null);
        //cursor= sql.query(nombre,new String[]{"*"},null,null,null,null, null);
        cursor = sql.query(nombre,new String[]{"*"},null,null,null,null,null);
        return cursor;
    }

    public Cursor seleccionar (int id) {
        return seleccionar("id=" + String.valueOf(id));
    }

    public Cursor seleccionar (String condicion) {
        cursor = sql.query(nombre,new String[]{"*"},condicion,null,null,null,null, null);
        // cursor = sql.rawQuery("SELECT * FROM " + nombre + " WHERE ?" , new String[] { condicion });
        return cursor;
    }


    public Cursor seleccionar (String condicion, String grupo) {
        cursor = sql.query(nombre,new String[]{"*"},condicion,null,grupo,null,null, null);
        // cursor = sql.rawQuery("SELECT * FROM " + nombre + " WHERE ?" , new String[] { condicion });
        return cursor;
    }


    public Cursor seleccionarPrimero () {
        return seleccionarPrimeros(1);
    }

    public Cursor seleccionarPrimeros (int cantidad) {
        //Cursor c = sql.rawQuery("*",null);
        //Cursor c= sql.query(nombre,new String[]{"*"},null,null,null,null, null);
        cursor = sql.query(nombre,new String[]{"*"},null,null,null,null,null,String.valueOf(cantidad));
        mensajeLog("total cursor: " + cursor.getCount());
        return cursor;
    }



    public Cursor seleccionarUltimos (int cantidad) {
        cursor = sql.query(nombre,new String[]{"*"},"",null,null,null,columnaIdentificadora + " desc", String.valueOf(cantidad));
        return cursor;
    }

    public Cursor seleccionarUltimo () {
        return seleccionarUltimos(1);
    }









    public Cursor seleccionar_descendiente () {
        //cursor = sql.rawQuery("*",null);
        //cursor= sql.query(nombre,new String[]{"*"},null,null,null,null, null);
        cursor = sql.query(nombre,new String[]{"*"},null,null,null,null,columnaIdentificadora + " desc");
        return cursor;
    }


    public Cursor seleccionar_descendiente (String condicion) {
        cursor = sql.query(nombre,new String[]{"*"},condicion,null,null,null,columnaIdentificadora + " desc", null);
        // cursor = sql.rawQuery("SELECT * FROM " + nombre + " WHERE ?" , new String[] { condicion });
        return cursor;
    }


    public Cursor seleccionar_descendiente (String condicion, String grupo) {
        cursor = sql.query(nombre,new String[]{"*"},condicion,null,grupo,null,columnaIdentificadora + " desc", null);
        // cursor = sql.rawQuery("SELECT * FROM " + nombre + " WHERE ?" , new String[] { condicion });
        return cursor;
    }







    // -------------------------------------------------------------------------------
    // Contenedor interno
    // -------------------------------------------------------------------------------
    public void agregar (String columna, String valor) {  contenedor.put(columna, valor);  }
    public void agregar (String columna, int valor) {  contenedor.put(columna, valor);  }
    public void agregar (String columna, long valor) {  contenedor.put(columna, valor);  }
    public void agregar (String columna, double valor) {  contenedor.put(columna, valor);  }
    public void agregar (ContentValues contentValues) {  contenedor.putAll(contentValues);  }

    public void insertar () { insertar(contenedor);  limpiarContenedor();}
    public void actualizar (int id) { actualizar(id, contenedor);  limpiarContenedor(); }

    // si existe la columna, la actualiza, sino la inserta
    public void persistir (String columna) {
        MensajeRegistro.msj(this, "persistiendo segun " + columna);
        String condicion = columna + " = '" + contenedor.getAsString(columna).trim() + "' ";
        seleccionar(condicion);

        MensajeRegistro.msj(this, condicion + " total: " + contar() );


        if (contar() == 0)
            insertar(contenedor);
        else
            actualizar(condicion, contenedor);

        limpiarContenedor();
    }

    public void limpiarContenedor () {
        contenedor.clear();
        contenedor = new ContentValues();
    }


    // -------------------------------------------------------------------------------
    // Otros
    // -------------------------------------------------------------------------------

    public long total () {
        return total (null);
    }

    public long total (String columna) {
        return DatabaseUtils.queryNumEntries(sql, nombre, columna);

    }

    public int contar () {
        return cursor.getCount();
    }

    public int sumar (String columna) {
        int total = 0;
        if (contar() == 0) return 0;



        do {
            cursor.moveToNext();
            total = total + entero(columna);
            MensajeRegistro.msj(this, "sumando -> total = "  + total );

        } while (cursor.isLast() == false) ;

        return total;
    }
    // -------------------------------------------------------------------------------



    protected String  cadena (String columna) {
        return cursor.getString(cursor.getColumnIndex(columna));
    }

    protected int entero (String columna) {
        return cursor.getInt(cursor.getColumnIndex(columna));
    }

    protected long largo (String columna) {
        return cursor.getLong(cursor.getColumnIndex(columna));
    }

    // -------------------------------------------------------------------------------




    // -------------------------------------------------------------------------------

    private void mensajeLog (String texto) {

        MensajeRegistro.msj (this, texto);
    }



}
