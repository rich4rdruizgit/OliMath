package olimpiadas.sena.com.olimpiadasmath.activities.menu;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hitomi.cmlibrary.OnMenuSelectedListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.library.LibraryActivity;
import olimpiadas.sena.com.olimpiadasmath.activities.ranking.RankingActivity;
import olimpiadas.sena.com.olimpiadasmath.activities.settings.SettingsActivity;
import olimpiadas.sena.com.olimpiadasmath.activities.shop.ShopActivity;
import olimpiadas.sena.com.olimpiadasmath.activities.test.TestActivity;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.librerias.CircleMenu;
import olimpiadas.sena.com.olimpiadasmath.util.DialogHelper;
import pl.droidsonroids.gif.GifImageView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    String arrayName[] = {"Practice","Study","Challenge"};
    CircleMenu circleMenu;
    Button btnShop, btnRanking,btnSettings, btnPractice, btnChallenge, btnTutor;
    GifImageView gifMenu;
    AppControl appControl;
    Context context;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gifMenu = (GifImageView) findViewById(R.id.img_gif_menu); // Gif del menu principal
        gifMenu.setOnClickListener(this);
        getSupportActionBar().hide();
        appControl = AppControl.getInstance();

        btnPractice = (Button) findViewById(R.id.btn_menu_practice);
        btnPractice.setOnClickListener(this);

        btnChallenge = (Button) findViewById(R.id.btn_menu_challenge);
        btnChallenge.setOnClickListener(this);

        btnTutor = (Button) findViewById(R.id.btn_menu_tutor);
        btnTutor.setOnClickListener(this);


        btnShop = (Button) findViewById(R.id.btn_menu_shop);
        btnShop.setOnClickListener(this);

        btnRanking = (Button) findViewById(R.id.btn_menu_ranking);
        btnRanking.setOnClickListener(this);

        btnSettings = (Button) findViewById(R.id.btn_settings);
        btnSettings.setOnClickListener(this);


        //test

        try {

            Log.d("Test","se va a obtener el usuario " + appControl.currentUser.getExperience() );


            Log.d("Test","se va a crear jobject");
            JSONObject test = new JSONObject("{'question':'Me comi una manzana....',\n" +
                    "\t\t'answers':[\n" +
                    "\t\t{'answ1':'Respuesta 1' ,'isCorrect':'0'},\n" +
                    "\t\t{'answ2':'Respuesta 2' ,'isCorrect':'0'},\n" +
                    "\t\t{'answ3':'Respuesta 3' ,'isCorrect':'1'},\n" +
                    "\t\t{'answ4':'Respuesta 4' ,'isCorrect':'0'}]\n" +
                    "\t\t\t\n" +
                    "\t\t}}");
            Log.d("Test","se creo jobject");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.circle_menu:
                toggle();
                break;

            case R.id.btn_menu_practice:
                Intent intPractice = new Intent(MainActivity.this, TestActivity.class);
                intPractice.putExtra("type",1);
                startActivity(intPractice);
                break;
            case R.id.btn_menu_challenge:
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        DialogHelper.showChallengeDialog(MainActivity.this);
                    }
                });
                break;
            case R.id.btn_menu_tutor:
                Intent intLibrary = new Intent(MainActivity.this, LibraryActivity.class);
                startActivity(intLibrary);
                break;
            case R.id.btn_menu_shop:
                Intent intent = new Intent(MainActivity.this, ShopActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_menu_ranking:
                Intent intent1 = new Intent(MainActivity.this, RankingActivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_settings:
                Intent intents = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intents);
                break;
            default:
                break;
        }
    }

    private void toggle(){
        if(gifMenu.getVisibility() == View.GONE){
            gifMenu.setVisibility(View.VISIBLE);

        }else{
            gifMenu.setVisibility(View.GONE);

        }

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }



}
