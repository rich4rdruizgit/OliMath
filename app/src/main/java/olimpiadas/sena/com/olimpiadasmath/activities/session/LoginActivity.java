package olimpiadas.sena.com.olimpiadasmath.activities.session;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.menu.MainActivity;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.model.Configuration;
import olimpiadas.sena.com.olimpiadasmath.util.webConManager.WebConnection;
import olimpiadas.sena.com.olimpiadasmath.util.webConManager.WebConnectionManager;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, WebConnectionManager.WebConnectionManagerListener{

    private static final String TAG = LoginActivity.class.toString();

    Button btnLogin,btnLosePass;
    EditText tvUser,tvPwd;
    WebConnectionManager webConnectionManager;
    AppControl appControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        AppControl.getInstance().currentActivity = LoginActivity.class.getSimpleName();


        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLosePass = (Button) findViewById(R.id.btn_lose_pass);
        tvUser = (EditText) findViewById(R.id.etv_login_user);
        tvPwd = (EditText) findViewById(R.id.etv_login_pass);
        appControl = AppControl.getInstance();


        btnLogin.setOnClickListener(this);
        btnLosePass.setOnClickListener(this);

        if(AppControl.getInstance().isLogged == true){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        appControl.soundButton = MediaPlayer.create(getApplicationContext(),appControl.soundButtonEfect);
        switch (v.getId()){
            case R.id.btn_login:
                if(appControl.isBackgroundPlaying)
                    appControl.soundButton.start();
                if(tvUser.getText().length() == 0){
                    tvUser.setError(getString(R.string.fiel_required));
                    return;
                }

                if(tvPwd.getText().length() == 0){
                    tvPwd.setError(getString(R.string.fiel_required));
                    return;
                }
                /*if(!tvUser.getText().toString().equals("user") || !tvPwd.getText().toString().equals("123")){
                    tvUser.setError(getString(R.string.user_not_found));
                    return;
                }*/
                webConnectionManager = WebConnectionManager.getWebConnectionManager();
                webConnectionManager.setWebConnectionManagerListener(this);
                webConnectionManager.login(tvUser.getText().toString(), tvPwd.getText().toString());
                break;
            case R.id.btn_lose_pass:
                appControl.soundButton.start();
                break;
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void webRequestComplete(WebConnectionManager.Response response) {
        //TODO : configurar la sesion local con realm
        Log.d("RESPONSE OBJECT", response.toString());
        if( (response.getStatus().equals(WebConnectionManager.Response.SUCCESS))&&
                (response.getResult().equals(WebConnectionManager.Response.LOGGED))){

            Realm realm = Realm.getDefaultInstance();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    Configuration config = realm.where(Configuration.class).equalTo("key","isLogged").findFirst();
                    config.setValue(true);
                }
            });

            Intent intentMenu = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intentMenu);
            finish();
        }else{
            Toast.makeText(this, "Paila", Toast.LENGTH_SHORT).show();
        }
    }
}
