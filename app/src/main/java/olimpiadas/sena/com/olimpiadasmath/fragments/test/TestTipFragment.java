package olimpiadas.sena.com.olimpiadasmath.fragments.test;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.test.Communication;
import olimpiadas.sena.com.olimpiadasmath.activities.test.TestActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TestTipFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class TestTipFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Communication communication;
    ImageButton img_test_tip_einstein;
    public TestTipFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TestTipFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TestTipFragment newInstance(String param1, String param2) {
        TestTipFragment fragment = new TestTipFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_test_tip, container, false);
        img_test_tip_einstein = (ImageButton) view.findViewById(R.id.img_test_tip_einstein);

        img_test_tip_einstein.setOnClickListener(this);
        return view;


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        communication = (Communication) context;
    }

    @Override
    public void onClick(View v) {

    }
}
