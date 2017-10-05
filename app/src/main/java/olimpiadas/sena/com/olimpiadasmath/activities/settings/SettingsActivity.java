package olimpiadas.sena.com.olimpiadasmath.activities.settings;

import android.content.Context;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.menu.MainActivity;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.util.DialogHelper;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SettingsActivity extends AppCompatActivity {

    Button btn_credits,btn_help;
    Spinner spn_lenguage;
    TextView txt_lenguage,txt_music,txt_efects;
    Switch swtMusic;
    AppControl appControl = AppControl.getInstance();

    //public static MediaPlayer sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_settings);


        btn_help = (Button) findViewById(R.id.btn_help);
        btn_credits = (Button) findViewById(R.id.btn_credits);
        swtMusic = (Switch) findViewById(R.id.swtMusic);
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

            }
        });
        swtMusic.setChecked(true);
        swtMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                    if(!appControl.soundBackground.isPlaying()){
                        appControl.soundBackground = MediaPlayer.create(getApplicationContext(),R.raw.soundbackground);
                        appControl.soundBackground.start();
                        //swtMusic.setChecked(true);
                    }
                    //sound.start();
                }else{
                    if(appControl.soundBackground.isPlaying()){
                        //MainActivity.sound.start();
                        appControl.soundBackground.stop();
                    }
                }
            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
