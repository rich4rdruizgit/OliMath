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

    private List<Achievements> achievementses;
    private Context context;

    public AchievementAdapter(List<Achievements> achievementses, Context context) {
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
        Achievements user = achievementses.get(position);

        holder.txtAchievement.setText(user.getNameAchievement()+"");
        holder.txtRewardMoney.setText(user.getRewardMoney());
        int avatar = context.getResources().getIdentifier(user.getImageAchievement(),"drawable", context.getPackageName());

        holder.imgAchievement.setImageResource(avatar);

        /*Picasso.with(holder.imgAchievement.getContext())
                .load(avatar)
                .transform(new CropCircleTransformation())
                .into(holder.imgAchievement);
    */
    }

    @Override
    public int getItemCount() {
        return achievementses.size();
    }

    public class AchievementViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imgAchievement;
        protected TextView txtAchievement;
        protected TextView txtRewardMoney;



        public AchievementViewHolder(View itemView) {
            super(itemView);
            imgAchievement = (ImageView) itemView.findViewById(R.id.img_achievement);
            txtAchievement = (TextView) itemView.findViewById(R.id.txt_achievement);
            txtRewardMoney = (TextView) itemView.findViewById(R.id.txt_reward_money);
        }
    }
}
