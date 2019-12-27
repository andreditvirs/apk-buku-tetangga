package com.example.buku_tetangga;


import android.animation.ArgbEvaluator;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.buku_tetangga.Adaptors.ProductAdaptor;
import com.example.buku_tetangga.Items.ProductsItem;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    // set card_of_home
    ViewPager viewPager;
    Adapter adapter2;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    //set card_of_home_horizontal
    private ProgressDialog progressDialog;
    private List<ProductsItem> productsItems = new ArrayList<ProductsItem>();
    private ProductAdaptor productAdaptor;
    private RecyclerView recyclerView;
    private String TAG = HomeFragment.class.getSimpleName();
    private LinearLayoutManager linearLayoutManager;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Set Slider
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        SliderView sliderView = (SliderView) rootView.findViewById(R.id.imageSlider);

        SliderAdapterExample adapter = new SliderAdapterExample(this.getActivity());

        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();

        // set card_of_home
//        models = new ArrayList<>();
//        models.add(new Model(R.drawable.cover_buku_1, "Brochure", "Ini adalah brosur"));
//        models.add(new Model(R.drawable.cover_buku_1, "Stiker", "Ini adalah stiker"));
//        models.add(new Model(R.drawable.cover_buku_1, "Poster", "Ini adalah poster"));
//        models.add(new Model(R.drawable.cover_buku_1, "Namecard", "Ini adalah kartu nama"));
//        models.add(new Model(R.drawable.cover_buku_1, "Namecard", "Ini adalah kartu nama"));
//        models.add(new Model(R.drawable.cover_buku_1, "Namecard", "Ini adalah kartu nama"));
//        models.add(new Model(R.drawable.cover_buku_1, "Namecard", "Ini adalah kartu nama"));
//
//        adapter2 = new Adapter(models, this.getActivity());
//
//        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager_rekomendasi);
//        viewPager.setAdapter(adapter2);
//        viewPager.setPadding(130, 0, 130, 0);

        //set card_of_home_horizontal
        recyclerView = rootView.findViewById(R.id.products);

        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        productAdaptor = new ProductAdaptor(getActivity(), productsItems);
        recyclerView.setAdapter(productAdaptor);
        getProductsFromServer();

        return rootView;
    }

    private void getProductsFromServer() {
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.SERVER_IP + Constants.SERVER_FOLDER + Constants.SERVER_FILE,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // parse data from server and asign to the list view
                            Log.e(TAG, response);
                            try
                            {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("productList");
                                if(jsonArray!=null)
                                {
                                    for(int i=0;i<jsonArray.length();i++)
                                    {
                                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                        String name  =jsonObject1.getString("productName");
                                        String imageurl = jsonObject1.getString("productImageUrl");
                                        String farmername = jsonObject1.getString("region");
                                        String stock = jsonObject1.getString("stock");

                                        ProductsItem productsItem = new ProductsItem();
                                        productsItem.setProductName(name);
                                        productsItem.setFarmerName(farmername);
                                        productsItem.setStock(stock);
                                        productsItem.setProductImageUrl(imageurl);

                                        productsItems.add(productsItem);
                                    }
                                }

                                productAdaptor.notifyDataSetChanged();
                            }catch (Exception ex)
                            {
                                Log.e(TAG, ""+ex.getLocalizedMessage());
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG,""+error.getLocalizedMessage());
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put("orderid", "1");
                    return map;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(stringRequest);

            stringRequest.setRetryPolicy(new RetryPolicy() {
                @Override
                public int getCurrentTimeout() {
                    return 0;
                }

                @Override
                public int getCurrentRetryCount() {
                    return 0;
                }

                @Override
                public void retry(VolleyError error) throws VolleyError {

                }
            });
        } catch (Exception ex) {
            Log.e(TAG, "" + ex.getLocalizedMessage());
        }
    }

}
