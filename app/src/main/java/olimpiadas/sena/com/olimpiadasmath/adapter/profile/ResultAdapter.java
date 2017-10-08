package olimpiadas.sena.com.olimpiadasmath.adapter.profile;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.model.Result;

/**
 * Created by didier on 26/09/2017.
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {

    private RealmResults<Result> results;
    private Context context;

    public ResultAdapter(List<Result> resultsold , Context context) {
        Realm realm = Realm.getDefaultInstance();
        results = realm.where(Result.class).findAll();
        //this.results = results;
        this.context = context;
    }

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result_profile, parent, false);
        return new ResultAdapter.ResultViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {

        Result result = results.get(position);


        holder.txtRightAnswer.setText(""+result.getAnswerCorrectResult());
        holder.txtWrongAnswer.setText(""+result.getAnswerIncorrectResult());
        holder.txtCoinsWin.setText(""+result.getCoinsWinResult());
        holder.txtTime.setText(""+result.getTimeGlobalResult());


    }

    @Override
    public int getItemCount() {
        return results.size();
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

            btnPublish.setVisibility(View.INVISIBLE);
        }
    }
}
