package olimpiadas.sena.com.olimpiadasmath.activities.shop;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import java.util.List;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.menu.MainActivity;
import olimpiadas.sena.com.olimpiadasmath.adapter.shop.ProductAdapter;
import olimpiadas.sena.com.olimpiadasmath.adapter.shop.ViewPagerShopAdapter;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.fragments.header.HeaderFragment;
import olimpiadas.sena.com.olimpiadasmath.fragments.shop.AvatarShopFragment;
import olimpiadas.sena.com.olimpiadasmath.fragments.shop.ItemShopFragment;
import olimpiadas.sena.com.olimpiadasmath.model.Product;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ShopActivity extends AppCompatActivity implements AvatarShopFragment.OnFragmentInteractionListener,
        ItemShopFragment.OnFragmentInteractionListener{

    RecyclerView recyclerViewShop;
    ProductAdapter adapter;
    List<Product> list;
    public HeaderFragment headerFragment;

    ViewPager viewPager;
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        getSupportActionBar().hide();

        AppControl.getInstance().currentActivity = ShopActivity.class.getSimpleName();

        headerFragment = (HeaderFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_shop_header);

        viewPager = (ViewPager) findViewById(R.id.vpPagerShop);
        viewPager.setAdapter(new ViewPagerShopAdapter(getSupportFragmentManager()));

        btnBack = (ImageButton) findViewById(R.id.btn_back_shop);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goBack = new Intent(ShopActivity.this, MainActivity.class);
                startActivity(goBack);
            }
        });

    }



    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ShopActivity.this, MainActivity.class));
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
