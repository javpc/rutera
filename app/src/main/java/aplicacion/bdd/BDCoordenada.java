package aplicacion.bdd;


import android.database.Cursor;

import java.util.ArrayList;
import java.util.Date;

import aplicacion.adaptador.Coordenada;
import utilidades.basico.MensajeRegistro;
import utilidades.bdd.Tabla;



/**
 *
 */
public class BDCoordenada extends Tabla {




    public BDCoordenada() {

        super("coordenada");

        estructura  = "" +
                "Create Table if not exists " + nombre + "(" +
                "id integer primary key autoincrement," +
                "sesion text," +
                "latitud text," +
                "longitud text," +
                "velocidad text," +
                "altitud text," +
                "precision text," +
                "proveedor text," +
                "milisegundos text," +
                "extra text," +
                "recibido int," +
                "eliminado int," +

                "fecha text" +


                ");"
        ;




        actualizacion = "ALTER TABLE " + nombre + " RENAME TO " + nombre + "copia ;";
    }



    public void insertar (utilidades.localizacion_gps.Coordenada coordenada, long sesion) {
        agregar("sesion", sesion);

        agregar("latitud", coordenada.getLatitud());
        agregar("longitud", coordenada.getLongitud());
        agregar("altitud", coordenada.getAltitud());
        agregar("velocidad", coordenada.getVelocidad());
        agregar("fecha", coordenada.getMilisegundos());
        agregar("proveedor", coordenada.getProveedor());
        agregar("precision", coordenada.getPrecision());
        agregar("milisegundos", coordenada.getMilisegundos());



        insertar();
    }



    public ArrayList<Coordenada> coordenadaObtenerTodas () {
        return Listar(seleccionar());
    }


    private Coordenada crearCoordenada (Cursor cursor) {
        this.cursor = cursor;

        double latitud;
        double longitud;
        String extra;
        Date fecha = new Date();
        int recibido = 0;
        int eliminado = 0;
        int id;
        float velocidad;
        String proveedor;
        long milisegundos;
        double altitud;
        String precision;
        long sesion;

        sesion = (Long.parseLong ( cadena("sesion")));
        fecha.setTime(Long.parseLong ( cadena("fecha")));
        latitud = Double.parseDouble ( cadena("latitud"));
        longitud = Double.parseDouble(cadena("longitud"));
        altitud = Double.parseDouble(cadena("altitud"));
        milisegundos = Long.parseLong(cadena("milisegundos"));
        velocidad = Float.parseFloat (cadena("velocidad"));
        proveedor = cadena("proveedor");
        extra = cadena("extra");
        id = entero ("id");


        Coordenada coordenada = new Coordenada();

        coordenada.setLatitud(latitud);
        coordenada.setLongitud(longitud);
        coordenada.setAltitud(altitud);
        coordenada.setVelocidad(velocidad);
        coordenada.setProveedor(proveedor);
        coordenada.setFechaHora(fecha);
        coordenada.setRecibido(recibido);
        coordenada.setIdentificador(id);
        coordenada.setMilisegundos(milisegundos);
        coordenada.setSesion(sesion);
        return coordenada;

    }











    protected ArrayList<Coordenada> Listar (Cursor cursor) {
        ArrayList<Coordenada> lista = new ArrayList<Coordenada>();
        int total = cursor.getCount();

        cursor.moveToFirst();

        if (total > 0)
            while (cursor.isAfterLast() == false) {

                Coordenada coordenada = crearCoordenada(cursor);
                lista.add(coordenada);
                cursor.moveToNext();
                MensajeRegistro.msj ("COORDENADA: ", "x" + coordenada.getFechaHora().toString());
            } ;


            MensajeRegistro . msj ("total: ", total);
        return lista;
    }







    public ArrayList<Coordenada> coordenadas (String condicion) {
        return Listar(seleccionar (condicion));
    }

    public ArrayList<Coordenada> obtenerSesiones () {
        return Listar(seleccionar_descendiente ("id>0", "sesion"));
    }

    public ArrayList<Coordenada> obtenerSesion (long sesion) {
        return Listar(seleccionar ("sesion=" + sesion));
    }

    public void borrar_sesion (long sesion) {
        eliminar("sesion = " + sesion);
    }
}
