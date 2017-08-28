package olimpiadas.sena.com.olimpiadasmath.activities.session;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.realm.Realm;
import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.menu.MainActivity;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.model.Configuration;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnLogin,btnLosePass;
    TextView tvUser,tvPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLosePass = (Button) findViewById(R.id.btn_lose_pass);
        tvUser = (TextView) findViewById(R.id.etv_login_user);
        tvPwd = (TextView) findViewById(R.id.etv_login_pass);

        btnLogin.setOnClickListener(this);
        btnLosePass.setOnClickListener(this);

        if(AppControl.getInstance().isLogged == true){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                if(tvUser.getText().length() == 0){
                    tvUser.setError(getString(R.string.fiel_required));
                    return;
                }

                if(tvPwd.getText().length() == 0){
                    tvPwd.setError(getString(R.string.fiel_required));
                    return;
                }

                if(!tvUser.getText().toString().equals("user") || !tvPwd.getText().toString().equals("123")){
                    tvUser.setError(getString(R.string.user_not_found));
                    return;
                }

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
                break;
            case R.id.btn_lose_pass:
                break;
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
