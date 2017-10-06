package olimpiadas.sena.com.olimpiadasmath.fragments.ranking;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.adapter.ranking.CoinsRankingAdapter;
import olimpiadas.sena.com.olimpiadasmath.adapter.ranking.TimeRankingAdapter;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.model.User;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CoinsRankingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CoinsRankingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoinsRankingFragment extends Fragment {

    private static final String TAG = "CoinsRankingFragment";
    RecyclerView recyclerView;
    private List<User> users;
    CoinsRankingAdapter adapter;
    LinearLayout linearMyPos;


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CoinsRankingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CoinsRankingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CoinsRankingFragment newInstance(String param1, String param2) {
        CoinsRankingFragment fragment = new CoinsRankingFragment();
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

        User user = AppControl.getInstance().currentUser;
        View view = inflater.inflate(R.layout.fragment_coins_ranking, container, false);
        Log.d(TAG,"OnCreateView created");
        linearMyPos = (LinearLayout) view.findViewById(R.id.linear_myposition);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_ranking_coins);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        if (user.getPosition() < 20) {
            linearMyPos.setVisibility(View.GONE);
        }
        linearMyPos.setVisibility(View.VISIBLE);
        llenarUsers();
        adapter = new CoinsRankingAdapter(users,getActivity());
        recyclerView.setAdapter(adapter);
        return view;
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
        users = new ArrayList<>();

        users.add(new User("Didier","1234",3000,1,47,12,10,2,"marco18"));
        users.add(new User("Carlos","1234",2000,2,57,12,10,2,"marco8"));
        users.add(new User("Harold","1234",1500,3,49,12,10,2,"marco11"));
        users.add(new User("Rich4rd","1234",1322,4,47,12,10,2,"jhonny"));
        users.add(new User("Jefferson","1234",1300,5,47,12,10,2,"marco"));

    }
}
