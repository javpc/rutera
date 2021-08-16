package utilidades.menu;


import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


/**
 * Javier 2019.
 */

public class PantallaPrincipalConMenuLateral extends AppCompatActivity {

        protected MenuLateral menuLateral;


        @Override
        public boolean onKeyUp(int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_MENU)
                menuLateral.alternar ();
            return super.onKeyUp(keyCode, event);
        }

        @Override
        public void onBackPressed() {
            if (menuLateral.abierto())
                menuLateral.cerrar();
            else
                super.onBackPressed();
        }





        @Override
        public boolean onOptionsItemSelected (MenuItem item) {
            menuLateral.alternar();
            return true;
        }







        // Menú lateral -----------------------------------------------------------------

        public void clicMenu (MenuItem item) {
            menuLateral.cerrar();
            /*
            switch (item.getItemId()) {
                case R.id.menu_ayuda:
                    control.iniciarActividad(ActividadAyuda.class);
                    break;
            }
            */

        }

        public void clicAbrir (View v) {
            menuLateral.abrir();
        }


        public void clicCerrar (View v) {
            menuLateral.cerrar();
        }







    }
