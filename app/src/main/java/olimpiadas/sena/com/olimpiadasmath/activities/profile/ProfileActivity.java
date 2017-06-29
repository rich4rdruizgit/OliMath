package olimpiadas.sena.com.olimpiadasmath.activities.profile;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import olimpiadas.sena.com.olimpiadasmath.R;

import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.librerias.identicons.Identicon;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProfileActivity extends AppCompatActivity {


    AppControl appControl;

    TextView tvLvl,tvCoins,tvTickets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        appControl = AppControl.getInstance();

        tvLvl = (TextView) findViewById(R.id.tv_profile_level_number);
        tvCoins = (TextView) findViewById(R.id.tv_profile_coins);
        tvTickets = (TextView) findViewById(R.id.tv_profile_ticket);


        tvLvl.setText(" x " + appControl.currentUser.getLevel());
        tvCoins.setText(" x " + appControl.currentUser.getCoins());
        tvTickets.setText(" x " + appControl.currentUser.getTickets());





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
    @Override
    protected void onResume() {
        super.onResume();

        tvCoins.setText(" X "+appControl.currentUser.getCoins());
        tvTickets.setText(" X "+appControl.currentUser.getTickets());

    }
}
