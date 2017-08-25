package olimpiadas.sena.com.olimpiadasmath.activities.result;

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
import olimpiadas.sena.com.olimpiadasmath.model.User;


public class ResultActivity extends AppCompatActivity {

    private static final String TAG = ResultActivity.class.toString();
    AppControl appControl;

    Button btnContinue;
    TextView tvCoins,tvTickets,tvExp,tvCorAns,tvIncAns;
    private ClipDrawable mImageDrawable;
    private User currentUser;
    int correctAnswers = 0,expWon;

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

        appControl = AppControl.getInstance();


        currentUser = appControl.currentUser;


        tempExp = (int) currentUser.getExperience()*MULTLEVEL;
        tempLvl = currentUser.getLevel();

        btnContinue = (Button) findViewById(R.id.btn_result_continue);
        tvCorAns = (TextView) findViewById(R.id.tv_result_rigth_answer_number);
        tvIncAns = (TextView) findViewById(R.id.tv_result_wrong_answer_number);
        tvCoins = (TextView) findViewById(R.id.tv_result_win_coins_number);
        tvTickets = (TextView) findViewById(R.id.tv_result_win_ticket_number);
        tvExp = (TextView) findViewById(R.id.tv_result_exp_number);
        ImageView img = (ImageView) findViewById(R.id.img_result_progress_bar2);
        mImageDrawable = (ClipDrawable) img.getDrawable();
        mImageDrawable.setLevel(tempExp);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
                finish();
            }
        });

        for(int i = 0; i < appControl.answers.length; i++){
            correctAnswers += appControl.answers[i];
        }


        realm = Realm.getDefaultInstance();

        tvCorAns.setText(String.valueOf(correctAnswers));
        tvIncAns.setText(String.valueOf(appControl.answers.length - correctAnswers));
        Log.d(TAG,"Correct answers " + correctAnswers);

        float bonus = appControl.answers.length/correctAnswers;

        bonusTable = realm.where(BonusTable.class).greaterThan("min",bonus).lessThanOrEqualTo("max",bonus).findFirst();
        calculateCoins();
        calculateTickets();
        calculateExp();
    }


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


        int winCoins = (int)(correctAnswers * bonusTable.getCoin());
        tvCoins.setText(" x " + winCoins);

        currentUser.setCoins(currentUser.getCoins() + winCoins);

    }
    private void calculateTickets(){
        int win = (int)bonusTable.getTicket();



        tvTickets.setText(" x " + win);

        currentUser.setTickets(currentUser.getTickets() + win);

    }




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
}