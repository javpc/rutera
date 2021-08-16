package utilidades.imagen;


import android.app.Activity;
import android.content.Intent;
import android.provider.MediaStore;
import android.widget.ImageView;

// Un conjunto de herramientas completa para imágenes
public class AdaptadorImagen extends Imagen {

    protected Activity actividad;






    public AdaptadorImagen (ImageView vistaImagen) {
        super(vistaImagen);
    }
    public AdaptadorImagen () {
        super();
    }




    public void elegirImagen (Activity actividad){
        this.actividad = actividad;
        final Intent galeria = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        // galeria.setType("image/*");
        actividad.startActivityForResult(galeria, 200101);
    }



    public void onActivityResult(int codigo, int resultado, Intent dato){

        if (resultado != Activity.RESULT_OK)
            return;

        if (codigo  != 200101)
            return;


        uri (actividad, dato.getData());


    }

}
