package olimpiadas.sena.com.olimpiadasmath.fragments.profile;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.adapter.profile.AchievementAdapter;
import olimpiadas.sena.com.olimpiadasmath.adapter.ranking.RankingAdapter;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.model.Achievements;
import olimpiadas.sena.com.olimpiadasmath.model.User;
import olimpiadas.sena.com.olimpiadasmath.util.CircleImage.CropCircleTransformation;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LogroFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LogroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogroFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    AppControl appControl;

    private List<Achievements> logrosList;

    RecyclerView recyclerView;
    AchievementAdapter achievementAdapter;
    private OnFragmentInteractionListener mListener;

    public LogroFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LogroFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LogroFragment newInstance(String param1, String param2) {
        LogroFragment fragment = new LogroFragment();
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

        View view =  inflater.inflate(R.layout.fragment_logro, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_achievement);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        llenarUsers();
        inicializarAdaptador();

        return view;
    }

    private void inicializarAdaptador() {
        achievementAdapter = new AchievementAdapter(logrosList,getActivity());
        recyclerView.setAdapter(achievementAdapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    private void llenarUsers() {
        logrosList = new ArrayList<>();

        logrosList.add(new Achievements("","Iniciar una Práctica","ACTIVO","100"));
        logrosList.add(new Achievements("","Terminar una Práctica","ACTIVO","200"));
        logrosList.add(new Achievements("","Terminar un reto","ACTIVO","100"));
        logrosList.add(new Achievements("","Práctica perfecta","ACTIVO","150"));
        logrosList.add(new Achievements("","Reto Perfecto","ACTIVO","200"));
        logrosList.add(new Achievements("","Comprar un avatar","ACTIVO","150"));
        logrosList.add(new Achievements("","Comprar una poción","ACTIVO","150"));
        logrosList.add(new Achievements("","Eres nivel 10","ACTIVO","50"));
        logrosList.add(new Achievements("","Eres nivel 20","ACTIVO","100"));
        logrosList.add(new Achievements("","Eres nivel 30","ACTIVO","150"));

    }
}
