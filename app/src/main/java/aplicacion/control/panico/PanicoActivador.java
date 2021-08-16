package aplicacion.control.panico;


import aplicacion.bdd.BD;
import utilidades.basico.MensajeRegistro;
import utilidades.control.Control;
import utilidades.difusion.Panico;

/**
 * Created by Javier on 25/7/2019.
 *
 * Botón de pánico
 * Activa la aplicación
 */

public class PanicoActivador extends Panico {






    @Override
    public void activar() {
        MensajeRegistro.msj(this, "Botón activado");

        iniciar();
    }


    public void iniciar () {


        BD bd = new BD (this);
        bd.eliminarTodoYCerrar();


    }


}
