package olimpiadas.sena.com.olimpiadasmath.adapter.profile;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import olimpiadas.sena.com.olimpiadasmath.fragments.profile.LogroFragment;
import olimpiadas.sena.com.olimpiadasmath.fragments.profile.PerfilFragment;
import olimpiadas.sena.com.olimpiadasmath.fragments.profile.ProfileResultsFragment;

/**
 * Created by rich4 on 10/09/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter{

    final int PAGE_COUNT = 2;

    //private String tabtitles[] = new String[]{"Perfil","Logros","Resultados" };
    private String tabtitles[] = new String[]{"Perfil","Resultados" };
    Context context;


    public ViewPagerAdapter(FragmentManager fm) {
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
                Fragment fragmentPerfil = new PerfilFragment();
                return fragmentPerfil;
            /*case 1:
                Fragment fragmentLogro = new LogroFragment();
                return fragmentLogro;*/
            case 1:
                Fragment fragmentResultado = new ProfileResultsFragment();
                return fragmentResultado;
        }
        return null;
    }

    public CharSequence getPageTitle(int position){
        return tabtitles[position];
    }
}