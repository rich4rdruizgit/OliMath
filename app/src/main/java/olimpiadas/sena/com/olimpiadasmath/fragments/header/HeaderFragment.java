package olimpiadas.sena.com.olimpiadasmath.fragments.header;


import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.profile.ProfileActivity;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HeaderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HeaderFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ImageView imgHeaderProfile, imgExp;
    private ClipDrawable mImageDrawable;

    TextView tvCoins,tvExp,tvTickets;

    AppControl appControl;

    public HeaderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HeaderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HeaderFragment newInstance(String param1, String param2) {
        HeaderFragment fragment = new HeaderFragment();
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
        appControl = AppControl.getInstance();

        View view = inflater.inflate(R.layout.fragment_header, container, false);
        imgHeaderProfile = (ImageView) view.findViewById(R.id.img_header_profile);
        imgExp = (ImageView) view.findViewById(R.id.img_header_progress_bar2);
        tvCoins = (TextView) view.findViewById(R.id.tv_header_coins);
        tvExp = (TextView) view.findViewById(R.id.tv_header_exp);
        tvTickets = (TextView) view.findViewById(R.id.tv_header_ticket);

        mImageDrawable = (ClipDrawable) imgExp.getDrawable();


        mImageDrawable.setLevel((int)appControl.currentUser.getExperience()*100);


        tvTickets.setText(" x " + appControl.currentUser.getTickets());
        tvCoins.setText(" x " + appControl.currentUser.getCoins());
        tvExp.setText(" x " + ((int)appControl.currentUser.getExperience()));



        imgHeaderProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);
            }
        });
        return view;


    }

    public void refreshInterface(){
        tvTickets.setText(" x " + appControl.currentUser.getTickets());
        tvCoins.setText(" x " + appControl.currentUser.getCoins());
        tvExp.setText(" x " + ((int)appControl.currentUser.getExperience()));

    }


}
