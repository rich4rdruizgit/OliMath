package olimpiadas.sena.com.olimpiadasmath.activities.menu;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hitomi.cmlibrary.OnMenuSelectedListener;

import java.util.Timer;
import java.util.TimerTask;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.shop.ShopActivity;
import olimpiadas.sena.com.olimpiadasmath.activities.test.TestActivity;
import olimpiadas.sena.com.olimpiadasmath.librerias.CircleMenu;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    String prac ="Practice";
    String study ="Study";
    String chall ="Challenge";
    String arrayName[] = {prac, study, chall};
    CircleMenu circleMenu;
    Button btnShop;
    GifImageView gifMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gifMenu = (GifImageView) findViewById(R.id.img_gif_menu); // Gif del menu principal
        gifMenu.setOnClickListener(this);
        getSupportActionBar().hide();


        btnShop = (Button) findViewById(R.id.btn_menu_shop);
        btnShop.setOnClickListener(this);

        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);
        circleMenu.setOnClickListener(this);
        circleMenu.setVisibility(View.GONE);
        circleMenu.setCloseAction(gifMenu,circleMenu);
        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"),R.drawable.albertee,R.drawable.albertee).addSubMenu(Color.parseColor("#258CFF"),R.drawable.practicar).
                addSubMenu(Color.parseColor("#6d4c41"),R.drawable.study).addSubMenu(Color.parseColor("#FF0000"),R.drawable.challenge)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int i) {
                        Toast.makeText(MainActivity.this, "Seleccionaste "+ arrayName[i], Toast.LENGTH_SHORT).show();
                        switch (i){
                            case 0:
                                TimerTask tareap =  new TimerTask() {
                                    @Override
                                    public void run() {
                                        Intent intPractice = new Intent(MainActivity.this, TestActivity.class);
                                        startActivity(intPractice);
                                    }
                                };
                                Timer timerp = new Timer();
                                timerp.schedule(tareap,1000);
                                break;
                            case 2:

                                TimerTask tareac =  new TimerTask() {
                                    @Override
                                    public void run() {
                                        Intent intChallenge = new Intent(MainActivity.this, TestActivity.class);
                                        startActivity(intChallenge);
                                    }
                                };
                                Timer timerc = new Timer();
                                timerc.schedule(tareac,1000);
                                break;

                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_gif_menu:
                toggle();
                circleMenu.openMenu();
                break;
            case R.id.circle_menu:
                toggle();
                break;
            case R.id.btn_menu_shop:
                Intent intent = new Intent(MainActivity.this, ShopActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void toggle(){
        if(gifMenu.getVisibility() == View.GONE){
            gifMenu.setVisibility(View.VISIBLE);
            circleMenu.setVisibility(View.GONE);
        }else{
            gifMenu.setVisibility(View.GONE);
            circleMenu.setVisibility(View.VISIBLE);
        }

    }


}
