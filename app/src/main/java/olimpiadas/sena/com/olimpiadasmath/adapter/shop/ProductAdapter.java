package olimpiadas.sena.com.olimpiadasmath.adapter.shop;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.shop.ShopActivity;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.model.Product;

/**
 * Created by rich4 on 16/06/2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    private static final String TAG = ProductAdapter.class.toString();

    public RealmResults<Product> lstProduct;
    static Context context;
    AppControl appControl;

    public ProductAdapter(List<Product> lstProduct, Context context) {

        Realm realm = Realm.getDefaultInstance();
        lstProduct = realm.where(Product.class).findAll();
        this.lstProduct = (RealmResults<Product>) lstProduct;
        this.context = context;
        appControl = AppControl.getInstance();
    }

    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop,parent ,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductAdapter.ProductViewHolder holder, final int position) {
        Product product = lstProduct.get(position);
        Log.d(TAG,product.toString());
        holder.imgViewShop.setImageResource(product.getUrlImg());

        Picasso.with(holder.imgViewShop.getContext())
                .load(lstProduct.get(position).getUrlImg())
                .resize(200,200)
                .into(holder.imgViewShop);


        holder.txtName.setText(product.getName());
        holder.txtPrice.setText("" + product.getPrice());
        holder.txtConstraint.setText(product.getConstraint());
        if(product.getState()==Product.FOR_BUY){
            holder.btnBuy.setText(""+product.getPrice());
        }
        if(product.getState()==Product.BOUGTH){
            holder.btnBuy.setText("Usar");
        }
        if(product.getState()==Product.USED){
            holder.btnBuy.setText("Usado");
        }

        holder.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(appControl.currentUser.getCoins()< lstProduct.get(position).getPrice()){
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setMessage(R.string.no_enought_coins)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            ;
                    alert.create();
                    alert.show();
                    return;
                }else {
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setMessage(R.string.alert_shop)
                            .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    appControl.currentUser.setCoins(appControl.currentUser.getCoins() - lstProduct.get(position).getPrice());
                                    holder.btnBuy.setText("Usar");
                                    ((ShopActivity)context).headerFragment.refreshInterface();
                                    Toast.makeText(context, "Felicidades, has adquirido este nuevo item", Toast.LENGTH_SHORT).show();
                                    //scontext.startActivity(new Intent(context, MainActivity.class));
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });
                    alert.create();
                    alert.show();
                }


            }
        });
    }

    @Override
    public int getItemCount() {
        return lstProduct.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        protected ImageView imgViewShop;
        protected TextView txtName;
        protected TextView txtPrice;
        protected TextView txtConstraint;
        protected Button btnBuy;


        public ProductViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imgViewShop = (ImageView) itemView.findViewById(R.id.img_view_shop);
            txtName = (TextView) itemView.findViewById(R.id.txt_name_shop);
            txtPrice = (TextView) itemView.findViewById(R.id.txt_price_shop);
            txtConstraint = (TextView) itemView.findViewById(R.id.txt_const_level);
            btnBuy = (Button) itemView.findViewById(R.id.btn_buy_product);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
