package olimpiadas.sena.com.olimpiadasmath.adapter.ranking;

import android.content.Context;
import android.service.notification.NotificationListenerService;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.model.User;
import olimpiadas.sena.com.olimpiadasmath.util.CircleImage.CropCircleTransformation;
import olimpiadas.sena.com.olimpiadasmath.util.webConManager.WebConnectionManager;

/**
 * Created by rich4 on 5/10/2017.
 */

public class TimeRankingAdapter extends RecyclerView.Adapter<TimeRankingAdapter.TimeRankingViewHolder>
        implements WebConnectionManager.WebConnectionManagerListener
{

    private List<User> users;
    private Context context;

    //Conexion ws
    WebConnectionManager webConnectionManager;
    String URL = "http://192.168.43.124:8097/WSRankings.asmx/mostrarRankings";

    public TimeRankingAdapter(List<User> users,Context context) {
        this.users = users;
        this.context = context;
    }

    @Override
    public TimeRankingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ranking_time,parent,false);
        return new TimeRankingViewHolder(view);
    }

    private User getRanking(JSONArray rankingJson){
        HashMap<String, String > rankingMap= new HashMap<>();

        User user = new User();
        Log.d("json response", rankingJson.toString());
        try {
            user.setNickname(rankingJson.getJSONObject(0).getJSONObject("nickname").getString("text"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
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
                userList.add(user);
            }
            users = userList;
            for(int  i = 0 ; i < jsonArray.length(); i++){
                Log.d("USER", userList.get(i).getNickname());
            }

        }catch (JSONException e){
            e.printStackTrace();
        }


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
