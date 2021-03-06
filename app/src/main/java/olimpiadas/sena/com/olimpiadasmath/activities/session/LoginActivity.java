package olimpiadas.sena.com.olimpiadasmath.activities.session;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import io.realm.Realm;
import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.menu.MainActivity;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.model.Configuration;
import olimpiadas.sena.com.olimpiadasmath.model.User;
import olimpiadas.sena.com.olimpiadasmath.util.DialogHelper;
import olimpiadas.sena.com.olimpiadasmath.util.webConManager.WebConnectionManager;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, WebConnectionManager.WebConnectionManagerListener{

    private static final String TAG = LoginActivity.class.toString();

    Button btnLogin, btnLosePass;
    EditText tvUser, tvPwd;
    WebConnectionManager webConnectionManager;
    boolean stateNet = false;

    boolean connected = false;
    AppControl appControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        AppControl.getInstance().currentActivity = LoginActivity.class.getSimpleName();

        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        if (!wifiManager.isWifiEnabled()) {
            // Mostrar un dialog
            DialogHelper.showWifiState(LoginActivity.this);
        }

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
        appControl.soundButtonPlay();
        switch (v.getId()) {
            case R.id.btn_login:
                if(tvUser.getText().length() == 0){
                    tvUser.setError(getString(R.string.fiel_required));
                    return;
                }

                if(tvPwd.getText().length() == 0){
                    tvPwd.setError(getString(R.string.fiel_required));
                    return;
                }
                webConnectionManager = WebConnectionManager.getWebConnectionManager();
                webConnectionManager.setWebConnectionManagerListener(this);
                stateNet = verificarConexion(this);
                Log.d("ESTADO NET", stateNet+"");
                if(stateNet){
                    webConnectionManager.login(tvUser.getText().toString(), tvPwd.getText().toString());
                }else {
                    Toast.makeText(this, "Debes estar conectado a Intenet para iniciar sesión", Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.btn_lose_pass:
                break;
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void webRequestComplete(WebConnectionManager.Response response) {

        Log.d("RESPONSE OBJECT", response.toString());
        if ((response.getStatus().equals(WebConnectionManager.Response.SUCCESS)) &&
                (response.getResult().equals(WebConnectionManager.Response.LOGGED))) {

            Realm realm = Realm.getDefaultInstance();

            final User currentuser = new User();

            try {
                JSONArray user = new JSONArray(response.getData());
                Log.d(TAG, " hola " + user.length());

                if (user.length() != 0) {
                    currentuser.setExperience((double) user.getJSONObject(0).getInt("experiencia"));
                    currentuser.setCoins(user.getJSONObject(0).getInt("coins"));
                    currentuser.setLevel(user.getJSONObject(0).getInt("nivel"));
                    currentuser.setNickname(user.getJSONObject(0).getString("nickname"));
                    currentuser.setAvatar(user.getJSONObject(0).getString("avatar").toLowerCase());
                    currentuser.setId(user.getJSONObject(0).getString("idPerfil"));
                    realm.executeTransactionAsync(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            realm.where(User.class).findAll().deleteAllFromRealm();
                            realm.insert(currentuser);

                            Configuration config = realm.where(Configuration.class).equalTo("key", "isLogged").findFirst();
                            config.setValue(true);

                        }
                    }, new Realm.Transaction.OnSuccess() {
                        @Override
                        public void onSuccess() {
                            appControl.currentUser = currentuser;
                            Intent intentMenu = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intentMenu);
                            finish();
                        }
                    }, new Realm.Transaction.OnError() {
                        @Override
                        public void onError(Throwable error) {
                            Toast.makeText(LoginActivity.this, "Se presento un error, por favor intenta nuevamente", Toast.LENGTH_LONG).show();

                        }
                    });

                }
            } catch (JSONException e) {


                e.printStackTrace();
            }



        } else {
            Toast.makeText(this, "No hay internet", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean verificarConexion(Context context) {
        boolean bConectado = false;
        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] redes = connec.getAllNetworkInfo();
        for (int i = 0; i < 2; i++) {
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                bConectado = true;
            }
        }
        return bConectado;
    }
}
