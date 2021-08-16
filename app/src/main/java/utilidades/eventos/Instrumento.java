package utilidades.eventos;

import android.util.Log;
import android.widget.TextView;

public class Instrumento {

    private TextView textView;
    private Receptor receptor;
    private MiniEvento evento;


    public Instrumento (TextView textView) {

        this.textView = textView;
        receptor = new Receptor();
        evento   = new MiniEvento();
        evento.agregar_receptor(receptor);
    }


    public void texto (String texto) {
        evento.lanzar(texto);

    }

    private void actualizar_inferfaz (String valor) {
        if (valor == null)
            valor = "";
        Log.d("EVENTO ; ", valor);
        if (textView != null)
            textView.setText(valor);
    }

    public MiniEvento obtenerEvento () { return evento; }
    public MiniReceptor obtenerReceptor () { return receptor; }




    protected class Receptor extends MiniReceptor {

        @Override
        public void recibir (String valor) {
            super.recibir(valor);
            actualizar_inferfaz(valor);
        }
    }

}
