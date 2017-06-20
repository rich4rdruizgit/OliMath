package olimpiadas.sena.com.olimpiadasmath.activities.menu;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hitomi.cmlibrary.OnMenuSelectedListener;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.ranking.RankingActivity;
import olimpiadas.sena.com.olimpiadasmath.activities.shop.ShopActivity;
import olimpiadas.sena.com.olimpiadasmath.librerias.CircleMenu;
import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    String arrayName[] = {"Practice","Study","Challenge"};
    CircleMenu circleMenu;
    Button btnShop, btnRanking;
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

        btnRanking = (Button) findViewById(R.id.btn_menu_ranking);
        btnRanking.setOnClickListener(this);

        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);
        circleMenu.setOnClickListener(this);
        circleMenu.setVisibility(View.GONE);
        circleMenu.setCloseAction(gifMenu,circleMenu);
        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"),R.drawable.alberte,R.drawable.alberte).addSubMenu(Color.parseColor("#258CFF"),R.drawable.practicar).
                addSubMenu(Color.parseColor("#6d4c41"),R.drawable.study).addSubMenu(Color.parseColor("#FF0000"),R.drawable.challenge)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {
                    @Override
                    public void onMenuSelected(int i) {
                        Toast.makeText(MainActivity.this, "Seleccionaste "+ arrayName[i], Toast.LENGTH_SHORT).show();
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
            case R.id.btn_menu_ranking:
                Intent intent1 = new Intent(MainActivity.this, RankingActivity.class);
                startActivity(intent1);
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
