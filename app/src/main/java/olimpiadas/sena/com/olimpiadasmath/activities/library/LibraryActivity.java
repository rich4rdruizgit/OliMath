package olimpiadas.sena.com.olimpiadasmath.activities.library;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.adapter.library.TopicAdapter;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
/* Modificado por Mile 17/07/2017*/
public class LibraryActivity extends AppCompatActivity {

    TopicAdapter adapter;
    RecyclerView listElements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        listElements=(RecyclerView) findViewById(R.id.recycler_library);
        listElements.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        listElements.setLayoutManager(llm);
        adapter = new TopicAdapter(this);
        listElements.setAdapter(adapter);

    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}



