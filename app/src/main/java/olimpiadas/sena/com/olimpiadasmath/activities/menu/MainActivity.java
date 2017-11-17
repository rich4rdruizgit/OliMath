package olimpiadas.sena.com.olimpiadasmath.activities.menu;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hitomi.cmlibrary.OnMenuSelectedListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.library.LibraryActivity;
import olimpiadas.sena.com.olimpiadasmath.activities.practice.PracticeActivity;
import olimpiadas.sena.com.olimpiadasmath.activities.ranking.RankingActivity;
import olimpiadas.sena.com.olimpiadasmath.activities.settings.SettingsActivity;
import olimpiadas.sena.com.olimpiadasmath.activities.shop.ShopActivity;
import olimpiadas.sena.com.olimpiadasmath.activities.test.TestActivity;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.librerias.CircleMenu;
import olimpiadas.sena.com.olimpiadasmath.util.DialogHelper;
import olimpiadas.sena.com.olimpiadasmath.util.webConManager.NetworkStateReceiver;
import olimpiadas.sena.com.olimpiadasmath.util.webConManager.WebConnectionManager;
import pl.droidsonroids.gif.GifImageView;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, NetworkStateReceiver.NetworkStateReceiverListener {

    private static final String TAG = "MainActivity";
    String arrayName[] = {"Practice", "Study", "Challenge"};
    CircleMenu circleMenu;
    Button btnShop, btnRanking, btnSettings, btnPractice, btnChallenge, btnLibrary;
    GifImageView gifMenu;
    AppControl appControl;

    Context context;
    pl.droidsonroids.gif.GifImageView getGifMenu;
    int cont = 0;
    public static MediaPlayer sound;
    private NetworkStateReceiver networkStateReceiver;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Verificar si hay internet

        networkStateReceiver = new NetworkStateReceiver();
        networkStateReceiver.addListener(this);
        this.registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
        //------------------------------------------------------------------------------------------

        gifMenu = (GifImageView) findViewById(R.id.img_gif_menu); // Gif del menu principal
        //gifMenu.setOnClickListener(this);
        gifMenu.setBackgroundResource(R.drawable.teachertalk);
        //chagedPJ();

        /*AnimationDrawable channgePJ= new AnimationDrawable();
        channgePJ.addFrame(getResources().getDrawable(R.drawable.teacherhello),300);
        channgePJ.addFrame(getResources().getDrawable(R.drawable.teacheridle),300);
        channgePJ.addFrame(getResources().getDrawable(R.drawable.teachertalk),300);
        channgePJ.setOneShot(false);*/
        //gifMenu.setBackgroundResource(channgePJ);
        //gifMenu.setBackground(channgePJ);
        //gifMenu.setImageDrawable(channgePJ);
        getSupportActionBar().hide();
        appControl = AppControl.getInstance();
        appControl.currentActivity = MainActivity.class.getSimpleName();
        Context context = this;


    /*TimerTask tareaTiempo = new TimerTask() {
        @Override
        public void run() {
            Intent intent = new Intent(SplashPaisajeActivity.this, MainActivity.class);
            startActivity(intent);
            finish();

        }
    };

    Timer timer = new Timer();
        timer.schedule(tareaTiempo,3000);*/
        // fin logica cambio personaje app


//        appControl.soundBackground = MediaPlayer.create(getApplicationContext(),R.raw.theartloop);

//        if(appControl.isBackgroundPlaying){
//
//            if(!appControl.soundBackground.isPlaying()){
//                appControl.soundBackground.start();
//                appControl.soundBackground.setLooping(true);
//                Log.e("REPRODUCION_SONIDO", TAG);
//            }
//
//        }

        int id = context.getResources().getIdentifier("fondo_b.png", "drawable", context.getPackageName());
        int resID = getResources().getIdentifier("fondo_b", "drawable", getPackageName());
        Log.d(TAG, "el id del recurso es " + resID);

        resID = getResources().getIdentifier("fondo_b", "drawable", getPackageName());
        Log.d(TAG, "el id del recurso es " + resID);
        // CONSULTA DE UN WEBSERVICES
        WebConnectionManager webConnectionManager = WebConnectionManager.getWebConnectionManager();
        Log.d(TAG,"Se va a consultar");
        /**/

        int[] hola = new int[10];
         //webConnectionManager.sendChallenge(new int[10],"1234","1234");

        // webConnectionManager.insertQuestion("http://10.73.120.221:8097/WSPreguntas.asmx/");
        btnShop = (Button) findViewById(R.id.btn_menu_shop);
        btnShop.setOnClickListener(this);

        btnRanking = (Button) findViewById(R.id.btn_menu_ranking);
        btnRanking.setOnClickListener(this);

        btnPractice = (Button) findViewById(R.id.btn_menu_practice);
        btnPractice.setOnClickListener(this);

        btnChallenge = (Button) findViewById(R.id.btn_menu_challenge);
        btnChallenge.setOnClickListener(this);


//
//        btnSettings = (Button) findViewById(R.id.btn_settings);
//        btnSettings.setOnClickListener(this);
//        btnLibrary = (Button) findViewById(R.id.btn_menu_tutor);
//        btnLibrary.setOnClickListener(this);

        //test

        try {



            Log.d("Test", "se va a obtener el usuario " + appControl.currentUser.getExperience());


            Log.d("Test", "se va a crear jobject");
            JSONObject test = new JSONObject("{'question':'Me comi una manzana....',\n" +
                    "\t\t'answers':[\n" +
                    "\t\t{'answ1':'Respuesta 1' ,'isCorrect':'0'},\n" +
                    "\t\t{'answ2':'Respuesta 2' ,'isCorrect':'0'},\n" +
                    "\t\t{'answ3':'Respuesta 3' ,'isCorrect':'1'},\n" +
                    "\t\t{'answ4':'Respuesta 4' ,'isCorrect':'0'}]\n" +
                    "\t\t\t\n" +
                    "\t\t}}");
            Log.d("Test", "se creo jobject");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //logica cambio personaje app
    public void delay3seconds(){
        try {
            Thread.sleep(3000);
        }catch (InterruptedException e){

        }
    }

    public void chagedPJ(){
        ArrayList<Integer> gifs = new ArrayList<>();
        gifs.add(R.drawable.teacherhello);
        gifs.add(R.drawable.teacheridle);
        gifs.add(R.drawable.teachertalk);
        int i=1;
        while(i<4){
            delay3seconds();
            Toast.makeText(this, "hghg", Toast.LENGTH_SHORT).show();
            if(i == 3){
                i = 1;
            }
            gifMenu.setBackgroundResource(gifs.get(i));
            i++;
        }

    }

    @Override
    public void onClick(View v) {
        appControl.soundButtonPlay();
        switch (v.getId()) {

            case R.id.btn_menu_practice:
//                Intent intPractice = new Intent(MainActivity.this, PracticeActivity.class);
//                startActivity(intPractice);
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        DialogHelper.showChallengePracticeDialog(MainActivity.this, 1);
                    }
                });
                break;
            case R.id.btn_menu_challenge:
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        DialogHelper.showChallengePracticeDialog(MainActivity.this, 2);
                    }
                });
                break;
            case R.id.btn_menu_shop:
                Intent intentShop = new Intent(MainActivity.this, ShopActivity.class);
                startActivity(intentShop);
                break;
            case R.id.btn_menu_ranking:
                appControl.currentTime = 0;
                Intent intentRanking = new Intent(MainActivity.this, RankingActivity.class);
                startActivity(intentRanking);
                break;
