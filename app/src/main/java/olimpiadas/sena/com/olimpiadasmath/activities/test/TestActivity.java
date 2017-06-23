package olimpiadas.sena.com.olimpiadasmath.activities.test;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.adapter.test.CardFragmentPagerAdapter;
import olimpiadas.sena.com.olimpiadasmath.adapter.test.CardPagerAdapter;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class TestActivity extends AppCompatActivity implements View.OnClickListener,
        CompoundButton.OnCheckedChangeListener, Communication {

    private Button mButton;
    private ViewPager mViewPager;

    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    private CardFragmentPagerAdapter mFragmentCardAdapter;
    private ShadowTransformer mFragmentCardShadowTransformer;

    private boolean mShowingFragments = false;
    int flag=0;
    int flagBet = 0;
    View fragBet;
    View fragPractice;
    View fragChallenge;
    LinearLayout linearPractice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        getSupportActionBar().hide();
        flag = getIntent().getExtras().getInt("type"); // con esto miramos si es una practica o un challenge

        // Cargando los fragments
        fragBet = findViewById(R.id.fragment_test_apuesta);
        fragPractice = findViewById(R.id.fragment_tip_test);
        fragChallenge  = findViewById(R.id.fragment_timer);

        linearPractice = (LinearLayout) findViewById(R.id.linear_practice);
        // Se controla que fragment aparece si es practica  = 1 o challenge = 2

        if(flagBet == 0){
            fragBet.setVisibility(View.VISIBLE);
            fragPractice.setVisibility(View.GONE);
            fragChallenge.setVisibility(View.GONE);
            linearPractice.setVisibility(View.GONE);
        }

        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        mButton = (Button) findViewById(R.id.cardTypeBtn);
        CheckBox ch = (CheckBox) findViewById(R.id.checkBox);
        ch.setOnCheckedChangeListener(this);
        //((CheckBox) findViewById(R.id.checkBox))

          //      .setOnCheckedChangeListener(this);
        mButton.setOnClickListener(this);

        mCardAdapter = new CardPagerAdapter();
        mCardAdapter.addCardItem(new CardItem(R.string.title_1, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_2, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_3, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_4, R.string.text_1));
        mFragmentCardAdapter = new CardFragmentPagerAdapter(getSupportFragmentManager(),
                dpToPixels(2, this));

        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
        mFragmentCardShadowTransformer = new ShadowTransformer(mViewPager, mFragmentCardAdapter);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
    }


    @Override
    public void onClick(View view) {
        if (!mShowingFragments) {

            mButton.setText("Views");
            mViewPager.setAdapter(mFragmentCardAdapter);
            mViewPager.setPageTransformer(false, mFragmentCardShadowTransformer);

        } else {
            mButton.setText("Fragments");
            mViewPager.setAdapter(mCardAdapter);
            mViewPager.setPageTransformer(false, mCardShadowTransformer);
        }

        mShowingFragments = !mShowingFragments;
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
        if(flagBet != 0){
            if(flag == 1){
                fragPractice.setVisibility(View.VISIBLE);
                linearPractice.setVisibility(View.VISIBLE);
                fragBet.setVisibility(View.GONE);
                fragChallenge.setVisibility(View.GONE);
            }else if(flag == 2){
                fragBet.setVisibility(View.GONE);
                fragPractice.setVisibility(View.GONE);
                fragChallenge.setVisibility(View.VISIBLE);
                linearPractice.setVisibility(View.VISIBLE);
            }
        }
        return flagBet;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}

