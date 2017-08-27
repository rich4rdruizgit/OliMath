package olimpiadas.sena.com.olimpiadasmath.activities.ranking;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.adapter.ranking.RankingAdapter;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.model.User;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RankingActivity extends AppCompatActivity {

    View fragment;
    RecyclerView recyclerViewRanking;
    //private List<User> users;
    private List<User> users;
    RankingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ranking);
        getSupportActionBar().hide();
        recyclerViewRanking = (RecyclerView) findViewById(R.id.recycler_ranking);


        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewRanking.setLayoutManager(llm);

//        User user = new User("yo", 10, 100);
        User user = AppControl.getInstance().currentUser;
        if (user.getPosition() < 20) {
            fragment = findViewById(R.id.fragment2);
            fragment.setVisibility(View.INVISIBLE);
        }

        llenarUsers();
        adapter = new RankingAdapter(users, this);
        recyclerViewRanking.setAdapter(adapter);
    }

    private void llenarUsers() {
        users = new ArrayList<>();

        users.add(new User("Rich4rd","1234",1322,4,47,12,10,2,"jhonny"));
        users.add(new User("Harold","1234",1500,3,49,12,10,2,"marco11"));
        users.add(new User("Jefferson","1234",1300,5,47,12,10,2,"marco"));
        users.add(new User("Carlos","1234",2000,2,57,12,10,2,"marco8"));
        users.add(new User("Didier","1234",10000,1,47,12,10,2,"marco18"));

        /*for (int i = 1; i <= 10; i++) {
            if (i%2 == 0){
            users.add(new User("rich4rd","1234",1322,7,47,12,10,2,"jhonny"));
                }else{
                users.add(new User("harold","1234",1322,7,47,12,10,2,"marco18"));
            }
        }*/
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
