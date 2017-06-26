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

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.menu.MainActivity;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.model.User;


public class ResultActivity extends AppCompatActivity {

    private static final String TAG = ResultActivity.class.toString();
    AppControl appControl;

    Button btnContinue;
    private ClipDrawable mImageDrawable;
    private User currentUser;


    public static final int MULTLEVEL = 100;
    public static final int MAX_LEVEL = 10000;
    public static final int LEVEL_DIFF = 100;
    public static final int DELAY = 50;


    int tempExp;

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

        if(currentUser == null){
            currentUser = new User("Harold",1,2);
            currentUser.setExperience(30);
        }





        tempExp = (int) currentUser.getExperience()*MULTLEVEL;

        btnContinue = (Button) findViewById(R.id.btn_result_continue);


        ImageView img = (ImageView) findViewById(R.id.img_result_progress_bar2);
        mImageDrawable = (ClipDrawable) img.getDrawable();


        mImageDrawable.setLevel(tempExp);




        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultActivity.this, MainActivity.class));
            }
        });

        calculateExp();


    }


    private void calculateExp(){
        double finalExp = currentUser.getExperience() + 8;

        if(finalExp >=100){
            currentUser.setExperience(finalExp - 100);
        }else{
            currentUser.setExperience(finalExp);
        }


        mUpHandler.post(animateUpImage);


    }




    private void doTheUpAnimation(int toLevel) {
        tempExp += LEVEL_DIFF;
        Log.d(TAG,"TempExp = " + tempExp);
        Log.d(TAG,"currentUser experience * multlevel = " + currentUser.getExperience()*MULTLEVEL);
        if(tempExp > currentUser.getExperience()*MULTLEVEL){
            mImageDrawable.setLevel(tempExp);
            if (tempExp <= MAX_LEVEL) {
                Log.d("tag", "el nicel es " + tempExp);
                mUpHandler.postDelayed(animateUpImage, DELAY);
            } else {
                tempExp = 0;
                mImageDrawable.setLevel(0);
                mUpHandler.postDelayed(animateUpImage, DELAY);

            }


        }else{
            mImageDrawable.setLevel(tempExp);
            if (tempExp <= currentUser.getExperience()*MULTLEVEL) {
                Log.d("tag", "el nicel es " + tempExp);
                mUpHandler.postDelayed(animateUpImage, DELAY);
            } else {
                mUpHandler.removeCallbacks(animateUpImage);

            }
        }


    }
}
