package olimpiadas.sena.com.olimpiadasmath.activities.result;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import io.realm.Realm;
import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.menu.MainActivity;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.model.BonusTable;
import olimpiadas.sena.com.olimpiadasmath.model.Result;
import olimpiadas.sena.com.olimpiadasmath.model.User;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class ResultActivity extends AppCompatActivity {

    private static final String TAG = ResultActivity.class.toString();
    AppControl appControl;

    Button btnContinue;
    TextView tvCoins,tvTickets,tvExp,tvCorAns, tvTime;
    private ClipDrawable mImageDrawable;
    private User currentUser;
    int correctAnswers = 0,expWon;
    int noAnswered = 0;


    Realm realm;

    BonusTable bonusTable;



    public static final int MULTLEVEL = 100;
    public static final int MAX_LEVEL = 10000;
    public static final int LEVEL_DIFF = 100;
    public static final int DELAY = 50;


    int tempExp,tempLvl;

    private Handler mUpHandler = new Handler();
    private Runnable animateUpImage = new Runnable() {

        @Override
        public void run() {
            doTheUpAnimation(tempExp);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().hide();

        appControl = AppControl.getInstance();
        appControl.currentActivity = ResultActivity.class.getSimpleName();

        Button btnShare = (Button) findViewById(R.id.btn_result_share);
        btnShare.setVisibility(View.GONE);

        currentUser = appControl.currentUser;

        tempExp = (int) currentUser.getExperience()*MULTLEVEL;
        tempLvl = currentUser.getLevel();

        btnContinue = (Button) findViewById(R.id.btn_result_continue);
        tvCorAns = (TextView) findViewById(R.id.tv_result_correct_answer_number);
        tvTime = (TextView) findViewById(R.id.tv_result_time);
        tvCoins = (TextView) findViewById(R.id.tv_result_coins_number);

//        tvTickets = (TextView) findViewById(R.id.tv_result_win_ticket_number);
        tvExp = (TextView) findViewById(R.id.tv_result_exp_number);
        ImageView img = (ImageView) findViewById(R.id.img_result_progress_bar2);
        mImageDrawable = (ClipDrawable) img.getDrawable();

        //mImageDrawable.setLevel(tempExp);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
                finish();
            }
        });

        for(int i = 0; i < appControl.answers.length; i++){
            if(appControl.answers[i] == -1){
                noAnswered += 1;
            }else{
                correctAnswers += appControl.answers[i];
            }

        }


        realm = Realm.getDefaultInstance();

        tvCorAns.setText(String.valueOf(correctAnswers));
        //long aux = 180 - appControl.currentTime;
        long aux = appControl.currentTime;
        tvTime.setText(String.valueOf(aux));
        Log.d(TAG,"Correct answers " + aux);
        Log.d(TAG,"Correct answers " + correctAnswers);
        Log.d(TAG,"total Questions " + appControl.answers.length);


        float bonus = (float)correctAnswers/(float)appControl.answers.length;

        Log.d(TAG,"Bonus = " + bonus);
        Log.d(TAG,"Bonus Size" +realm.where(BonusTable.class).findAll().size());
        //RealmResults<BonusTable> asdf =
        bonusTable = realm.where(BonusTable.class).greaterThanOrEqualTo("max",bonus).lessThanOrEqualTo("min",bonus).findFirst();
        /*
        for(int i = 0; i < asdf.size(); i++){
            Log.d(TAG,"Max = " + asdf.get(i).getMax() + "min = " + asdf.get(i).getMin());
        }
        Log.d(TAG,"asdf Size" + asdf.size());
        RealmResults<BonusTable> fin = realm.where(BonusTable.class).lessThanOrEqualTo("min",bonus).findAll();
        Log.d(TAG,"fin Size" + fin.size());

        for(int i = 0; i < fin.size(); i++){
            Log.d(TAG,"Max = " + fin.get(i).getMax() + "min = " + fin.get(i).getMin());
        }
        */
        if(bonusTable ==null){
            Log.d(TAG,"NO existe el bonus");
        }else{
            Log.d(TAG,"El bonus min = " + bonusTable.getMin() + " El bonus max = " + bonusTable.getMax());
            calculateCoins();
//            calculateTickets();
            calculateExp();
//            calculateScore();
        }

        User.updateUser(appControl.currentUser);

      realm.executeTransaction(new Realm.Transaction() {
          @Override
          public void execute(Realm realm) {
              int incorrectAnswers = appControl.numberOfQuestions - correctAnswers;
              int numQuestions = appControl.numberOfQuestions;

              Result result = new Result();
              result.setAnswerCorrectResult(correctAnswers);
              result.setAnswerIncorrectResult(incorrectAnswers);
              result.setNumQuestionResult(numQuestions);
              result.setAnswerNoneResult(noAnswered);
              result.setTimeTestResult(appControl.currentTime);
              result.setCoinsWinResult(appControl.currentCoinsPool);

              realm.insert(result);

          }
      });


    }

