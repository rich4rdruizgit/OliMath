package olimpiadas.sena.com.olimpiadasmath.adapter.profile;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.model.User;

/**
 * Created by didier on 26/09/2017.
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {

    private List<User> results;
    private Context context;

    public ResultAdapter(List<User> results, Context context) {
        this.results = results;
        this.context = context;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result_profile, parent, false);
        return new ResultAdapter.ResultViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {

        User user = results.get(position);
        /* falta tabla para guardar respuestas

        holder.txtRightAnswer.setText();
        holder.txtWrongAnswer.setText();
        holder.txtCoinsWin.setText();
        holder.txtTime.setText();
        */

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder{

        protected TextView txtRightAnswer;
        protected TextView txtWrongAnswer;
        protected TextView txtCoinsWin;
        protected TextView txtTime;
        protected Button btnPublish;


        public ResultViewHolder(View itemView) {
            super(itemView);
            txtRightAnswer = (TextView) itemView.findViewById(R.id.txt_result_profile_rigth_answer_number);
            txtWrongAnswer = (TextView) itemView.findViewById(R.id.txt_result_profile_wrong_answer_number);
            txtCoinsWin = (TextView) itemView.findViewById(R.id.txt_result_profile_win_coins_number);
            txtTime = (TextView) itemView.findViewById(R.id.txt_result_profile_time);
            btnPublish = (Button) itemView.findViewById(R.id.btn_publish_result_profile);
        }
    }
}
