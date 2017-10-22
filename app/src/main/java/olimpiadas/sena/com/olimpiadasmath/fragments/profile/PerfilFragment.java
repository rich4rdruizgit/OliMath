package olimpiadas.sena.com.olimpiadasmath.fragments.profile;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.menu.MainActivity;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PerfilFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    AppControl appControl;
    TextView tvLvl,tvCoins,tvTickets,txtNameUser,tvScore, tvPercent;

    ImageButton btnBack;
    private ClipDrawable mImageDrawable;

    private OnFragmentInteractionListener mListener;

    public PerfilFragment() {
        // Required empty public constructor
    }

    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
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
        appControl = AppControl.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        tvPercent = (TextView) view.findViewById(R.id.tv_profile_percent);
        tvLvl = (TextView) view.findViewById(R.id.tv_profile_level_number);
        tvCoins = (TextView) view.findViewById(R.id.tv_profile_coins);

        btnBack = (ImageButton) view.findViewById(R.id.btn_back_profile);

        tvPercent.setText(""+appControl.currentUser.getExperience()+"%");
        tvLvl.setText(""+appControl.currentUser.getLevel());
        tvCoins.setText(" x " + appControl.currentUser.getCoins());
        ImageView imgExp = (ImageView) view.findViewById(R.id.img_profile_progress_bar2);
        mImageDrawable = (ClipDrawable) imgExp.getDrawable();
        mImageDrawable.setLevel((int)appControl.currentUser.getExperience() * 100);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBack = new Intent(getActivity(), MainActivity.class);
                startActivity(goBack);
            }
        });

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
