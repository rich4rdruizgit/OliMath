package olimpiadas.sena.com.olimpiadasmath.activities.ranking;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import io.realm.Realm;
import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.menu.MainActivity;
import olimpiadas.sena.com.olimpiadasmath.activities.session.LoginActivity;
import olimpiadas.sena.com.olimpiadasmath.adapter.ranking.ViewPagerRankingAdapter;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.model.Configuration;
import olimpiadas.sena.com.olimpiadasmath.util.webConManager.WebConnectionManager;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RankingActivity extends AppCompatActivity{

    ViewPager viewPager;
    Button btnBack;
    WebConnectionManager webConnectionManager;
    AppControl appControl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        getSupportActionBar().hide();
        AppControl.getInstance().currentActivity = RankingActivity.class.getSimpleName();

        viewPager = (ViewPager) findViewById(R.id.vpPagerRanking);
        viewPager.setAdapter(new ViewPagerRankingAdapter(getSupportFragmentManager()));

        appControl = AppControl.getInstance();

        btnBack = (Button) findViewById(R.id.btn_back_ranking);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appControl.soundButton = MediaPlayer.create(getApplicationContext(),appControl.soundButtonEfect);
                if(appControl.isBackgroundPlaying)
                    appControl.soundButton.start();
                Intent goBack = new Intent(RankingActivity.this, MainActivity.class);
                startActivity(goBack);
            }
        });

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


   /* @Override
    public void webRequestComplete(WebConnectionManager.Response response) {
        Log.d("RESPONSE OBJECT RANKING", response.toString());
        if( (response.getStatus().equals(WebConnectionManager.Response.SUCCESS))&&
                (response.getResult().equals(WebConnectionManager.Response.LOGGED))){
            Toast.makeText(this, "Conectado al servicio ranking", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Paila", Toast.LENGTH_SHORT).show();
        }
    }*/
}
