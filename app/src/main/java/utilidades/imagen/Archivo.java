package utilidades.imagen;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;

public class Archivo {

    private Activity actividad;


    public Archivo (Activity actividad) {
        this.actividad = actividad;
    }


    public File archivo (String nombre_completo) {
        return new File(nombre_completo);
    }

    public File archivo (Uri uri) {
        return archivo (nombreCompleto(uri));
    }



    private String nombreCompleto(Uri uri) {
        if (uri == null)
            return "";

        String result;
        Cursor cursor = actividad.getContentResolver().query(uri, null, null, null, null);
        if (cursor == null) {
            // Source is Dropbox or other similar local file path
            result = uri.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }



}
