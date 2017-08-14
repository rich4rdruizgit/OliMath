package olimpiadas.sena.com.olimpiadasmath.activities.test;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import io.realm.Realm;
import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.result.ResultActivity;
import olimpiadas.sena.com.olimpiadasmath.adapter.test.CardFragmentPagerAdapter;
import olimpiadas.sena.com.olimpiadasmath.adapter.test.CardPagerAdapter;

import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.model.Question;
import olimpiadas.sena.com.olimpiadasmath.util.DialogHelper;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class TestActivity extends AppCompatActivity implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener, Communication , CardPagerAdapter.CommunicationTest, CardPagerAdapter.MoveTestListener {

    private Button mButton;
    private ViewPagerPersonalizado mViewPager;

    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    private CardFragmentPagerAdapter mFragmentCardAdapter;
    private ShadowTransformer mFragmentCardShadowTransformer;

    private boolean mShowingFragments = false;

    int flag = 0;
    int flagBet = 0;
    int betcoin=0;



    SeekBar seekBar;
    TextView tvBet;

    int countPage = 1;
    int totalPage=0;
    int flagBackCountChallenge=0;


    int page = 1;

    boolean scaled = false;
    boolean imaged = false;
    View fragHeader;
    View fragBet;

    View fragPractice;
    View fragChallenge;

    LinearLayout linearPractice;
    LinearLayout lnPractice;
    LinearLayout lnChallenge;

    TextView chronometer;
    pl.droidsonroids.gif.GifImageView img_test_tip_einstein;
    TextView tvTestNumQuest;
    TextView tvTetTipNumQuet;

    Button btnBackChallenge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        getSupportActionBar().hide();
        flag = getIntent().getExtras().getInt("type"); // con esto miramos si es una practica o un challenge
        if(flag==1){
            AppControl.getInstance().onPractice = true;
        }else{
            AppControl.getInstance().onChallenge =true;
        }
        // Cargando los fragments
        fragHeader = findViewById(R.id.fragment_test_header);
        fragBet = findViewById(R.id.fragment_test_apuesta);


        linearPractice = (LinearLayout) findViewById(R.id.linear_practice);
        lnPractice = (LinearLayout) findViewById(R.id.ln_practice);
        lnChallenge = (LinearLayout) findViewById(R.id.ln_challenge);

        // Se controla que fragment aparece si es practica  = 1 o challenge = 2

        // Apuesta
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        tvBet = (TextView) findViewById(R.id.tv_bet);

        if (flagBet == 0) {
            if(AppControl.getInstance().onChallenge){
                AppControl.getInstance().onChallenge = true;
                AppControl.getInstance().onPractice = false;
                timeChallenge();
                fragBet.setVisibility(View.GONE);
                lnPractice.setVisibility(View.GONE);
                lnChallenge.setVisibility(View.VISIBLE);
                linearPractice.setVisibility(View.VISIBLE);
            }
            if(AppControl.getInstance().onPractice){
                AppControl.getInstance().onChallenge = false;
                AppControl.getInstance().onPractice = true;
                fragBet.setVisibility(View.VISIBLE);
                lnPractice.setVisibility(View.GONE);
                lnChallenge.setVisibility(View.GONE);
                linearPractice.setVisibility(View.GONE);
            }
        }

        mViewPager = (ViewPagerPersonalizado) findViewById(R.id.viewPager);
        Realm realm = Realm.getDefaultInstance();

        mCardAdapter = new CardPagerAdapter(realm.where(Question.class).findAll());
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

        if (AppControl.getInstance().currentUser.getCoins() < 100) {
            seekBar.setMax(AppControl.getInstance().currentUser.getCoins());
        }
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvBet.setText(progress+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        chronometer = (TextView) findViewById(R.id.chronometer_clock);

        img_test_tip_einstein = (pl.droidsonroids.gif.GifImageView) findViewById(R.id.img_test_tip_einstein);
        img_test_tip_einstein.setOnClickListener(this);
        tvTestNumQuest = (TextView) findViewById(R.id.tv_test_numquest);
        tvTestNumQuest.setText(countPage+"/"+mCardAdapter.getCount());
        totalPage=  mCardAdapter.getCount();
        tvTetTipNumQuet = (TextView) findViewById(R.id.tv_test_tip_numberofquestion);
        tvTetTipNumQuet.setText(countPage+"/"+mCardAdapter.getCount());

        btnBackChallenge = (Button) findViewById(R.id.btn_test_back);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_test_tip_einstein:
                DialogHelper.showTipDialog(view.getContext());
                break;
        }
    }

    public static float dpToPixels(int dp, Context context) {
        return dp * (context.getResources().getDisplayMetrics().density);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        mCardShadowTransformer.enableScaling(b);
        mFragmentCardShadowTransformer.enableScaling(b);
    }

    @Override
    public int sendBet(int bet) {
        flagBet = bet;
        if (flagBet != 0) {
            if (flag == 1) {
                AppControl.getInstance().onPractice = true;
                AppControl.getInstance().onChallenge = false;
                lnPractice.setVisibility(View.VISIBLE);
                linearPractice.setVisibility(View.VISIBLE);
                fragBet.setVisibility(View.GONE);
                lnChallenge.setVisibility(View.GONE);
            }
        }
        return flagBet;
    }

    public void timeChallenge(){
        final CountDownTimer timer = new CountDownTimer(120000, 1000) {

            public void onTick(long millisUntilFinished) {
                String v = String.format("%02d", millisUntilFinished/60000);
                int va = (int)( (millisUntilFinished%60000)/1000);
                chronometer.setText(v+":"+String.format("%02d",va));
            }

            public void onFinish() {
                chronometer.setText("Time Up!");
            }
        }.start();

    }

    @Override


    public void changeScale() {
        scaled = !scaled;

        if (scaled) {
            if(flag==1){
                lnPractice.setVisibility(View.VISIBLE);
                lnChallenge.setVisibility(View.GONE);
                fragHeader.setVisibility(View.GONE);
            }else if(flag==2){
                lnPractice.setVisibility(View.GONE);
                lnChallenge.setVisibility(View.VISIBLE);
                fragHeader.setVisibility(View.GONE);
            }

        } else {
            if(flag == 1){
                lnPractice.setVisibility(View.VISIBLE);
                lnChallenge.setVisibility(View.GONE);
                fragHeader.setVisibility(View.VISIBLE);
            }else if(flag ==2 ){
                lnPractice.setVisibility(View.GONE);
                lnChallenge.setVisibility(View.VISIBLE);
                fragHeader.setVisibility(View.VISIBLE);
            }
        }
        mCardShadowTransformer.enableScaling(scaled);
        mFragmentCardShadowTransformer.enableScaling(scaled);

    }


    @Override
    public void moveClick(int dir) {
        if(flag == 1){
            countPage = dir + 1;
            tvTetTipNumQuet.setText(countPage+"/"+totalPage);
            mViewPager.setCurrentItem(dir);
        }else if(flag == 2){
            countPage = dir + 1;
            tvTestNumQuest.setText(countPage+"/"+totalPage);
            tvTetTipNumQuet.setText(countPage+"/"+totalPage);
            mViewPager.setCurrentItem(dir);
        }

    }

    @Override
    public void finished() {

        startActivity(new Intent(this, ResultActivity.class));
    }

    protected void attachBaseContext (Context newBase){
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));

    }
}