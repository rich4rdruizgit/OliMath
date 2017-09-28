package olimpiadas.sena.com.olimpiadasmath.activities.settings;

import android.content.Context;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.menu.MainActivity;
import olimpiadas.sena.com.olimpiadasmath.util.DialogHelper;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SettingsActivity extends AppCompatActivity {

    Button btn_credits,btn_help;
    Spinner spn_lenguage;
    TextView txt_lenguage,txt_music,txt_efects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_settings);


        btn_help = (Button) findViewById(R.id.btn_help);
        btn_credits = (Button) findViewById(R.id.btn_credits);
        //txt_efects = (TextView) findViewById(R.id.txt_efects);
        //txt_lenguage = (TextView) findViewById(R.id.txt_lenguage);
        //txt_music = (TextView) findViewById(R.id.txt_music);

        btn_credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingsActivity.this, "Presionó botón Creditos", Toast.LENGTH_SHORT).show();
                DialogHelper.showCopyRightDialog(v.getContext());
            }
        });
        
        btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SettingsActivity.this, "Presionó botón Ayuda", Toast.LENGTH_SHORT).show();
                MainActivity.sound.stop();
            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
