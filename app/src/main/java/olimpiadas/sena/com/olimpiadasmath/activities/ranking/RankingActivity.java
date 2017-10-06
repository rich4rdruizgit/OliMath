package olimpiadas.sena.com.olimpiadasmath.activities.ranking;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.RecyclerView;
import android.view.View;


import java.util.List;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.adapter.profile.ViewPagerAdapter;
import olimpiadas.sena.com.olimpiadasmath.adapter.ranking.RankingAdapter;

import olimpiadas.sena.com.olimpiadasmath.adapter.ranking.ViewPagerRankingAdapter;
import olimpiadas.sena.com.olimpiadasmath.fragments.challenge.QuestionFragment;
import olimpiadas.sena.com.olimpiadasmath.fragments.ranking.CoinsRankingFragment;
import olimpiadas.sena.com.olimpiadasmath.fragments.ranking.GeneralRankingFragment;
import olimpiadas.sena.com.olimpiadasmath.fragments.ranking.TimeRankingFragment;
import olimpiadas.sena.com.olimpiadasmath.model.User;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RankingActivity extends AppCompatActivity {

    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        getSupportActionBar().hide();
        viewPager = (ViewPager) findViewById(R.id.vpPagerRanking);
        viewPager.setAdapter(new ViewPagerRankingAdapter(getSupportFragmentManager()));
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


}
