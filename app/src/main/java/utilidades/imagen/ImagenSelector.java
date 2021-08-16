package utilidades.imagen;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;

public class ImagenSelector {

    // código interto para recibir imágenes de la galeria.
    private final int AFIRMATIVO = 100;
    private Activity actividad;
    private ImageView vistaImagen;
    private Uri uri;

    public ImagenSelector(Activity actividad, ImageView vistaImagen) {
        this.actividad = actividad;
        this.vistaImagen = vistaImagen;
    }



    public void asociarEventoClic () {
        asociarEventoClic(vistaImagen);
    }

    public void asociarEventoClic (View vista) {
        vista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elegirImagen();
            }
        });
    }

    public void elegirImagen (){
        Intent galeria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        // galeria.setType("image/*");
        actividad.startActivityForResult(galeria, AFIRMATIVO);
    }



    public void onActivityResult(int codigo, int resultado, Intent dato){

        if (resultado != Activity.RESULT_OK)
            return;

        if (codigo  != AFIRMATIVO)
            return;

        uri = dato.getData();


        if (vistaImagen != null)
            vistaImagen.setImageURI(uri);

    }


    public Bitmap bitmap () {
        if (uri == null)
            return null;
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap (actividad.getContentResolver(), uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }


    public Uri uri () { return  uri; }

}
