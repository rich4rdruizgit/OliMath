package olimpiadas.sena.com.olimpiadasmath.adapter.profile;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import olimpiadas.sena.com.olimpiadasmath.model.User;

/**
 * Created by didier on 26/09/2017.
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ResultViewHolder> {

    private List<User> results;
    private Context context;

    @Override
    public ResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ResultViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ResultViewHolder extends RecyclerView.ViewHolder{
        public ResultViewHolder(View itemView) {
            super(itemView);
        }
    }
}
