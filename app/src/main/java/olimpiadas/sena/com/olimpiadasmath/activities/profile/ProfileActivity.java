package olimpiadas.sena.com.olimpiadasmath.activities.profile;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.ClipDrawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import olimpiadas.sena.com.olimpiadasmath.R;

import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.librerias.identicons.Identicon;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProfileActivity extends AppCompatActivity {


    AppControl appControl;

    TextView tvLvl,tvCoins,tvTickets;
    ImageView imgExp;
    private ClipDrawable mImageDrawable;


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



        ImageView imgExp = (ImageView) findViewById(R.id.img_profile_progress_bar2);
        mImageDrawable = (ClipDrawable) imgExp.getDrawable();


        mImageDrawable.setLevel((int)appControl.currentUser.getExperience() * 100);



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
