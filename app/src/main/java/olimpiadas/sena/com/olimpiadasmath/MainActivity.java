package olimpiadas.sena.com.olimpiadasmath;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class MainActivity extends AppCompatActivity {

    String arrayName[] = {"Practice","Study","Challenge"};
    CircleMenu circleMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

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
}
