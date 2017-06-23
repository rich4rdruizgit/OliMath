package olimpiadas.sena.com.olimpiadasmath.activities.statistics;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import olimpiadas.sena.com.olimpiadasmath.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class StatisticActivity extends AppCompatActivity {

    PieChart grafica;
    int respuestas[] = {40,20,35,11};
    String temas[] = {"Regla de 3", "Fraccionarios","Potencias","Geometría"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        grafica = (PieChart) findViewById(R.id.grafica);
        cargarGrafica();
    }

    private void cargarGrafica() {
        List<PieEntry> listaDatos = new ArrayList<>();
        for (int i = 0; i < respuestas.length; i++) {
            PieEntry pieEntry = new PieEntry(respuestas[i],temas[i]);
            listaDatos.add(pieEntry);
        }
        PieDataSet dataSet = new PieDataSet(listaDatos, "Rendimiento en temas");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData pieData = new PieData(dataSet);
        grafica.setData(pieData);
        grafica.invalidate();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
