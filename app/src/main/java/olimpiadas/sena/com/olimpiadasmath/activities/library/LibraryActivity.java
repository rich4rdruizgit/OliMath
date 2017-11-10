package olimpiadas.sena.com.olimpiadasmath.activities.library;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.menu.MainActivity;
import olimpiadas.sena.com.olimpiadasmath.adapter.library.TopicAdapter;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
/* Modificado por Mile 17/07/2017*/
public class LibraryActivity extends AppCompatActivity implements View.OnClickListener{

    TopicAdapter adapter;
    RecyclerView listElements;
    AppControl appControl;
    ImageButton btnBackSetting;
    Button btnEquations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        getSupportActionBar().hide();

        btnBackSetting = (ImageButton) findViewById(R.id.btn_back_tutor);
        btnBackSetting.setOnClickListener(this);

        btnEquations = (Button) findViewById(R.id.btn_equations_library);
        btnEquations.setOnClickListener(this);

        appControl = AppControl.getInstance();

        /*listElements=(RecyclerView) findViewById(R.id.recycler_library);
        listElements.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        listElements.setLayoutManager(llm);
        adapter = new TopicAdapter(this);
        listElements.setAdapter(adapter);*/

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onClick(View v) {
        appControl.soundButton = MediaPlayer.create(getApplicationContext(),appControl.soundButtonEfect);
        switch (v.getId()){
            case R.id.btn_back_tutor:
                if(appControl.isBackgroundPlaying)
                    if(appControl.isBackgroundPlaying)
                        appControl.soundButton.start();
                Intent intentBack = new Intent(LibraryActivity.this, MainActivity.class);
                startActivity(intentBack);
                break;
            case R.id.btn_equations_library:
                if(appControl.isBackgroundPlaying)
                    if(appControl.isBackgroundPlaying)
                        appControl.soundButton.start();
                Intent intentGoEquetions = new Intent(LibraryActivity.this, EquationsActivity.class);
                startActivity(intentGoEquetions);

        }
    }
}



