package olimpiadas.sena.com.olimpiadasmath.activities.practice;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.realm.Realm;
import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.menu.MainActivity;
import olimpiadas.sena.com.olimpiadasmath.activities.result.ResultActivity;
import olimpiadas.sena.com.olimpiadasmath.activities.test.CardItem;
import olimpiadas.sena.com.olimpiadasmath.activities.test.ShadowTransformer;
import olimpiadas.sena.com.olimpiadasmath.activities.test.TestActivity;
import olimpiadas.sena.com.olimpiadasmath.activities.test.ViewPagerPersonalizado;
import olimpiadas.sena.com.olimpiadasmath.adapter.test.CardFragmentPagerAdapter;
import olimpiadas.sena.com.olimpiadasmath.adapter.test.CardPagerAdapter;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.model.Question;
import olimpiadas.sena.com.olimpiadasmath.util.DialogHelper;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PracticeActivity extends AppCompatActivity implements CardPagerAdapter.CommunicationTest, CardPagerAdapter.MoveTestListener ,View.OnClickListener,
        CompoundButton.OnCheckedChangeListener{

    AppControl appControl;

    //lo del CardView
    private ViewPagerPersonalizado mViewPager;
    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    private CardFragmentPagerAdapter mFragmentCardAdapter;
    private ShadowTransformer mFragmentCardShadowTransformer;

    //Contador de pregunta
    int countPage = 1;
    int totalPage=0;

    //Scala
    boolean scaled = false;
    View fragHeader;
    LinearLayout lnPractice;

    Chronometer chronometer; // Cronometro

    //Inicio de la practica
    private boolean initTest = false;

    //
    TextView tvTetTipNumQuet;
    Button btnBackChallenge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        getSupportActionBar().hide(); // Quitando la barra superior de la app
        // Control de la app
        appControl = AppControl.getInstance();

        appControl.onPractice =  true;
        //init true
        initTest = true;

        //Fragment Header
        fragHeader = findViewById(R.id.fragment_test_header);
        lnPractice = (LinearLayout) findViewById(R.id.ln_practice);
        //Referencias
        tvTetTipNumQuet = (TextView) findViewById(R.id.tv_test_tip_numberofquestion); // Numero de Pregunta
        chronometer = (Chronometer) findViewById(R.id.chronometer_clocked);//Cronometro
        timeChallenge();

        mViewPager = (ViewPagerPersonalizado) findViewById(R.id.viewPager);
        mViewPager.setPagingEnabled(false);
        Realm realm = Realm.getDefaultInstance();

        mCardAdapter = new CardPagerAdapter(realm.where(Question.class).findAll(),this);
        mCardAdapter.addCardItem(new CardItem(R.string.title_1, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_2, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_3, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_4, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_4, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_4, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_4, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_4, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_4, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_4, R.string.text_1));

        mFragmentCardAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(),
                dpToPixels(2, this));

        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
        mFragmentCardShadowTransformer = new ShadowTransformer(mViewPager, mFragmentCardAdapter);

        mViewPager.setCardAdapter(mCardAdapter);
        mCardAdapter.setCommunicationTest(this);
        mCardAdapter.setMoveTestListener(this);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(10);

        totalPage=  mCardAdapter.getCount();
        tvTetTipNumQuet.setText(countPage+"/"+mCardAdapter.getCount());
        btnBackChallenge = (Button) findViewById(R.id.btn_test_back);


    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    public void timeChallenge(){
        chronometer.start();
    }

    @Override
    public void moveClick(int dir) {
        countPage = dir + 1;
        tvTetTipNumQuet.setText(countPage+"/"+totalPage);
        mViewPager.setCurrentItem(dir);
    }

    @Override
    public void finished() {
        startActivity(new Intent(this, ResultActivity.class));
    }

    @Override
    public void changeScale() {
        scaled = !scaled;

        if (scaled) {
            lnPractice.setVisibility(View.VISIBLE);
            fragHeader.setVisibility(View.GONE);

        } else {
            lnPractice.setVisibility(View.VISIBLE);
            fragHeader.setVisibility(View.VISIBLE);
        }
        mCardShadowTransformer.enableScaling(scaled);
        mFragmentCardShadowTransformer.enableScaling(scaled);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mCardShadowTransformer.enableScaling(isChecked);
        mFragmentCardShadowTransformer.enableScaling(isChecked);
    }

    @Override
    public void onBackPressed() {
        if(appControl.onPractice){
            if(initTest){
                DialogHelper.ConfimrFinishTestDialog(this,"Seguro que quieres terminar la practica? \nPerderas lo apostado");
            }else{
                finish();
                startActivity(new Intent(PracticeActivity.this, MainActivity.class));
            }
        }else{
            DialogHelper.ConfimrFinishTestDialog(this,"Seguro que quieres terminar el reto? \nPerderas los créditos que pagaste para ingresar y se contará como perdido");
        }
    }

    //Ajuste de la fuente de la letra
    protected void attachBaseContext (Context newBase){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
