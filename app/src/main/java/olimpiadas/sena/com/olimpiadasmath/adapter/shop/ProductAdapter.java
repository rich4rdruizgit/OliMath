package olimpiadas.sena.com.olimpiadasmath.adapter.shop;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.activities.shop.ShopActivity;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.model.Product;
import olimpiadas.sena.com.olimpiadasmath.model.User;
import olimpiadas.sena.com.olimpiadasmath.util.DialogHelper;

/**
 * Created by rich4 on 16/06/2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> implements DialogHelper.DialogHelperListener{

    private static final String TAG = ProductAdapter.class.toString();

    //public RealmResults<Product> lstProduct;
    public List<Product> lstProduct;
    public RealmResults<Product> lstProductItem;


    Context context;
    AppControl appControl;
    Realm realm;
    int idCardView;

    int currentAvatar = 1;
    Button currentAvatarButton = null;
    Button currentBuyItem = null;
    int currentPosition = 0;
    private String recurso = "drawable";

    public ProductAdapter(List<Product> lstProduct, Context context, int type) {

        realm = Realm.getDefaultInstance();
        this.lstProduct = lstProduct;
        if(type == Product.AVATAR){
            //lstProduct = realm.where(Product.class).equalTo("type",Product.AVATAR).findAll();
        }
        if(type == Product.POTION){
            lstProduct = realm.where(Product.class).equalTo("type",Product.POTION).findAll();
        }
        //this.lstProduct = (RealmResults<Product>) lstProduct;
        this.context = context;
        appControl = AppControl.getInstance();
    }


    private void updateProductState(final int pos,final int state){

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                lstProduct.get(pos).setState(state);
            }
        });

    }
    private void updateState(){

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Product> result2 = realm.where(Product.class)
                        .equalTo("state", 3)
                        .findAll();
                for(int i  = 0 ; i < result2.size(); i++){
                    result2.get(i).setState(Product.BOUGTH);
                }

            }
        });
    }


    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shop,parent ,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductAdapter.ProductViewHolder holder, final int position) {
        final Product product = lstProduct.get(position);
        Log.d(TAG,product.toString());
        //int avatar = getActivity().getBaseContext().getResources().getIdentifier(appControl.currentUser.getAvatar(), recurso, context.getActivity().getBaseContext().getPackageName());


        int avatar = context.getResources().getIdentifier(product.getUrlImg(),recurso,context.getPackageName());

        holder.imgViewShop.setImageResource(avatar);
        holder.txtName.setText(product.getName());
        holder.txtPrice.setText("" + product.getPrice());
        holder.txtConstraint.setText(product.getConstraint());
        if(product.getState()==Product.FOR_BUY){
            holder.btnBuy.setText(""+product.getPrice());
        }
        if(product.getState()==Product.BOUGTH){
            if(product.getType() == Product.POTION){
                holder.btnBuy.setText("Comprado");
            }else
                holder.btnBuy.setText("Usar");
        }
        if(product.getState()==Product.USED){
            holder.btnBuy.setText("Usado");
            currentAvatarButton = holder.btnBuy;
        }

        holder.btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appControl.soundButtonPlay();
                if(Integer.parseInt(product.getConstraint()) <= appControl.currentUser.getLevel()){
                    if(product.getState() == Product.FOR_BUY){ // para comprar
                        currentBuyItem = holder.btnBuy;
                        currentPosition = position;

                        if(appControl.currentUser.getCoins()< lstProduct.get(position).getPrice()){
                            DialogHelper.ConfimrBuyDialog(context,context.getString(R.string.no_enought_coins),DialogHelper.NO_BUY,ProductAdapter.this);
                            return;
                        }else {
                            DialogHelper.ConfimrBuyDialog(context,context.getString(R.string.alert_shop),DialogHelper.BUY,ProductAdapter.this);
                        }
                    }
                    else if(product.getState() == Product.BOUGTH){
                        if(product.getType() == Product.POTION){
                            Toast.makeText(context, "Solo puedes comprar una pocion", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if(currentAvatarButton != null ){
                            currentAvatarButton.setText("Usar");
                        }
                        currentAvatarButton = holder.btnBuy;
                        holder.btnBuy.setText("Usado");
                        currentAvatar = position;
                        updateState();
                        appControl.currentUser.setAvatar(product.getSourceName());
                        ProductAdapter.this.updateProductState(position,Product.USED);
                        User.updateUser(appControl.currentUser);
                        ((ShopActivity)context).headerFragment.refreshInterface();
                    }
                    if(product.getState() == Product.USED){

                    }
                }else{
                    Toast.makeText(context, "Debes subir de nivel para comprar este avatar", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return lstProduct.size();
    }

    @Override
    public void dialogEnd(boolean result) {

        if(result){
                if(lstProduct.get(currentPosition).getType()== Product.AVATAR){
                appControl.currentUser.setCoins(appControl.currentUser.getCoins() - lstProduct.get(currentPosition).getPrice());
                currentBuyItem.setText("Usar");
                ProductAdapter.this.updateProductState(currentPosition,Product.BOUGTH);
                ((ShopActivity)context).headerFragment.refreshInterface();
                User.updateUser(appControl.currentUser);
                Toast.makeText(context, "Felicidades, has adquirido este nuevo item", Toast.LENGTH_SHORT).show();
            }else{
                    appControl.currentUser.setCoins(appControl.currentUser.getCoins() - lstProduct.get(currentPosition).getPrice());
                    currentBuyItem.setText("Comprado");
                    ProductAdapter.this.updateProductState(currentPosition,Product.BOUGTH);
                    Toast.makeText(context,"Felicidades has adquirido una poción", Toast.LENGTH_SHORT).show();
                }
        }
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
