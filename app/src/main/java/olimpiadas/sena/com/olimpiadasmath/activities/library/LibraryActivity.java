package olimpiadas.sena.com.olimpiadasmath.activities.library;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import olimpiadas.sena.com.olimpiadasmath.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LibraryActivity extends AppCompatActivity {

    ArrayAdapter<String> elemntsList;
    ListView listElements;

    String[] programs = {
            "Element 1",
            "Element 2",
            "Element 3",
            "Element 4",
            "Element 5"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        listElements=(ListView)findViewById(R.id.list_elements);

        elemntsList = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,programs);
        listElements.setAdapter(elemntsList);

        listElements.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LibraryActivity.this, TopicInformationActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
            });
    }
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}



