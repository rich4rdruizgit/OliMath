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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.adapter.ranking.RankingAdapter;
import olimpiadas.sena.com.olimpiadasmath.adapter.ranking.TimeRankingAdapter;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.model.User;
import olimpiadas.sena.com.olimpiadasmath.util.webConManager.WebConnectionManager;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TimeRankingFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TimeRankingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimeRankingFragment extends Fragment implements WebConnectionManager.WebConnectionManagerListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "TimeRankingFragment";


    private String mParam1;
    private String mParam2;

    WebConnectionManager webConnectionManager;

    RecyclerView recyclerView;
    private List<User> users;
    TimeRankingAdapter adapter;
    LinearLayout linearMyPos;

    private OnFragmentInteractionListener mListener;

    public TimeRankingFragment() {
        // Required empty public constructor
    }

    public static TimeRankingFragment newInstance(String param1, String param2) {
        TimeRankingFragment fragment = new TimeRankingFragment();
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
        User user = AppControl.getInstance().currentUser;
        View view = inflater.inflate(R.layout.fragment_time_ranking, container, false);
        Log.d(TAG,"OnCreateView created");
        linearMyPos = (LinearLayout) view.findViewById(R.id.linear_myposition);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_ranking_time);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        if (user.getPosition() < 20) {
            linearMyPos.setVisibility(View.GONE);
        }
        linearMyPos.setVisibility(View.VISIBLE);
        llenarUsers();


        webConnectionManager = WebConnectionManager.getWebConnectionManager();
        webConnectionManager.setWebConnectionManagerListener(this);
        webConnectionManager.mostrarRankings();

        //recyclerView.setAdapter(adapter);
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

    private void llenarUsers() {
        users = new ArrayList<>();

        users.add(new User("Didier","1234",3000,1,47,12,10,2,"marco18"));
        users.add(new User("Carlos","1234",2000,2,57,12,10,2,"marco8"));
        users.add(new User("Harold","1234",1500,3,49,12,10,2,"marco11"));
        users.add(new User("Rich4rd","1234",1322,4,47,12,10,2,"jhonny"));
        users.add(new User("Jefferson","1234",1300,5,47,12,10,2,"marco"));

    }

    @Override
    public void webRequestComplete(WebConnectionManager.Response response) throws JSONException {

        //Log.d("ANTES TIO",response.toString());
        //String orale ="[{\"posicion\":1,\"ide\":1,\"nickname\":\"LUCHO\",\"avatarActivo\":\"1\",\"marcoActivo\":\"1\",\"fondoActivo\":\"1\",\"puntaje\":82,\"numResCorrecta\":8,\"tiempo\":\" 00:42:00\"},{\"posicion\":2,\"ide\":3,\"nickname\":\"CARLOSM\",\"avatarActivo\":\"1\",\"marcoActivo\":\"1\",\"fondoActivo\":\"1\",\"puntaje\":80,\"numResCorrecta\":8,\"tiempo\":\" 01:01:00\"},{\"posicion\":3,\"ide\":5,\"nickname\":\"DANIELA\",\"avatarActivo\":\"1\",\"marcoActivo\":\"1\",\"fondoActivo\":\"1\",\"puntaje\":60,\"numResCorrecta\":6,\"tiempo\":\" 02:00:00\"},{\"posicion\":4,\"ide\":6,\"nickname\":\"JEFERSON\",\"avatarActivo\":\"1\",\"marcoActivo\":\"1\",\"fondoActivo\":\"1\",\"puntaje\":60,\"numResCorrecta\":6,\"tiempo\":\" 02:00:17\"},{\"posicion\":5,\"ide\":2,\"nickname\":\"LUIZ\",\"avatarActivo\":\"1\",\"marcoActivo\":\"1\",\"fondoActivo\":\"1\",\"puntaje\":45,\"numResCorrecta\":5,\"tiempo\":\" 02:04:00\"},{\"posicion\":6,\"ide\":4,\"nickname\":\"CORAL\",\"avatarActivo\":\"1\",\"marcoActivo\":\"1\",\"fondoActivo\":\"1\",\"puntaje\":35,\"numResCorrecta\":3,\"tiempo\":\" 01:02:00\"}]";
        //Log.d("ANTES TIO",response.getData());
        try{
            JSONArray jsonArray = new JSONArray(response.getData());

            List<User> userList = new ArrayList<>();
            for(int  i = 0 ; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                User user =  new User();
                user.setNickname(jsonObject.getString("nickname"));
                user.setScore(Integer.parseInt(jsonObject.getString("puntaje")));
                user.setPosition(Integer.parseInt(jsonObject.getString("posicion")));
                user.setAvatar("marco18");
                userList.add(user);
            }
            users = userList;
            adapter = new TimeRankingAdapter(userList,getActivity());
            recyclerView.setAdapter(adapter);


        }catch (JSONException e){
            e.printStackTrace();
        }


    }
}
