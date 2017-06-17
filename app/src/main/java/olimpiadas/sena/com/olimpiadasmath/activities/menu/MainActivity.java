package olimpiadas.sena.com.olimpiadasmath.activities.menu;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.shop.ShopActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    String arrayName[] = {"Practice","Study","Challenge"};
    CircleMenu circleMenu;
    Button btnShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        btnShop = (Button) findViewById(R.id.btn_menu_shop);
        btnShop.setOnClickListener(this);

        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);
        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"),R.drawable.albert,R.drawable.albert).addSubMenu(Color.parseColor("#258CFF"),R.drawable.practicar).
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
            case R.id.btn_menu_shop:
                Intent intent = new Intent(MainActivity.this, ShopActivity.class);
                startActivity(intent);
            break;
            default:
                break;
        }
    }
}
