package olimpiadas.sena.com.olimpiadasmath.fragments.challenge;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

        previewQuestion = (Button) view.findViewById(R.id.btn_bet_preview);
        previewQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                onStartPressed();
            }
        });
        Log.d(TAG,"FInish onCreateView");
        return view;
    }


    public void onPreviewPressed() {
        if (mListener != null) {
            mListener.onBetFragmentListener(PREVIEW);
        }
    }
    public void onStartPressed() {
        if (mListener != null) {
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
}
