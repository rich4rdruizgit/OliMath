package olimpiadas.sena.com.olimpiadasmath.fragments;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.model.User;


public class RankingFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking, container, false);
        TextView position = (TextView) view.findViewById(R.id.txt_my_position);
        TextView nickname = (TextView) view.findViewById(R.id.txt_my_nickname);
        TextView points = (TextView) view.findViewById(R.id.txt_my_points);
        User user = new User("yo",200,100);
        points.setText(user.getPoints()+"");
        points.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"grobold.ttf"));
        position.setText(user.getPosition()+"");
        position.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"grobold.ttf"));
        nickname.setText(user.getNickname());
        nickname.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"grobold.ttf"));
        return view;
    }
}
