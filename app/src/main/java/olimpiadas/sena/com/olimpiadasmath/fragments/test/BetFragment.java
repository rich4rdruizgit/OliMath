package olimpiadas.sena.com.olimpiadasmath.fragments.test;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.test.Communication;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;

/**
 * A simple {@link Fragment} subclass.
 */
public class BetFragment extends Fragment implements View.OnClickListener {


    Communication communication;
    Button btnBetAcept;
    int bet =0;
    AppControl appControl = AppControl.getInstance();
    public BetFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bet, container, false);
        btnBetAcept = (Button) view.findViewById(R.id.btn_bet_acept);

        btnBetAcept.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_bet_acept:
                if(appControl.isBackgroundPlaying)
                    appControl.soundButton.start();
                bet = 1;
                communication.sendBet(bet);
                break;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        communication = (Communication) context;
    }


}
