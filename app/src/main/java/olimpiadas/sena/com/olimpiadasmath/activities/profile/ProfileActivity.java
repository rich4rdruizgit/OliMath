package olimpiadas.sena.com.olimpiadasmath.activities.profile;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.librerias.identicons.Identicon;

public class ProfileActivity extends AppCompatActivity {
    TextView txt_level, txt_levelnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ((TextView)findViewById(R.id.textView_level)).setTypeface(Typeface.createFromAsset(this.getAssets(),"grobold.ttf"));
        ((TextView)findViewById(R.id.textView_level)).setTypeface(Typeface.createFromAsset(this.getAssets(),"grobold.ttf"));

        Identicon identicon = (Identicon) findViewById(R.id.identicon);
        identicon.show("john.doe@example.org");
        // identicon.show(myObject);
        // identicon.show(42);
        // identicon.show(System.currentTimeMillis());
        // identicon.show(true);


    }
}
