package olimpiadas.sena.com.olimpiadasmath.activities.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import io.realm.Realm;
import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.menu.MainActivity;
import olimpiadas.sena.com.olimpiadasmath.activities.session.LoginActivity;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.model.Configuration;
import olimpiadas.sena.com.olimpiadasmath.util.DialogHelper;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SettingsActivity extends AppCompatActivity {

    private static final String TAG = "SettingsActivity";
    Button btn_credits, btn_help, btnBack, btnLogout;
    Spinner spn_lenguage;
    TextView txt_lenguage, txt_music, txt_efects;
    Switch swtMusic;
    AppControl appControl = AppControl.getInstance();
    Realm realm = Realm.getDefaultInstance();

    //public static MediaPlayer sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_settings);
        appControl.currentActivity = SettingsActivity.class.getSimpleName();

        //btn_help = (Button) findViewById(R.id.btn_help);
        btn_credits = (Button) findViewById(R.id.btn_credits);
        btnLogout = (Button) findViewById(R.id.btn_logout);
        swtMusic = (Switch) findViewById(R.id.swtMusic);
        btnBack = (Button) findViewById(R.id.btn_back_settings);
        //txt_efects = (TextView) findViewById(R.id.txt_efects);
        //txt_lenguage = (TextView) findViewById(R.id.txt_lenguage);
        //txt_music = (TextView) findViewById(R.id.txt_music);
        btn_credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appControl.soundButtonPlay();
                DialogHelper.showCopyRightDialog(v.getContext());
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appControl.soundButtonPlay();
                Intent goBack = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(goBack);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appControl.isLogged = false;
                appControl = null;

                realm = Realm.getDefaultInstance();
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Configuration configuration = realm.where(Configuration.class).equalTo("key", "isLogged").findFirst();
                        configuration.setValue(false);
                        realm.insertOrUpdate(configuration);

                    }
                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        error.printStackTrace();
                        Log.d(TAG, "error al guardar");
                        Log.d(TAG, "error al guardar");
                    }
                });

                Intent logout = new Intent(SettingsActivity.this, LoginActivity.class);
                startActivity(logout);
                finish();
            }
        });
        /*btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(SettingsActivity.this, "Presionó botón Ayuda", Toast.LENGTH_SHORT).show();

            }
        });*/
        if (appControl.isBackgroundPlaying) {
            swtMusic.setChecked(true);
        }
        //


        swtMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                appControl.settingSoundBackground(isChecked);
            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
