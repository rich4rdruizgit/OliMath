package olimpiadas.sena.com.olimpiadasmath.fragments.profile;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import olimpiadas.sena.com.olimpiadasmath.R;

/**
 * Created by andres on 20/06/2017.
 */

public class StatisticFragment extends Fragment {
    PieChart grafica;
    int respuestas[] = {40,20,35,11};
    String temas[] = {"Regla de 3", "Fraccionarios","Potencias","Geometría"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistic, container, false);
        ((TextView)view.findViewById(R.id.txt_title_statistic)).setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"grobold.ttf"));
        grafica = (PieChart) view.findViewById(R.id.grafica);
        cargarGrafica();
        return view;
    }

    private void cargarGrafica() {
        List<PieEntry> listaDatos = new ArrayList<>();
        for (int i = 0; i < respuestas.length; i++) {
            PieEntry pieEntry = new PieEntry(respuestas[i],temas[i]);
            listaDatos.add(pieEntry);
        }
        PieDataSet dataSet = new PieDataSet(listaDatos, "TEMAS");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData pieData = new PieData(dataSet);
        grafica.setData(pieData);
        grafica.invalidate();
    }
}
