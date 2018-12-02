package olimpiadas.sena.com.olimpiadasmath.fragments.shop;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import olimpiadas.sena.com.olimpiadasmath.R;
import olimpiadas.sena.com.olimpiadasmath.adapter.shop.ProductAdapter;
import olimpiadas.sena.com.olimpiadasmath.control.AppControl;
import olimpiadas.sena.com.olimpiadasmath.model.Product;
import olimpiadas.sena.com.olimpiadasmath.model.User;
import olimpiadas.sena.com.olimpiadasmath.util.webConManager.WebConnectionManager;

public class AvatarShopFragment extends Fragment implements WebConnectionManager.WebConnectionManagerListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private String TAG = "AvatarShopFragment";
    AppControl appControl;

    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    public RealmResults<Product> lstProduct;

    WebConnectionManager webConnectionManager;
    private List<Product> products;

    private OnFragmentInteractionListener mListener;

    public AvatarShopFragment() {
        // Required empty public constructor
    }

    public static AvatarShopFragment newInstance(String param1, String param2) {
        AvatarShopFragment fragment = new AvatarShopFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        appControl = AppControl.getInstance();
        View view = inflater.inflate(R.layout.fragment_avatar_shop, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_avatar_shop);

        GridLayoutManager llm = new GridLayoutManager(getActivity(), 2);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        webConnectionManager = WebConnectionManager.getWebConnectionManager();
        webConnectionManager.setWebConnectionManagerListener(this);
        //webConnectionManager.mostrarTienda();
        webConnectionManager.mostrarTiendaCustom(appControl.currentUser.getId());



        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void webRequestComplete(WebConnectionManager.Response response) throws JSONException {
        try {
            if (response.getStatus() == WebConnectionManager.Response.ERROR) {
                Log.d(TAG, "No se pudo cargar el Tienda");
            } else {
                if (response.getOperationType() == WebConnectionManager.OperationType.SHOW_SHOP_CUSTOM) {

                    Log.d(TAG,"ENTRANDO A LA TIENDA");
                    JSONArray jsonArray = new JSONArray(response.getData());
                    List<Product> productList = new ArrayList<>();

                    Log.d(TAG,"UNODOS"+response.getData());

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Product product = new Product();
                        //int urlImg, String name, int price, String constraint, int state, String sourceName, int type
                        product.setUrlImg(jsonObject.getString("nombre"));
                        product.setName(jsonObject.getString("imagen"));
//                        product.setName(jsonObject.getString("imagen"));
                        product.setPrice(Integer.parseInt(jsonObject.getString("precio")));
                        product.setConstraint(jsonObject.getString("nivelRequerio"));

                        if(Integer.parseInt(jsonObject.getString("comprado")) == 0){
                            product.setState(Product.FOR_BUY);
                        }else if (Integer.parseInt(jsonObject.getString("comprado")) == 1){
                            product.setState(Product.BOUGTH);
                        }if(Integer.parseInt(jsonObject.getString("usado")) == 1 ){
                            product.setState(Product.USED);
                        }

                        product.setSourceName(jsonObject.getString("tipo"));
                        //product.setBuy(Integer.parseInt(jsonObject.getString("comprado")));
                        if (jsonObject.getString("tipo").equals("Avatar")) {
                            product.setType(1);
                        } else {
                            product.setType(2);
                        }

                        productList.add(product);
                    }
                    for (Product p : productList) {
                        Log.d(TAG,"TRES" + p.getName());
                    }
                    products = productList;
                    productAdapter = new ProductAdapter(productList, getActivity(), Product.AVATAR);
                    recyclerView.setAdapter(productAdapter);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
