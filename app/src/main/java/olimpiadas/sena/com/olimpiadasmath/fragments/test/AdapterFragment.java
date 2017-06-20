package olimpiadas.sena.com.olimpiadasmath.fragments.test;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import olimpiadas.sena.com.olimpiadasmath.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdapterFragment extends Fragment {


    public AdapterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_adapter, container, false);
    }

}
