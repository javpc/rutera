package utilidades.difusion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Javier 2021
 *
 *
 *
 *
         <activity
             android:name=".control.panico.PanicoActivador"
             android:theme="@android:style/Theme.NoDisplay"
             android:noHistory="true"
             >
             <intent-filter>
                 <action android:name="info.guardianproject.panic.action.TRIGGER" />
                 <category android:name="android.intent.category.DEFAULT" />
             </intent-filter>
         </activity>

          <activity
             android:name=".control.panico.ActividadPanicoOpciones"
             android:label="@string/boton_de_panico"
             >
              <intent-filter>
                 <action android:name="info.guardianproject.panic.action.CONNECT" />
                 <category android:name="android.intent.category.DEFAULT" />
             </intent-filter>
         </activity>.
 */



    public abstract class Panico extends Activity {



        public static final String GATILLO_PANICO = "info.guardianproject.panic.action.TRIGGER";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);



            Intent intent = getIntent();

            Log.d ("Pánico Recibido", intent.getAction());

            if (intent != null && GATILLO_PANICO.equals(intent.getAction())) {
                Log.d ("Pánico Activado", intent.getAction());
                activar();
            }

			finish ();
        }


        public abstract void activar () ;

    }







