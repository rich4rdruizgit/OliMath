package olimpiadas.sena.com.olimpiadasmath.fragments.challenge;


import android.app.Fragment;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BetFragment.OnBetFragmentListener} interface
 * to handle interaction events.
 * Use the {@link BetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BetFragment extends Fragment {


    private static final String TAG = "BetFragment";
    public static final int PREVIEW = 1;
    public static final int START = 2;


    private Button previewQuestion,startQuestion;
    private AppControl appControl = AppControl.getInstance();

    private OnBetFragmentListener mListener;
    private SeekBar seekBar;
    private TextView tvBet,tvPoolCoins;

    public BetFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static BetFragment newInstance() {
        BetFragment fragment = new BetFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bet_new, container, false);


        seekBar = (SeekBar) view.findViewById(R.id.seekbar);
        tvBet = (TextView) view.findViewById(R.id.tv_bet);
        tvPoolCoins = (TextView) view.findViewById(R.id.tv_pool_coins);
        tvPoolCoins.setText("x " + appControl.currentCoinsPool);

        previewQuestion = (Button) view.findViewById(R.id.btn_bet_preview);
        appControl.soundButton = MediaPlayer.create(view.getContext(),appControl.soundButtonEfect);
        previewQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appControl.soundButton.start();
                onPreviewPressed();
            }
        });
        if(appControl.previewUsed){
            previewQuestion.setVisibility(View.GONE);

        }
        startQuestion = (Button) view.findViewById(R.id.btn_bet_start);
        startQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(appControl.isBackgroundPlaying)
                    appControl.soundButton.start();
                onStartPressed();
            }
        });
        tvBet.setText("2");

        seekBar.setMax(appControl.currentCoinsPool);
        seekBar.setProgress(2);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress<2){
                    tvBet.setText("2");
                    seekBar.setProgress(2);
                }else{
                    appControl.currentBet = progress;
                    tvBet.setText(progress+"");
                }


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        Log.d(TAG,"FInish onCreateView");
        return view;
    }


    public void onPreviewPressed() {
        if (mListener != null) {
            appControl.currentCoinsPool -= 2;
            mListener.onBetFragmentListener(PREVIEW);
        }
    }
    public void onStartPressed() {
        Log.d(TAG,"On start pressed");
        if (mListener != null) {
            Log.d(TAG,"Listener no null");
            mListener.onBetFragmentListener(START);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBetFragmentListener) {
            mListener = (OnBetFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnQuestionFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnBetFragmentListener {
        // TODO: Update argument type and name
        void onBetFragmentListener(int i);
    }

    public void enablePreview(boolean enable){
        previewQuestion.setEnabled(enable);
        previewQuestion.setVisibility(View.GONE);
        Log.d(TAG,"FInish enablePreview");
    }

    public void setmListener(OnBetFragmentListener onBetFragmentListener){
        mListener = onBetFragmentListener;

    }
}
