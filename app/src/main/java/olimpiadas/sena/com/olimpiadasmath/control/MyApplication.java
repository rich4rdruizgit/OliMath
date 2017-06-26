package olimpiadas.sena.com.olimpiadasmath.control;

import android.app.Application;
import android.content.Context;

import io.realm.Realm;
import olimpiadas.sena.com.olimpiadasmath.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by defytek on 6/21/17.
 * Mi Aplicación
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("grobold.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        // The following line triggers the initialization of ACRA
        //ACRA.init(this);
        //Realm.init(this);
    }


}
