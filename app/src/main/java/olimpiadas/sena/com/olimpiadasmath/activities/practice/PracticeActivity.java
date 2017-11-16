package olimpiadas.sena.com.olimpiadasmath.activities.practice;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.challenge.ChallengeActivity;
import olimpiadas.sena.com.olimpiadasmath.activities.menu.MainActivity;
import olimpiadas.sena.com.olimpiadasmath.activities.result.ResultActivity;
import olimpiadas.sena.com.olimpiadasmath.activities.test.CardItem;
import olimpiadas.sena.com.olimpiadasmath.activities.test.ShadowTransformer;
import olimpiadas.sena.com.olimpiadasmath.activities.test.ViewPagerPersonalizado;
import olimpiadas.sena.com.olimpiadasmath.adapter.test.CardFragmentPagerAdapter;
import olimpiadas.sena.com.olimpiadasmath.adapter.test.CardPagerAdapter;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.model.Question;
import olimpiadas.sena.com.olimpiadasmath.util.DialogHelper;
import olimpiadas.sena.com.olimpiadasmath.util.webConManager.WebConnectionManager;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PracticeActivity extends AppCompatActivity implements CardPagerAdapter.CommunicationTest, CardPagerAdapter.MoveTestListener ,View.OnClickListener,
        CompoundButton.OnCheckedChangeListener, WebConnectionManager.WebConnectionManagerListener {

    private static final String TAG = "PracticeActivity";
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
    LinearLayout lnFragmentPractice;

    Chronometer chronometer; // Cronometro

    //Inicio de la practica
    private boolean initTest = false;

    //
    TextView tvTetTipNumQuet;
    Button btnBackChallenge;
    ImageView btn_settings_header;
    List<Question> rs;
    Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        getSupportActionBar().hide(); // Quitando la barra superior de la app
        // Control de la app
        appControl = AppControl.getInstance();
        appControl.currentActivity = PracticeActivity.class.getSimpleName();
        appControl.onPractice =  true;
        //init true
        initTest = true;
        appControl = AppControl.getInstance();


        //Fragment Header
        fragHeader = findViewById(R.id.fragment_test_header);
        btn_settings_header = (ImageView) findViewById(R.id.btn_settings_header);
        btn_settings_header.setVisibility(View.GONE);
        lnPractice = (LinearLayout) findViewById(R.id.ln_practice);
        lnFragmentPractice = (LinearLayout) findViewById(R.id.lnFragmentPractice);
        //Referencias
        tvTetTipNumQuet = (TextView) findViewById(R.id.tv_test_tip_numberofquestion); // Numero de Pregunta
        chronometer = (Chronometer) findViewById(R.id.chronometer_clocked);//Cronometro
        timeChallenge();

        mViewPager = (ViewPagerPersonalizado) findViewById(R.id.viewPager);
        mViewPager.setPagingEnabled(false);
        realm = Realm.getDefaultInstance();

         rs =  realm.where(Question.class).findAll();

        if(rs.size() < appControl.numberOfQuestions  ){
            if(verificarConexion(this)){
                Toast.makeText(this,"Estamos cargando preguntas",Toast.LENGTH_SHORT).show();
                DialogHelper.showBusyDialog(this,"Estamos Cargando preguntas");
                WebConnectionManager webConnectionManager = WebConnectionManager.getWebConnectionManager();
                webConnectionManager.setWebConnectionManagerListener(this);
                webConnectionManager.getQuestions();
            }else{
                Toast.makeText(this,"Te quedaste sin preguntas, porfavor conectate a internet",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this,MainActivity.class));
                finish();

            }

        }else{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    rs = new ArrayList<>();
                    for(int i = 0 ; i < appControl.numberOfQuestions; i++){

                        Question tempQuestion= realm.where(Question.class).findFirst();
                        rs.add(realm.copyFromRealm(tempQuestion));
                        tempQuestion.deleteFromRealm();
                    }
                }
            });

        }

        mCardAdapter = new CardPagerAdapter(rs,this);
        mCardAdapter.addCardItem(new CardItem(R.string.title_1, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_2, R.string.text_1));
        mCardAdapter.addCardItem(new CardItem(R.string.title_3, R.string.text_1));
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
        btnBackChallenge = (Button) findViewById(R.id.btn_test_back);//ponerle efecto de sonido


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
        appControl.currentTime = ( SystemClock.elapsedRealtime() -  chronometer.getBase())/1000;
        appControl.onPractice = true;
        startActivity(new Intent(this, ResultActivity.class));
    }

    @Override
    public void changeScale() {
        scaled = !scaled;

        if (scaled) {
            lnPractice.setVisibility(View.VISIBLE);
            lnFragmentPractice.setVisibility(View.GONE);

        } else {
            lnPractice.setVisibility(View.VISIBLE);
            lnFragmentPractice.setVisibility(View.VISIBLE);
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

    public boolean verificarConexion(Context context) {
        boolean bConectado = false;
        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] redes = connec.getAllNetworkInfo();
        for (int i = 0; i < 2; i++) {
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                bConectado = true;
            }
        }
        return bConectado;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void webRequestComplete(WebConnectionManager.Response response) throws JSONException {
        Log.d(TAG,"Entro a webRequestComplete ");
        if (response.getOperationType() == WebConnectionManager.OperationType.GET_QUESTIONS) {
            if (response.getStatus() == WebConnectionManager.Response.SUCCESS) {
                Log.d(TAG,response.getData());
                JSONArray jsonArray = new JSONArray(response.getData());
//        JSONArray jsonArray = new JSONArray(data());
                List<Question> qs = Question.JsonArrayToList(jsonArray);

                for (Question q : qs) {
                    Log.e("Question", q.getJsonObject());
                }
                LoadQuestionsFromWebService(qs);
                DialogHelper.hideBusyDialog();


            }
        }
    }


    public void LoadQuestionsFromWebService(final List<Question> questionList) {

        Realm realmTemp = Realm.getDefaultInstance();
        realmTemp.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Question> questionsTemp = realm.where(Question.class).findAll();
                questionsTemp.deleteAllFromRealm();
                realm.copyToRealmOrUpdate(questionList);

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {

                for (int i = 1; i < questionList.size(); i++) {
                    Log.d("JSON", questionList.get(i).toString());
                }
                //questions = questionList;
                appControl.currentQuestion = 0;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        
                        mCardAdapter = new CardPagerAdapter(realm.where(Question.class).findAll().subList(0,appControl.numberOfQuestions),PracticeActivity.this);
                        mCardAdapter.addCardItem(new CardItem(R.string.title_1, R.string.text_1));
                        mCardAdapter.addCardItem(new CardItem(R.string.title_2, R.string.text_1));
                        mCardAdapter.addCardItem(new CardItem(R.string.title_3, R.string.text_1));
                        mCardAdapter.addCardItem(new CardItem(R.string.title_4, R.string.text_1));
                        mCardAdapter.addCardItem(new CardItem(R.string.title_4, R.string.text_1));
                        mCardAdapter.setCommunicationTest(PracticeActivity.this);
                        mCardAdapter.setMoveTestListener(PracticeActivity.this);
                        mViewPager.setCardAdapter(mCardAdapter);
                        totalPage = mCardAdapter.getCount();

                    }
                });

            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
                Log.d(TAG,"Database save questions error " + error.getMessage());
            }
        });


    }
}
