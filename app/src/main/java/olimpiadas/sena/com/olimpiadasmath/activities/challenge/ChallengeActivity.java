package olimpiadas.sena.com.olimpiadasmath.activities.challenge;


import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import io.realm.Realm;
import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.menu.MainActivity;
import olimpiadas.sena.com.olimpiadasmath.activities.result.ResultActivity;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.fragments.challenge.BetFragment;
import olimpiadas.sena.com.olimpiadasmath.fragments.challenge.QuestionFragment;
import olimpiadas.sena.com.olimpiadasmath.model.Question;
import olimpiadas.sena.com.olimpiadasmath.util.DialogHelper;


import static android.os.Build.VERSION.SDK;
import static java.lang.Thread.sleep;


public class ChallengeActivity extends AppCompatActivity implements BetFragment.OnBetFragmentListener, QuestionFragment.OnQuestionFragmentListener {

    private static final String TAG = "ChallengeActivity";
    private boolean mShowingBack = false;
    private Handler mHandler = new Handler();
    BetFragment bet;
    QuestionFragment question;
    AppControl appControl = AppControl.getInstance();
    List<Question> questions;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);
        getSupportActionBar().hide();
        appControl.currentCoinsPool = 20;
        bet = BetFragment.newInstance();
        bet.setmListener(this);
        question = QuestionFragment.newInstance();

        getFragmentManager()
                .beginTransaction()
                .add(R.id.container, bet)
                .commit();

        Realm realm = Realm.getDefaultInstance();
        appControl.currentQuestion = 0;
        questions = realm.where(Question.class).findAll().subList(0,appControl.numberOfQuestions);
        question.setmListener(this);
        //getFragmentManager().addOnBackStackChangedListener(this);
    }

    private void flipCard() {
        Log.d(TAG,"Sdk int = " + Build.VERSION.SDK_INT);
        if (mShowingBack) {
            getFragmentManager().popBackStack();
            bet.enablePreview(false);
            mShowingBack = false;
            if(appControl.currentCoinsPool == 0){
                DialogHelper.showNoCoins(ChallengeActivity.this);

            }
            return;
        }

        // Flip to the back.

        mShowingBack = true;

        // Create and commit a new fragment transaction that adds the fragment for the back of
        // the card, uses custom animations, and is part of the fragment manager's back stack.


        if(Build.VERSION.SDK_INT > 4){
            Log.d(TAG,"Sdk int > 23 ");
            getFragmentManager()
                    .beginTransaction()

                    // Replace the default fragment animations with animator resources representing
                    // rotations when switching to the back of the card, as well as animator
                    // resources representing rotations when flipping back to the front (e.g. when
                    // the system Back button is pressed).
                    .setCustomAnimations(
                    R.animator.card_flip_right_in, R.animator.card_flip_right_out,
                                  R.animator.card_flip_left_in, R.animator.card_flip_left_out)

                    // Replace any fragments currently in the container view with a fragment
                    // representing the next page (indicated by the just-incremented currentPage

                    .replace(R.id.container, question)

                    // Add this transaction to the back stack, allowing users to press Back
                    // to get to the front of the card.
                    .addToBackStack(null)

                    // Commit the transaction.
                    .commit();
        }else{
            Log.d(TAG,"Entro aqui");
            getFragmentManager()
                    .beginTransaction().replace(R.id.container, question)

                    // Add this transaction to the back stack, allowing users to press Back
                    // to get to the front of the card.
                    .addToBackStack(null)

                    // Commit the transaction.
                    .commit();
        }




        // Defer an invalidation of the options menu (on modern devices, the action bar). This
        // can't be done immediately because the transaction may not yet be committed. Commits
        // are asynchronous in that they are posted to the main thread's message loop.
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                invalidateOptionsMenu();
            }
        });
    }


    @Override
    public void onBetFragmentListener(int type) {

        if(type == BetFragment.PREVIEW){
            appControl.previewUsed = true;
            appControl.isPreview = true;
            flipCard();
        }else if(type == BetFragment.START){
            appControl.previewUsed = false;
            appControl.isPreview = false;
            flipCard();

        }
    }

    @Override
    public void onPreviewEnd() {
        flipCard();
    }

    @Override
    public void onQuestionEnd() {
        appControl.currentQuestion++;
        if(appControl.currentQuestion == appControl.numberOfQuestions){
            startActivity(new Intent(ChallengeActivity.this, ResultActivity.class));
        }else{
            flipCard();
        }

        //Aqui termina la pregunta, y debe hacerse la logica de pasar a la siguiente pregunta.

    }

    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public void onBackPressed() {
//        DialogHelper.ConfimrFinishTestDialog(this,"Seguro que quieres terminar el reto? \nPerderas los créditos que pagaste para ingresar y se contará como perdido");
        DialogHelper.ConfimrFinishTestDialog(this, getResources().getString(R.string.text_dialog_exit_challenge));
    }
}
