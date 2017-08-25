package olimpiadas.sena.com.olimpiadasmath.adapter.library;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.library.LibraryActivity;
import olimpiadas.sena.com.olimpiadasmath.model.Topic;

/**
 * Created by jyanguas on 29/06/2017.
 * Modificado por Mile 17/07/2017
 */

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder>{

    public Context context;
    List<Topic> topics;

    public TopicAdapter(Context ctx) {

        context = ctx;

        topics = new ArrayList<Topic>();
        topics.add(new Topic("Ecuaciones"));
        topics.add(new Topic("Geometría"));
        topics.add(new Topic("Tema3"));
        topics.add(new Topic("Tema4"));

    }


    @Override
    public TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_library,parent ,false);
        return new TopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopicViewHolder holder, int position) {


        holder.txtName.setText(topics.get(position).getName());



    }

    @Override
    public int getItemCount() {
        return topics.size();
    }




    public static class TopicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        protected TextView txtName;



        public TopicViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            txtName = (TextView) itemView.findViewById(R.id.txt_name_topic);


        }

        @Override
        public void onClick(View v) {

            Toast toast =
                    Toast.makeText(v.getContext(),
                            "Toast por defecto", Toast.LENGTH_SHORT);

            toast.show();
        }
    }
}
