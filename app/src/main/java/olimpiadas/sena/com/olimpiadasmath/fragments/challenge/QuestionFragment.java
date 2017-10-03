package olimpiadas.sena.com.olimpiadasmath.fragments.challenge;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.challenge.ChallengeActivity;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.model.Question;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnQuestionFragmentListener} interface
 * to handle interaction events.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {


    private static final String TAG = "QuestionFragment";
    private OnQuestionFragmentListener mListener;
    private TextView tvCountDown,tvQuestionContent,tvTitle;
    private CardView cardView;
    private ImageView imgScale;
    private Button btnNext;
    RadioGroup radioGroup;
    AppControl appControl = AppControl.getInstance();
    boolean isScaled = false;
    private Question currentQuestion;
    CountDownTimer currentTimer;


    public QuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     +* @return A new instance of fragment QuestionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuestionFragment newInstance() {
        QuestionFragment fragment = new QuestionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_question, container, false);
        tvCountDown = (TextView) view.findViewById(R.id.tv_count_down);
        cardView = (CardView) view.findViewById(R.id.cardView);
        imgScale = (ImageView) view.findViewById(R.id.img_test_scale);
        btnNext = (Button) view.findViewById(R.id.btn_question_next);
        tvTitle = (TextView) view.findViewById(R.id.titleTextView);
        tvQuestionContent = (TextView) view.findViewById(R.id.tv_question_content);
        tvTitle.setText(currentQuestion.getQuestionText());

        radioGroup = (RadioGroup) view.findViewById(R.id.rg_group_answer);
        for(int x = 0 ; x<4 ;x++){
            Log.d(TAG,"item" + x + " correct" + currentQuestion.getAnswerCorrect(x));

            if(currentQuestion.getAnswerCorrect(x).equals("1") ) {
                Log.d(TAG,"Entro 1");
                tvQuestionContent.setText(currentQuestion.getAnswerText(x));
            }
            RadioButton radioButton = new RadioButton(view.getContext());
            radioButton.setText(currentQuestion.getAnswerText(x));
            radioGroup.addView(radioButton);
        }

        long miliseconds = 180000;
        if(appControl.isPreview){
            miliseconds = 15000;
        }else if(appControl.previewUsed){
            miliseconds -= 15000;
        }
        final long totalMilisec = miliseconds;

        imgScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableScaling(isScaled);
            }
        });

        currentTimer = new CountDownTimer(miliseconds, 1000) {

            public void onTick(long millisUntilFinished) {
                String v = String.format("%02d", millisUntilFinished/60000);
                int va = (int)( (millisUntilFinished%60000)/1000);
                tvCountDown.setText(v+":"+String.format("%02d",va));
                appControl.currentTime += 1;
            }

            public void onFinish() {
                tvCountDown.setText("00:00");
                appControl.currentTime += totalMilisec/1000;
                if (mListener != null) {
                    mListener.onPreviewEnd();
                }
            }
        };
        currentTimer.start();

        if(appControl.currentQuestion == appControl.numberOfQuestions-1){
            btnNext.setText("Terminar");
        }

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                Log.d(TAG, "EL selectedId es " + selectedId);
                if(selectedId == -1){
                    appControl.answers[appControl.currentQuestion] = -1;
                }else{

                    Log.d(TAG,"Respuesta es " + radioGroup.getCheckedRadioButtonId());
                    //Log.d(TAG,"Respuesta es " + ((RadioButton) view.findViewById(radioGroup.getCheckedRadioButtonId())).getText());
                    int idx = radioGroup.indexOfChild(view.findViewById(radioGroup.getCheckedRadioButtonId()));
                    Log.d(TAG, "EL indice es " + idx);
                    Log.d(TAG, "Correct answer " + currentQuestion.getAnswerCorrect(idx));
                    if(currentQuestion.getAnswerCorrect(idx).equals("1")){
                        appControl.answers[appControl.currentQuestion] = 1;
                        appControl.currentCoinsPool += appControl.currentBet;

                    }else
                    {
                        appControl.answers[appControl.currentQuestion] = 0;
                        appControl.currentCoinsPool -= appControl.currentBet;
                        String myasnwer = "my answer";
                        String theanswer = "the answer";
                        String feedback = "feedback papu";
                    }

                }
                currentTimer.cancel();
                mListener.onQuestionEnd();
            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnQuestionFragmentListener) {
            mListener = (OnQuestionFragmentListener) context;
            ChallengeActivity ca = (ChallengeActivity) context;
            currentQuestion = ca.getQuestions().get(appControl.currentQuestion);

        } else {
            throw new RuntimeException(context.toString() + " must implement OnQuestionFragmentListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void enableScaling(boolean scale) {
        if (scale) {
            // shrink main card
            if (cardView != null) {
                cardView.animate().scaleY(1);
                cardView.animate().scaleX(1);
            }
        }else {
            // grow main card
            if (cardView != null) {
                cardView.animate().scaleY(1.1f);
                cardView.animate().scaleX(1.1f);
            }
        }
        isScaled = !isScaled;

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
public interface OnQuestionFragmentListener {
        // TODO: Update argument type and name
        void onPreviewEnd();
        void onQuestionEnd();
    }
}
