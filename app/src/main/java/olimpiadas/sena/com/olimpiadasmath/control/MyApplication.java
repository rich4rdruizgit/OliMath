package olimpiadas.sena.com.olimpiadasmath.control;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;

/**
 * Created by defytek on 6/21/17.
 * Mi Aplicación
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        // The following line triggers the initialization of ACRA
        //ACRA.init(this);
        //Realm.init(this);
    }


}
