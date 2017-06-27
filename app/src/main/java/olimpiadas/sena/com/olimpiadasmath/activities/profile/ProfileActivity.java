package olimpiadas.sena.com.olimpiadasmath.activities.profile;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import olimpiadas.sena.com.olimpiadasmath.R;

import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.librerias.identicons.Identicon;
import olimpiadas.sena.com.olimpiadasmath.model.User;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProfileActivity extends AppCompatActivity {

    TextView tvNickName, tvNivel, tvTickets, tvCoins;

    User user;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        tvNickName = (TextView) findViewById(R.id.txt_nick);
        tvNivel = (TextView) findViewById(R.id.txt_level_number);
        tvTickets = (TextView) findViewById(R.id.txt_ticket);
        tvCoins = (TextView) findViewById(R.id.txt_coin);

        user = AppControl.getInstance().currentUser;
        Log.d("Usuario",user.toString());

        tvNickName.setText(user.getNickname().toString());
//        tvNivel.setText(user.getN);
        tvTickets.setText("X "+user.getTickets());
        tvCoins.setText("X "+user.getCoins());
        Identicon identicon = (Identicon) findViewById(R.id.identicon);
        identicon.show("mcabrerapatino@gmail.com");
        // identicon.show(myObject);
        // identicon.show(42);
        // identicon.show(System.currentTimeMillis());
        // identicon.show(true);


    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
