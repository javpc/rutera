package utilidades.imagen;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import utilidades.basico.MensajeRegistro;

public class Imagen  {




    protected ImageView vistaImagen;
    protected Bitmap bitmap;
    protected Bitmap bitmap_original;
    protected Uri uri;







    public Imagen () {}
    public Imagen (ImageView vistaImagen) {
        this.vistaImagen = vistaImagen;
    }

    public void vista (ImageView vistaImagen) {
        this.vistaImagen = vistaImagen;
    }

    public ImageView vista () {
        return vistaImagen;
    }



    public Bitmap bitmap () {
        // si bitmap es nulo debería devolver el contenido de vistaImagen
        return bitmap;
    }

    public void bitmap (Bitmap bitmap) {
        this.bitmap = bitmap;
        if (vistaImagen != null)
            vistaImagen.setImageBitmap(bitmap);
        if (bitmap_original == null)
            bitmap_original = bitmap;
    }



    public Uri uri () {
        return uri;
    }

    public void uri (Uri uri) {
        if (uri == null)
            return;
        this.uri = uri;
        if (vistaImagen == null)
            return;
        try { vistaImagen.setImageURI(uri); }
        catch (Exception e) { }
    }

    public void uri (String cadena) {
        try { uri (Uri.parse(cadena)); }
        catch (Exception e) { }
    }

    public void uri (Context contexto, Uri uri) {
        this.uri = uri;
        if (contexto == null)
            return;
        try {
            bitmap ( MediaStore.Images.Media.getBitmap(contexto.getContentResolver(), uri));
        } catch (Exception e) {
            MensajeRegistro.msj( "ERROR IMAGEN - BITMAP");
        }
    }



    public void nuevo_uri (Context contexto, String titulo) {
        if (bitmap == null)
            return;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        final String path = MediaStore.Images.Media.insertImage(contexto.getContentResolver(), bitmap, titulo, "");
        uri (path);
    }


    public void bitmap (String base64) {
        bitmap ( decodificar(base64) );

    }





    public void rotar (float angulo)
    {
        final Matrix matrix = new Matrix();
        matrix.postRotate(angulo);
        bitmap ( Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true) );
    }


    public void escalar (int x, int y) {
        if (bitmap == null)
            return;


        final int ancho = bitmap.getWidth();
        final int alto  = bitmap.getHeight();


        // calculate the scale - in this case = 0.4f
        final float escala_x = ((float) x) / ancho;
        final float escala_y = ((float) y) / alto;

        // createa matrix for the manipulation
        final Matrix matrix = new Matrix();
        // resize the bit map
        matrix.postScale(escala_x, escala_y);

        // recreate the new Bitmap
        bitmap ( Bitmap.createBitmap(bitmap, 0, 0, ancho, alto, matrix, true) );

    }



    public void texto (String texto, int x, int y) {

    }


    // --------------------



    public byte[] bytes () {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        if (bitmap != null)
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }



    public String base64 () {
        byte[] bytes = bytes();
        Bitmap bm= BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (bm == null)
            return "";
        bm.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }




    public Bitmap decodificar (String codigoB64) {
        MensajeRegistro.msj(this, "Decodificando + " + codigoB64);
        byte[] bytes = Base64.decode(codigoB64, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }







    public void deshacer () {
        bitmap(bitmap_original);
    }

    public void confirmar () {
        bitmap_original = bitmap;
    }
}


