package olimpiadas.sena.com.olimpiadasmath.fragments.profile;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
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
 * {@link PerfilFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PerfilFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    AppControl appControl;
    //Button btnTakePic,btnSavePic,btnOpenGal;
    ImageView imgPic;
    TextView tvLvl,tvCoins,tvTickets,txtNameUser,tvScore, tvPercent;

    private ClipDrawable mImageDrawable;

    private OnFragmentInteractionListener mListener;

    public PerfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PerfilFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        /*btnOpenGal = (Button) view.findViewById(R.id.btnOpenGal);
        btnSavePic = (Button) view.findViewById(R.id.btnSavePic);
        btnTakePic = (Button) view.findViewById(R.id.btnTakePic);*/
        //tvTickets = (TextView) view.findViewById(R.id.tv_profile_ticket);
        //tvScore = (TextView) view.findViewById(R.id.tv_profile_score);


        tvPercent.setText(""+appControl.currentUser.getExperience()+"%");
        tvLvl.setText(""+appControl.currentUser.getLevel());
        tvCoins.setText(" x " + appControl.currentUser.getCoins());
        //tvTickets.setText(" x " + appControl.currentUser.getTickets());
        //tvScore.setText("" + appControl.currentUser.getScore());
        ImageView imgExp = (ImageView) view.findViewById(R.id.img_profile_progress_bar2);
        //imgPic = (ImageView) view.findViewById(R.id.imgPic);
        mImageDrawable = (ClipDrawable) imgExp.getDrawable();
        mImageDrawable.setLevel((int)appControl.currentUser.getExperience() * 100);

        /*btnTakePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,1,null);
                Bitmap bitmap = ((BitmapDrawable)imgPic.getDrawable()).getBitmap();
            }
        });*/


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        imgPic.setImageBitmap(bitmap);
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
