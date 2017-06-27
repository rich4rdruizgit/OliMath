package olimpiadas.sena.com.olimpiadasmath.activities.session;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.menu.MainActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnLogin,btnLosePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLosePass = (Button) findViewById(R.id.btn_lose_pass);
        btnLogin.setOnClickListener(this);
        btnLosePass.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                Intent intentMenu = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intentMenu);
                finish();
                break;
            case R.id.btn_lose_pass:
                break;
        }
    }
}
