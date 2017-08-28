package olimpiadas.sena.com.olimpiadasmath.fragments.ranking;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.model.User;
import olimpiadas.sena.com.olimpiadasmath.util.CircleImage.CropCircleTransformation;


public class RankingFragment extends Fragment {

    AppControl appControl;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ranking, container, false);
        TextView position = (TextView) view.findViewById(R.id.txt_my_position);
        TextView nickname = (TextView) view.findViewById(R.id.txt_my_nickname);
        TextView points = (TextView) view.findViewById(R.id.txt_my_points);
        ImageView imgRanking = (ImageView) view.findViewById(R.id.img_my_avatar_fragment_ranking);

        User user = AppControl.getInstance().currentUser;
        appControl = AppControl.getInstance();

        points.setText(user.getScore()+"");
        position.setText(user.getPosition()+"");
        nickname.setText(user.getNickname());

        nickname.setText(appControl.currentUser.getNickname());
        position.setText((appControl.currentUser.getPosition()+""));
        points.setText((appControl.currentUser.getScore()+""));

        int avatar = getResources().getIdentifier(appControl.currentUser.getAvatar(),"drawable", getActivity().getPackageName());
        imgRanking.setImageResource(avatar);
        Picasso.with(imgRanking.getContext())
                .load(avatar)
                .transform(new CropCircleTransformation())
                .into(imgRanking);

        return view;
    }


}
