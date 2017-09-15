package olimpiadas.sena.com.olimpiadasmath.adapter.profile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.adapter.ranking.RankingAdapter;
import olimpiadas.sena.com.olimpiadasmath.model.Achievements;
import olimpiadas.sena.com.olimpiadasmath.model.User;
import olimpiadas.sena.com.olimpiadasmath.util.CircleImage.CropCircleTransformation;

/**
 * Created by rich4 on 10/09/2017.
 */

public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.AchievementViewHolder> {

    private List<User> achievementses;
    private Context context;

    public AchievementAdapter(List<User> achievementses, Context context) {
        this.achievementses = achievementses;
        this.context = context;
    }

    @Override
    public AchievementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_achievement, parent, false);
        return new AchievementViewHolder(view);
    }


    @Override
    public void onBindViewHolder(AchievementAdapter.AchievementViewHolder holder, int position) {
        User user = achievementses.get(position);
        holder.txtPosition.setText(user.getPosition()+"");
        holder.txtNickName.setText(user.getNickname()+"");
        holder.txtPoints.setText(user.getScore()+"");

        int avatar = context.getResources().getIdentifier(user.getAvatar(),"drawable", context.getPackageName());

        holder.imgAvatarRanking.setImageResource(avatar);
        Picasso.with(holder.imgAvatarRanking.getContext())
                .load(avatar)
                .transform(new CropCircleTransformation())
                .into(holder.imgAvatarRanking);

    }

    @Override
    public int getItemCount() {
        return achievementses.size();
    }

    public class AchievementViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtPosition;
        protected TextView txtNickName;
        protected TextView txtPoints;
        protected ImageView imgAvatarRanking;
        protected LinearLayout layout;

        public AchievementViewHolder(View itemView) {
            super(itemView);
            txtPosition = (TextView) itemView.findViewById(R.id.txt_position_ranking);
            txtNickName = (TextView) itemView.findViewById(R.id.txt_nickname_ranking);
            txtPoints = (TextView) itemView.findViewById(R.id.txt_points_ranking);
            imgAvatarRanking = (ImageView) itemView.findViewById(R.id.img_avatar_ranking);
            layout = (LinearLayout) itemView.findViewById(R.id.layout_ranking);
        }
    }
}
