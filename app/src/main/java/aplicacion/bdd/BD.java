
package aplicacion.bdd;

import android.content.Context;

/**
 * Javier 2016.
 * maneja la base de datos, controlador
 *
 */
public class BD extends utilidades.bdd.BDAdaptador {



    // tablas de la base de datos

    BDCoordenada bdCoordenada = new BDCoordenada();
    BDClima bdClima = new BDClima();



    public BD(Context contexto) {
        super(contexto, 3);

        agregarTabla(bdCoordenada);
        agregarTabla(bdClima);
        abrir();

    }


    public BDCoordenada getBdCoordenada() {
        return bdCoordenada;
    }



    public BDClima getBdClima () {return  bdClima;}





/*




    public ArrayList<Coordenada> publicaciones (int cantidad) {
        ArrayList<Coordenada> publicaciones = new ArrayList<>();

        bdCoordenada.seleccionarUltimos (cantidad);

        MensajeRegistro.msj(this, "publicaciones "  + bdCoordenada.contar() );

        if (bdCoordenada.contar() > 0)

        do {
            bdCoordenada.cursor().moveToNext();
            Coordenada coordenada = crearPublicacion();
            publicaciones.add(coordenada);
            MensajeRegistro.msj(this, "publicaciones -> "  + coordenada.getIdentificador() );

        } while (bdCoordenada.cursor().isLast() == false) ;

        return publicaciones;

    }





    public ArrayList<Coordenada> publicaciones (String condicion) {
        ArrayList<Coordenada> publicaciones = new ArrayList<>();

        bdCoordenada.seleccionar(condicion);

        MensajeRegistro.msj(this, "publicaciones "  + bdCoordenada.contar() );

        if (bdCoordenada.contar() > 0)

            do {
                bdCoordenada.cursor().moveToNext();
                Coordenada coordenada = crearPublicacion();
                publicaciones.add(coordenada);
                MensajeRegistro.msj(this, "publicaciones -> "  + coordenada.getIdentificador() );

            } while (bdCoordenada.cursor().isLast() == false) ;

        return publicaciones;

    }




*/




    /*

    private Coordenada crearPublicacion () {
        Coordenada coordenada = new Coordenada();


        coordenada.setIdentificador        ( bdCoordenada.entero("id" ));
        coordenada.setLatitud ( bdCoordenada.entero("latitud" ));
        coordenada.setLongitud        ( bdCoordenada.largo("longitud" ));
        coordenada.setAltitud ( bdCoordenada.largo("altitud" ));
        coordenada.setMilisegundos ( bdCoordenada.largo("milisegundos" ));
        coordenada.setPrecision ( bdCoordenada.cadena("precision" ));
        coordenada.setProveedor        ( bdCoordenada.cadena("proveedor" ));
        coordenada.setVelocidad        ( bdCoordenada.largo("velocidad" ));





        return coordenada;
    }


     */










    public void eliminarTodoYCerrar () {
        bdCoordenada.eliminar();
        bdClima.eliminar();
        cerrar();
    }

}