//            case R.id.btn_settings:
//                Intent intentSettings = new Intent(MainActivity.this, SettingsActivity.class);
//                startActivity(intentSettings);
//                break;
//            case R.id.btn_menu_tutor:
//                Intent intentTutor = new Intent(MainActivity.this, LibraryActivity.class);
//                startActivity(intentTutor);
//                break;
            default:
                break;
        }
    }

    private void toggle() {
        if (gifMenu.getVisibility() == View.GONE) {
            gifMenu.setVisibility(View.VISIBLE);

        } else {
            gifMenu.setVisibility(View.GONE);
        }

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    public void onBackPressed() {

        DialogHelper.ConfimrExitDialog(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"OnDestroy");
        //if(appControl.soundBackground.isPlaying())
          //  appControl.soundBackground.pause();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"OnPause");

//        if(appControl.soundBackground.isPlaying())
//            appControl.soundBackground.pause();
    }

    @Override
    protected void onResume() {
        Log.d(TAG,"OnResume");
        super.onResume();

//        if(appControl.soundBackground.isPlaying())
//            appControl.soundBackground.pause();
    }

    @Override
    public void networkAvailable() {
        btnPractice.setBackground(this.getResources().getDrawable(R.drawable.practact));
        btnShop.setBackground(this.getResources().getDrawable(R.drawable.shopact));
        btnRanking.setBackground(this.getResources().getDrawable(R.drawable.rankact));
        btnChallenge.setBackground(this.getResources().getDrawable(R.drawable.changact));;
    }

    @Override
    public void networkUnavailable() {
        btnPractice.setBackground(this.getResources().getDrawable(R.drawable.practact));
        btnShop.setBackground(this.getResources().getDrawable(R.drawable.shopdes));
        btnRanking.setBackground(this.getResources().getDrawable(R.drawable.rankdes));
        btnChallenge.setBackground(this.getResources().getDrawable(R.drawable.chandes));
        btnShop.setEnabled(false);
        btnRanking.setEnabled(false);
        btnChallenge.setEnabled(false);
    }
}
