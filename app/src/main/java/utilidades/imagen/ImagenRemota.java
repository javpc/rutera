package utilidades.imagen;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import utilidades.basico.MensajeRegistro;
import utilidades.eventos.MiniEvento;
import utilidades.eventos.MiniReceptor;

public class ImagenRemota {

    private ImageView vistaImagen;
    private Bitmap bitmap;

    public void conectar (URL direccion) {
        Conexion conexion = new Conexion(direccion);
        conexion.start();
    }

    public void conectar (String direccion) {
        try {
            URL url= new URL(direccion);
            conectar(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }



    public void bitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        if (vistaImagen != null)
            vistaImagen.setImageBitmap(bitmap);
    }


    public ImageView getVistaImagen () { return vistaImagen; }
    public void setVistaImagen (ImageView vistaImagen) {
        this.vistaImagen = vistaImagen;
    }


    private class Conexion extends Thread {
        private URL url;
        MiniReceptor r = new Receptor(); // RECEPTOR EN HILO PRINCIPAL

        public Conexion (URL url) {
            this.url = url;
        }
        @Override
        public void run () {
            try {

                Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
                MiniEvento evento = new MiniEvento(this);
                ArrayList<Bitmap> a = new ArrayList<>();
                a.add(bitmap);
                evento.agregar_receptor (r);
                evento.agregar_dato("imagen", a);
                evento.set_tipo_dato(MiniEvento.TIPO_LISTA);
                evento.lanzar();
                MensajeRegistro.msj (this, "IMAGEN LANZADA");

            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }


    private class Receptor extends MiniReceptor {


        @Override
        public void recibir (Bundle bundle) {
            ArrayList<Bitmap> a;
            Bitmap bitmap;
            try {
                a = (ArrayList) bundle.getSerializable("imagen");
                bitmap = (Bitmap) a.get(0);
            } catch (Exception e) {
                return;
            }
            MensajeRegistro.msj (this, "IMAGEN RECIBIDA");

            bitmap(bitmap);
        }
    }


}
