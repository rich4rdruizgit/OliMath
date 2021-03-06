package olimpiadas.sena.com.olimpiadasmath.adapter.test;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v4.view.PagerAdapter;
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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.test.CardItem;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.model.Question;
import olimpiadas.sena.com.olimpiadasmath.util.DialogHelper;

public class CardPagerAdapter extends PagerAdapter implements CardAdapter, View.OnClickListener, DialogHelper.FeedbackDialogListener {

    private static final String TAG = CardPagerAdapter.class.toString();
    private List<CardView> mViews;
    // private List<CardItem> question;

    private List<Question> question;
    private float mBaseElevation;
    public CommunicationTest communicationTest;
    AppControl appControl;
    Context context;

    Button btnNext, btnBack;
    //ImageView imgViewTest;
    public boolean imageScaled = false;
    public int currentPosition = 0;


    public MoveTestListener getMoveTestListener() {
        return moveTestListener;
    }

    public void setMoveTestListener(MoveTestListener moveTestListener) {
        this.moveTestListener = moveTestListener;
    }

    MoveTestListener moveTestListener;

    public CommunicationTest getCommunicationTest() {
        return communicationTest;
    }

    public void setCommunicationTest(CommunicationTest communicationTest) {
        this.communicationTest = communicationTest;
    }


    public CardPagerAdapter(List<Question> question, Context context) {
        this.question = question;
        mViews = new ArrayList<>();
        appControl = AppControl.getInstance();
        this.context = context;

    }

    public void addCardItem(CardItem item) {
        mViews.add(null);

    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return question.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        final View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.adapter, container, false);
        container.addView(view);
        final RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rg_group_answer);

        if (AppControl.getInstance().onChallenge) {
            btnBack = (Button) view.findViewById(R.id.btn_test_back);
            btnBack.setVisibility(View.GONE);
            btnNext = (Button) view.findViewById(R.id.btn_test_next);
        }
        if (AppControl.getInstance().onPractice) {
            btnNext = (Button) view.findViewById(R.id.btn_test_next);
            btnBack = (Button) view.findViewById(R.id.btn_test_back);
            btnBack.setVisibility(View.GONE);
        }

        ImageView imgScale = (ImageView) view.findViewById(R.id.img_test_scale);
        ImageView imgScaleView = (ImageView) view.findViewById(R.id.img_test_image);
        final ImageView imgViewTest = (ImageView) view.findViewById(R.id.contentViewImage);
        imgViewTest.setVisibility(View.GONE);
        imgScaleView.setVisibility(View.GONE);
        imgScaleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appControl.soundButtonPlay();
                imageScaled = !imageScaled;
                if (imageScaled) {
                    Log.d(TAG, "img_test_image pressed");
                    imgViewTest.setVisibility(View.VISIBLE);
                } else {
                    Log.d(TAG, "img_test_image pressed");
                    imgViewTest.setVisibility(View.GONE);
                }
            }
        });

        if (position == getCount() - 1) {
            btnNext.setText(R.string.finish);
        } else {
            btnNext.setText(R.string.next);
        }
        //final RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rg_group_answer);

        /*radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                Toast.makeText(context, "si hace clic", Toast.LENGTH_SHORT).show();
                btnNext.setEnabled(true);
                btnNext.setText("Putos");
                btnNext.setBackgroundColor(view.getResources().getColor(R.color.primary_light));

            }
        });*/

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appControl.soundButtonPlay();
                int value = radioGroup.getCheckedRadioButtonId();
                if (value != -1) {

                    appControl.soundButton = MediaPlayer.create(v.getContext(), appControl.soundButtonEfect);
                    if (appControl.isBackgroundPlaying)
                        appControl.soundButton.start();
                    int selectedId = radioGroup.getCheckedRadioButtonId();

                    if (selectedId == -1) {
                        appControl.answers[position] = -1;

                    } else {

                        Log.d(TAG, "Respuesta es " + radioGroup.getCheckedRadioButtonId());
                        Log.d(TAG, "Respuesta es " + ((RadioButton) view.findViewById(radioGroup.getCheckedRadioButtonId())).getText());
                        int idx = radioGroup.indexOfChild(view.findViewById(radioGroup.getCheckedRadioButtonId()));
                        Log.d(TAG, "EL indice es " + idx);
                        Log.d(TAG, "Correct answer " + question.get(position).getAnswerCorrect(idx));
                        appControl.answersId[position] = Integer.parseInt(question.get(position).getAnswerId(idx));
                        if (question.get(position).getAnswerCorrect(idx).equals("1")) {
                            appControl.answers[position] = 1;
                            if (position + 1 < getCount()) {
                                moveTestListener.moveClick(position + 1);
                            } else {
                                moveTestListener.finished();
                            }
                        } else {
                            appControl.answers[position] = 0;
                            String myasnwer = question.get(position).getAnswerText(position);
                            String theanswer = question.get(position).getCorrectAnswerText();
                            String feedback = question.get(position).getFeedback();
                            currentPosition = position;
                            Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                            vibrator.vibrate(1000);
                            DialogHelper.FeedbackDialog(context, myasnwer, theanswer, feedback, CardPagerAdapter.this);
                        }
                    }

                }else{
                    Toast.makeText(context, "Debes seleccionar una opción", Toast.LENGTH_SHORT).show();
                    }
            }

        });

        imgScale.setOnClickListener(this);

        bind(question.get(position), view, position);
        CardView cardView = (CardView) view.findViewById(R.id.cardView);

        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }


        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bind(Question item, final View view, int position) {
        TextView titleTextView = (TextView) view.findViewById(R.id.titleTextView);
        TextView contentTextView = (TextView) view.findViewById(R.id.contentTextView);


        Log.d(TAG, "Question text : " + item.getQuestionText());
        titleTextView.setText(item.getQuestionText());
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.rg_group_answer);
        for (int x = 0; x < 5; x++) {
            Log.d(TAG, "item" + x + " correct" + item.getAnswerCorrect(x));

            if (item.getAnswerCorrect(x).equals("1")) {
                Log.d(TAG, "Entro 1");
                contentTextView.setText(item.getAnswerText(x));
            }

            final RadioButton radioButton = new RadioButton(view.getContext());
            radioButton.setText(item.getAnswerText(x));
            if(item.getAnswerCorrect(x).equals("2") ) {
                Log.d(TAG,"Entro 2");
                radioButton.setVisibility(View.GONE);
            }
            radioGroup.addView(radioButton);
        }
    }

    @Override
    public void onClick(View v) {
        appControl.soundButtonPlay();
        switch (v.getId()){
            case R.id.img_test_scale:
                if(communicationTest!=null){communicationTest.changeScale();}
                break;
            case R.id.img_test_image:
                Log.d(TAG,"img_test_image pressed");

                break;

        }
    }

    @Override
    public void closeFeedBackDialog() {

        //Pasar a la siguiente interfaz
        if (currentPosition + 1 < getCount()) {


            moveTestListener.moveClick(currentPosition + 1);

        } else {
            moveTestListener.finished();
        }

    }

    public interface MoveTestListener {
        public final int NEXT = 1;
        public final int BACK = 2;

        public void moveClick(int pos);

        public void finished();
    }

    public interface CommunicationTest {
        public void changeScale();
    }


}
