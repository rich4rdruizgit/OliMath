package olimpiadas.sena.com.olimpiadasmath.fragments.profile;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.adapter.profile.ResultAdapter;
import olimpiadas.sena.com.olimpiadasmath.model.Result;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileResultsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileResultsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileResultsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private List<Result> results;
    RecyclerView recyclerView;
    ResultAdapter resultAdapter;


    private OnFragmentInteractionListener mListener;

    public ProfileResultsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileResultsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileResultsFragment newInstance(String param1, String param2) {
        ProfileResultsFragment fragment = new ProfileResultsFragment();
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

        View view =  inflater.inflate(R.layout.fragment_profile_results, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_result_profile);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        recyclerView.setAdapter(resultAdapter);
        llenarUsers();
        inicializarAdaptador();


        return view;
    }


    private void inicializarAdaptador() {
        resultAdapter = new ResultAdapter(results,getActivity());
        recyclerView.setAdapter(resultAdapter);
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void llenarUsers() {
        results = new ArrayList<>();

        results.add(new Result("id","1","pregunta 5"," resp ok 3","resp not ok 2"," non resp 0","15 min","tiempo resp 2 min","70","monedas perdidas 0","monedas bet 20"));
        results.add(new Result("id","2","pregunta 1"," resp ok 3","resp not ok 2"," non resp 0","20 min","tiempo resp 2 min","200","monedas perdidas 0","monedas bet 20"));
        results.add(new Result("id","3","pregunta 5"," resp ok 3","resp not ok 2"," non resp 0","10 min","tiempo resp 2 min","20","monedas perdidas 0","monedas bet 20"));
        results.add(new Result("id","4","pregunta 5"," resp ok 3","resp not ok 2"," non resp 0","30 min","tiempo resp 2 min","50","monedas perdidas 0","monedas bet 20"));
        results.add(new Result("id","5","pregunta 5"," resp ok 3","resp not ok 1"," non resp 0","27 min","tiempo resp 2 min","100","monedas perdidas 0","monedas bet 20"));

    }
}
