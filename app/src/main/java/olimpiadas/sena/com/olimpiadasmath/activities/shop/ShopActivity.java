package olimpiadas.sena.com.olimpiadasmath.activities.shop;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.menu.MainActivity;
import olimpiadas.sena.com.olimpiadasmath.adapter.shop.ProductAdapter;
import olimpiadas.sena.com.olimpiadasmath.fragments.header.HeaderFragment;
import olimpiadas.sena.com.olimpiadasmath.model.Product;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ShopActivity extends AppCompatActivity {

    RecyclerView recyclerViewShop;
    ProductAdapter adapter;
    List<Product> list;
    public HeaderFragment headerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        getSupportActionBar().hide();
        recyclerViewShop = (RecyclerView) findViewById(R.id.recycler_shop);

        headerFragment = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_shop_header);


        // Adaptacion del recycler con el item shop

        GridLayoutManager llm = new GridLayoutManager(this, 2);
        //LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(GridLayoutManager.VERTICAL);
        recyclerViewShop.setLayoutManager(llm);
        list = llenarLista();
        adapter = new ProductAdapter(list,this);
        recyclerViewShop.setAdapter(adapter);
    }

    public static List<Product> llenarLista(){
        List<Product>  lista =  new ArrayList<>();
        lista.add(new Product(R.mipmap.ic_launcher,"View Animator",5,"10",Product.FOR_BUY,""));
        lista.add(new Product(R.mipmap.ic_launcher,"View Animator",20,"10",Product.FOR_BUY,""));
        lista.add(new Product(R.mipmap.ic_launcher,"View Animator",100,"10",Product.FOR_BUY,""));
        lista.add(new Product(R.mipmap.ic_launcher,"View Animator",2500,"10",Product.FOR_BUY,""));
        lista.add(new Product(R.mipmap.ic_launcher,"View Animator",2500,"10",Product.FOR_BUY,""));
        lista.add(new Product(R.mipmap.ic_launcher,"View Animator",2500,"10",Product.FOR_BUY,""));
        lista.add(new Product(R.mipmap.ic_launcher,"View Animator",2500,"10",Product.FOR_BUY,""));
        return lista;
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ShopActivity.this, MainActivity.class));
    }
}
