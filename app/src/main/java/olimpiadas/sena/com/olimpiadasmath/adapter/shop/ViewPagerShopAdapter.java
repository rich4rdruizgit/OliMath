package olimpiadas.sena.com.olimpiadasmath.adapter.shop;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import olimpiadas.sena.com.olimpiadasmath.fragments.shop.AvatarShopFragment;
import olimpiadas.sena.com.olimpiadasmath.fragments.shop.ItemShopFragment;

/**
 * Created by rich4 on 14/09/2017.
 */

public class ViewPagerShopAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 1;

    //private String tabtitles[] = new String[]{"Avatars","Items"};
    private String tabtitles[] = new String[]{"Avatars"};
    Context context;

    public ViewPagerShopAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                Fragment fragmentAvatarShop = new AvatarShopFragment();
                return fragmentAvatarShop;
            /*case 1:
                Fragment fragmentItemShop = new ItemShopFragment();
                return fragmentItemShop;*/
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
    public CharSequence getPageTitle(int position){
        return tabtitles[position];
    }
}
