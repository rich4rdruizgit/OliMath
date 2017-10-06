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
 * Created by rich4 on 6/10/2017.
 */

public class CoinsRankingAdapter extends RecyclerView.Adapter<CoinsRankingAdapter.CoinsRankingViewHolder> {

    private List<User> users;
    private Context context;

    public CoinsRankingAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public CoinsRankingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ranking_coins,parent,false);
        return new CoinsRankingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CoinsRankingViewHolder holder, int position) {
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

    public class CoinsRankingViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtPosition;
        protected TextView txtNickName;
        protected TextView txtPoints;
        protected ImageView imgAvatarRanking;
        protected LinearLayout layout;

        public CoinsRankingViewHolder(View itemView) {
            super(itemView);
            txtPosition = (TextView) itemView.findViewById(R.id.txt_position_ranking_coins);
            txtNickName = (TextView) itemView.findViewById(R.id.txt_nickname_ranking_coins);
            txtPoints = (TextView) itemView.findViewById(R.id.txt_points_ranking_coins);
            imgAvatarRanking = (ImageView) itemView.findViewById(R.id.img_avatar_ranking_coins);
            layout = (LinearLayout) itemView.findViewById(R.id.layout_ranking_coins);
        }
    }
}
