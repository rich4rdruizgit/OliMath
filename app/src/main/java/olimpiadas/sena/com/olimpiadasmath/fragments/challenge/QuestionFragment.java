package olimpiadas.sena.com.olimpiadasmath.fragments.challenge;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnQuestionFragmentListener} interface
 * to handle interaction events.
 * Use the {@link QuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionFragment extends Fragment {



    private OnQuestionFragmentListener mListener;
    private TextView tvCountDown;
    private CardView cardView;
    private ImageView imgScale;
    AppControl appControl = AppControl.getInstance();
    boolean isScaled = false;

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
        View view = inflater.inflate(R.layout.fragment_question, container, false);
        tvCountDown = (TextView) view.findViewById(R.id.tv_count_down);
        cardView = (CardView) view.findViewById(R.id.cardView);
        imgScale = (ImageView) view.findViewById(R.id.img_test_scale);
        long miliseconds = 180000;
        if(appControl.isPreview){
            miliseconds = 15000;
        }else if(appControl.previewUsed){
            miliseconds -= 15000;
        }

        imgScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableScaling(isScaled);
            }
        });

        new CountDownTimer(miliseconds, 1000) {

            public void onTick(long millisUntilFinished) {
                String v = String.format("%02d", millisUntilFinished/60000);
                int va = (int)( (millisUntilFinished%60000)/1000);
                tvCountDown.setText(v+":"+String.format("%02d",va));
            }

            public void onFinish() {
                tvCountDown.setText("00:00");
                if (mListener != null) {
                    mListener.onPreviewEnd();
                }
            }
        }.start();

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