//    private void calculateScore() {
//        int winScore = 0;
//        if(bonusTable.getScore()<0){
//            winScore = - appControl.answers.length - correctAnswers;
//        }else if(bonusTable.getCoin()==0){
//            winScore = appControl.currentBet;
//        }else{
//            winScore = (int)( ( correctAnswers * bonusTable.getCoin() ) +  ( appControl.currentBet * bonusTable.getCoin() ) );
//        }
//
//        //winCoins = (int)(correctAnswers * bonusTable.getCoin());
//
//
//
//        tvCoins.setText(" x " + winScore);
//
//        currentUser.setCoins(currentUser.getCoins() + winScore);
//
//    }


    private void calculateExp(){



        expWon = (int) (correctAnswers * bonusTable.getExp());
        double finalExp = currentUser.getExperience() + expWon;
        if(finalExp >=100){
            currentUser.setExperience(finalExp - 100);
            currentUser.setLevel(tempLvl + 1);

        }else{
            currentUser.setExperience(finalExp);
        }
        mUpHandler.post(animateUpImage);
    }
    private void calculateCoins(){

        int winCoins = 0;
        if(bonusTable.getCoin()<0){
            winCoins = - appControl.currentCoinsPool;
        }else if(bonusTable.getCoin()==0){
            winCoins = appControl.currentCoinsPool;
        }else{
            winCoins = (int)( ( correctAnswers * bonusTable.getCoin() ) +  ( appControl.currentCoinsPool * bonusTable.getCoin() ) );
        }

        //winCoins = (int)(correctAnswers * bonusTable.getCoin());



        tvCoins.setText(" x " + winCoins);

        currentUser.setCoins(currentUser.getCoins() + winCoins);

    }
//    private void calculateTickets(){
//        int win = (int)bonusTable.getTicket();
//
//
//
//        tvTickets.setText(" x " + win);
//
//        currentUser.setTickets(currentUser.getTickets() + win);
//
//    }




    private void doTheUpAnimation(int toLevel) {
        tempExp += LEVEL_DIFF;
        Log.d(TAG,"TempExp = " + tempExp);
        Log.d(TAG,"currentUser experience * multlevel = " + currentUser.getExperience()*MULTLEVEL);
        if(tempLvl < currentUser.getLevel()){
            mImageDrawable.setLevel(tempExp);
            if (tempExp <= MAX_LEVEL) {
                Log.d("tag", "el nicel es " + tempExp);
                mUpHandler.postDelayed(animateUpImage, DELAY);
            } else {
                tempExp = 0;
                tempLvl ++;
                mImageDrawable.setLevel(0);
                mUpHandler.postDelayed(animateUpImage, DELAY);

            }


        }else{
            mImageDrawable.setLevel(tempExp);
            if (tempExp <= currentUser.getExperience()*MULTLEVEL) {
                Log.d("tag", "el nivel es " + tempExp);
                mUpHandler.postDelayed(animateUpImage, DELAY);
            } else {
                mUpHandler.removeCallbacks(animateUpImage);
                tvExp.setText(" + " + expWon );

            }
        }


    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
