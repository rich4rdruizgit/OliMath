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
 * Created by andres on 19/06/2017.
 */

public class RankingAdapter extends RecyclerView.Adapter<RankingAdapter.RankingViewHolder> {

    private List<User> users;
    private Context context;

    public RankingAdapter(List<User> users, Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public RankingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ranking, parent, false);

        return new RankingViewHolder(view);
    }
    @Override
    public void onBindViewHolder(RankingViewHolder holder, int position) {

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

//        if(user.getNickname().equals(AppControl.getInstance().currentUser.getNickname())){
//            holder.layout.setBackgroundResource(R.drawable.scall);
//            holder.layout.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
//            holder.txtPosition.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
//            holder.txtNickName.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
//            holder.txtPoints.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
//        }
    }
    @Override
    public int getItemCount() {
        return users.size();
    }


    public class RankingViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtPosition;
        protected TextView txtNickName;
        protected TextView txtPoints;
        protected ImageView imgAvatarRanking;
        protected LinearLayout layout;

        public RankingViewHolder(View itemView) {
            super(itemView);
            txtPosition = (TextView) itemView.findViewById(R.id.txt_position_ranking);
            txtNickName = (TextView) itemView.findViewById(R.id.txt_nickname_ranking);
            txtPoints = (TextView) itemView.findViewById(R.id.txt_points_ranking);
            imgAvatarRanking = (ImageView) itemView.findViewById(R.id.img_avatar_ranking);
            layout = (LinearLayout) itemView.findViewById(R.id.layout_ranking);
        }
    }
}
