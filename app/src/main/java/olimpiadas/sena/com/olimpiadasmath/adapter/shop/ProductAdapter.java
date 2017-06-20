package olimpiadas.sena.com.olimpiadasmath.adapter.shop;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.model.Product;

/**
 * Created by rich4 on 16/06/2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{

    private static final String TAG = ProductAdapter.class.toString();
    public List<Product> lstProduct;
    static Context context;

    public ProductAdapter(List<Product> lstProduct) {
        this.lstProduct = lstProduct;
    }

    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop,parent ,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ProductViewHolder holder, int position) {
        Product product = lstProduct.get(position);
        Log.d(TAG,product.toString());
        holder.imgViewShop.setBackgroundResource(product.getUrlImg());
        holder.txtName.setText(product.getName());
        holder.txtPrice.setText(product.getPrice());
        holder.txtConstraint.setText(product.getConstraint());
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


        public ProductViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            imgViewShop = (ImageView) itemView.findViewById(R.id.img_view_shop);
            txtName = (TextView) itemView.findViewById(R.id.txt_name_shop);
            txtPrice = (TextView) itemView.findViewById(R.id.txt_price_shop);
            txtConstraint = (TextView) itemView.findViewById(R.id.txt_const_level);

        }

        @Override
        public void onClick(View v) {

        }
    }
}
