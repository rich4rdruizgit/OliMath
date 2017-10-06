package olimpiadas.sena.com.olimpiadasmath.adapter.ranking;

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
import olimpiadas.sena.com.olimpiadasmath.model.User;
import olimpiadas.sena.com.olimpiadasmath.util.CircleImage.CropCircleTransformation;

/**
 * Created by rich4 on 5/10/2017.
 */

public class TimeRankingAdapter extends RecyclerView.Adapter<TimeRankingAdapter.TimeRankingViewHolder> {

    private List<User> users;
    private Context context;

    public TimeRankingAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public TimeRankingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ranking_time,parent,false);
        return new TimeRankingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TimeRankingViewHolder holder, int position) {
        User user = users.get(position);
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
        return users.size();
    }


    public class TimeRankingViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtPosition;
        protected TextView txtNickName;
        protected TextView txtPoints;
        protected ImageView imgAvatarRanking;
        protected LinearLayout layout;

        public TimeRankingViewHolder(View itemView) {
            super(itemView);
            txtPosition = (TextView) itemView.findViewById(R.id.txt_position_ranking_time);
            txtNickName = (TextView) itemView.findViewById(R.id.txt_nickname_ranking_time);
            txtPoints = (TextView) itemView.findViewById(R.id.txt_points_ranking_time);
            imgAvatarRanking = (ImageView) itemView.findViewById(R.id.img_avatar_ranking_time);
            layout = (LinearLayout) itemView.findViewById(R.id.layout_ranking_time);
        }
    }
}
