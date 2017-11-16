package olimpiadas.sena.com.olimpiadasmath.activities.challenge;


import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.menu.MainActivity;
import olimpiadas.sena.com.olimpiadasmath.activities.result.ResultActivity;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.fragments.challenge.BetFragment;
import olimpiadas.sena.com.olimpiadasmath.fragments.challenge.QuestionFragment;
import olimpiadas.sena.com.olimpiadasmath.model.Question;
import olimpiadas.sena.com.olimpiadasmath.util.DialogHelper;
import olimpiadas.sena.com.olimpiadasmath.util.webConManager.WebConnectionManager;


import static android.os.Build.VERSION.SDK;
import static java.lang.Thread.sleep;


public class ChallengeActivity extends AppCompatActivity implements BetFragment.OnBetFragmentListener, QuestionFragment.OnQuestionFragmentListener, WebConnectionManager.WebConnectionManagerListener {

    private static final String TAG = "ChallengeActivity";
    private boolean mShowingBack = false;
    private Handler mHandler = new Handler();
    BetFragment bet;
    QuestionFragment question;
    AppControl appControl = AppControl.getInstance();
    List<Question> questions;
    Realm realm;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenge);
        getSupportActionBar().hide();
        appControl.currentCoinsPool = 20;
        appControl.currentTime = 0;
        appControl.currentActivity = ChallengeActivity.class.getSimpleName();
        bet = BetFragment.newInstance();
        bet.setmListener(this);
        question = QuestionFragment.newInstance();



        realm = Realm.getDefaultInstance();
        appControl.currentQuestion = 0;
        //questions = new ArrayList<>();
        questions = realm.where(Question.class).findAll();
        if(questions.size() < appControl.numberOfQuestions  ){
            Toast.makeText(this,"Estamos cargando preguntas",Toast.LENGTH_SHORT).show();
            DialogHelper.showBusyDialog(this,"Estamos Cargando preguntas");
            WebConnectionManager webConnectionManager = WebConnectionManager.getWebConnectionManager();
            webConnectionManager.setWebConnectionManagerListener(this);
            webConnectionManager.getQuestions();
        }else{
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    questions = new ArrayList<>();
                    for(int i = 0 ; i < appControl.numberOfQuestions; i++){

                        Question tempQuestion= realm.where(Question.class).findFirst();
                        questions.add(realm.copyFromRealm(tempQuestion));
                        tempQuestion.deleteFromRealm();
                    }
                }
            });
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container, bet)
                    .commit();
            question.setmListener(this);
        }




        //getFragmentManager().addOnBackStackChangedListener(this);
    }

    private void flipCard() {
        Log.d(TAG,"Sdk int = " + Build.VERSION.SDK_INT);
        if (mShowingBack) {
            getFragmentManager().popBackStack();
            bet.enablePreview(false);
            mShowingBack = false;
            if(appControl.currentCoinsPool < 2){
                DialogHelper.showNoCoins(ChallengeActivity.this);
            }
            return;
        }

        // Flip to the back.

        mShowingBack = true;

        // Create and commit a new fragment transaction that adds the fragment for the back of
        // the card, uses custom animations, and is part of the fragment manager's back stack.
        question = QuestionFragment.newInstance();
        question.setmListener(ChallengeActivity.this);

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
            //appControl.previewUsed = false;
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
        appControl.previewUsed = false;
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
                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.container, bet)
                        .commit();
                //Realm realm = Realm.getDefaultInstance();

            }
        }
    }


    public void LoadQuestionsFromWebService(final List<Question> questionList) {

        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
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
                question.setmListener(ChallengeActivity.this);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ChallengeActivity.this.questions = ChallengeActivity.this.realm.where(Question.class).findAll().subList(0,appControl.numberOfQuestions);
                        Log.d(TAG,"Questions size at loaded " + questions.size());
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
