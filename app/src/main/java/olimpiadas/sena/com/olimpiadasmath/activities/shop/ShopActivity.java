package olimpiadas.sena.com.olimpiadasmath.activities.shop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.adapter.shop.ProductAdapter;
import olimpiadas.sena.com.olimpiadasmath.model.Product;

public class ShopActivity extends AppCompatActivity {

    RecyclerView recyclerViewShop;
    ProductAdapter adapter;
    List<Product> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        getSupportActionBar().hide();
        recyclerViewShop = (RecyclerView) findViewById(R.id.recycler_shop);

        // Adaptacion del recycler con el item shop

        GridLayoutManager llm = new GridLayoutManager(this, 2);
        //LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(GridLayoutManager.VERTICAL);
        recyclerViewShop.setLayoutManager(llm);
        list = llenarLista();
        adapter = new ProductAdapter(list);
        recyclerViewShop.setAdapter(adapter);
    }

    public static List<Product> llenarLista(){
        List<Product>  lista =  new ArrayList<>();
        lista.add(new Product(R.mipmap.ic_launcher,"View Animator","2500","10"));
        lista.add(new Product(R.mipmap.ic_launcher,"View Animator","2500","10"));
        lista.add(new Product(R.mipmap.ic_launcher,"View Animator","2500","10"));
        lista.add(new Product(R.mipmap.ic_launcher,"View Animator","2500","10"));
        lista.add(new Product(R.mipmap.ic_launcher,"View Animator","2500","10"));
        lista.add(new Product(R.mipmap.ic_launcher,"View Animator","2500","10"));
        lista.add(new Product(R.mipmap.ic_launcher,"View Animator","2500","10"));
        return lista;
    }
}
