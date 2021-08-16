package utilidades.menu;

import android.app.Activity;

import android.view.MenuItem;
import android.widget.TextView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;



/**
 * Javier 2019.
 */

public class MenuLateral {
    protected DrawerLayout drawer;
    protected Activity activity;
    private int idVista;
    //private int id = R.id.drawer_layout;

    public MenuLateral(Activity activity, int idDrawerLayout, int idVista) {
        drawer = (DrawerLayout) activity.findViewById(idDrawerLayout);
        this.activity= activity;
        this.idVista= idVista;
    }


    public void abrir () {
                drawer.openDrawer(GravityCompat.START);
    }

    public void cerrar () {
                drawer.closeDrawer(GravityCompat.START);
    }

    public void alternar () {
            if (drawer.isDrawerOpen(GravityCompat.START))
                drawer.closeDrawer(GravityCompat.START);
            else
                drawer.openDrawer(GravityCompat.START);
    }

    // consulta si el menú está abierto
    public boolean abierto () {
            return drawer.isDrawerOpen(GravityCompat.START);
    }


    public DrawerLayout getDrawer() {
        return drawer;
    }

    public void setDrawer(DrawerLayout drawer) {
        this.drawer = drawer;
    }




    // Menú



    public void setTextView (int id, String texto) {
        TextView textView = getTextView(id);
        if (textView != null)
            textView.setText(texto);
    }

    public void setOpcionVisible(int id, boolean valor) {
        MenuItem menuItem = getItem(id);
        if (menuItem != null)
            menuItem.setVisible(valor);
    }
    public void setOpcionHabilitada (int id, boolean valor) {
        MenuItem menuItem = getItem(id);
        if (menuItem != null)
            menuItem.setEnabled(valor);
    }

    public MenuItem getItem (int id) {
        return ((NavigationView) drawer.findViewById(idVista)).getMenu().findItem(id);
    }

    public TextView getTextView (int id) {
        return ((TextView) activity.findViewById(id));
    }



}
