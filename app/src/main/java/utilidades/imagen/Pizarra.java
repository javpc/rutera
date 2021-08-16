package utilidades.imagen;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


/*
    ImageView que permite "toques" para dibujar
 */

public class Pizarra extends AdaptadorImagen {



    public Pizarra () { super (); }

    public Pizarra(ImageView vista) {
        super(vista);
        // asignar_evento();
    }









    // revisar ---
    public Bitmap obtener_bitmap (View v) {
        final Bitmap b = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas c = new Canvas(b);
        v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
        v.draw(c);
        return b;
    }



    public void texto (String texto) {


        // Typeface tf = Typeface.create("Helvetica", Typeface.BOLD);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);
        //paint.setTypeface(tf);
        paint.setTextAlign(Paint.Align.CENTER);
        // paint.setTextSize(convertToPixels(mContext, 11));

        Rect textRect = new Rect();
        paint.getTextBounds(texto, 0, texto.length(), textRect);

        Canvas canvas = new Canvas(bitmap);

        //If the text is bigger than the canvas , reduce the font size
        //if(textRect.width() >= (canvas.getWidth() - 4))     //the padding on either sides is considered as 4, so as to appropriately fit in the text
            //paint.setTextSize(convertToPixels(mContext, 7));        //Scaling needs to be used for different dpi's

        //Calculate the positions
        int xPos = (canvas.getWidth() / 2) - 2;     //-2 is for regulating the x position offset

        //"- ((paint.descent() + paint.ascent()) / 2)" is the distance from the baseline to the center.
        int yPos = (int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2)) ;

        canvas.drawText(texto, xPos, yPos, paint);

    }

    public void texto (String texto, int x, int y) {

    }







    private void asignar_evento () {
        if (vistaImagen == null)
            return;
        vistaImagen.setOnTouchListener(new View.OnTouchListener() {
            float lastX = 0, lastY = 0;
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case (MotionEvent.ACTION_DOWN):
                        lastX = motionEvent.getX();
                        lastY = motionEvent.getY();

                        break;
                    case MotionEvent.ACTION_MOVE:
                        float dx = motionEvent.getX() - lastX;
                        float dy = motionEvent.getY() - lastY;
                        float finalX = view.getX() + dx;
                        float finalY = view.getY() + dy + view.getHeight();
                        view.setX(finalX);
                        view.setY(finalY);
                        break;
                }

                return true;
            }
        });

    }







}
