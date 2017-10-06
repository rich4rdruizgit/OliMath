package olimpiadas.sena.com.olimpiadasmath.adapter.ranking;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import olimpiadas.sena.com.olimpiadasmath.fragments.ranking.CoinsRankingFragment;
import olimpiadas.sena.com.olimpiadasmath.fragments.ranking.GeneralRankingFragment;
import olimpiadas.sena.com.olimpiadasmath.fragments.ranking.TimeRankingFragment;

/**
 * Created by rich4 on 2/10/2017.
 */

public class ViewPagerRankingAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 2;

    private String tabtitles[] = new String[]{"Tiempo","Monedas"};

    public ViewPagerRankingAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Fragment timeRankingFragment = new TimeRankingFragment();
                Log.d("Posicion Fragment", position+"");
                return timeRankingFragment;

            case 1:
                Fragment coinsRankingFragment = new CoinsRankingFragment();
                Log.d("Posicion Fragment", position+"");
                return coinsRankingFragment;
            /*case 2:'
                Fragment generalRankingFragment = new GeneralRankingFragment();
                Log.d("Posicion Fragment", position+"");
                return generalRankingFragment;*/

        }
        return null;
    }

    public CharSequence getPageTitle(int position){

        return tabtitles[position];

    }
}
