package com.example.buku_tetangga;

import android.app.Application;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.buku_tetangga.adapters.book.BukuLainAdapter;
import com.example.buku_tetangga.Items.Buku;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

//import android.support.design.widget.CollapsingToolbarLayout;

public class BookButangActivity extends AppCompatActivity{

    private ViewPager viewPager;
    private ApiInterface apiInterface;
    private ImageView imageView;
    private TextView judul_buku, harga, penyedia;
    private List<Buku> buku_lain = new ArrayList<Buku>();
    private BukuLainAdapter bukuLainAdapter;
    private RecyclerView recyclerViewLain;
    private LinearLayoutManager linearLayoutManagerLain;
    private String TAG = BookButangActivity.class.getSimpleName();

    public BookButangActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_butang);

        Bundle bundle = getIntent().getExtras();
        String rakbuku_id = bundle.getString("rakbuku_id");
        reqDetailBuku(Request.Method.POST, Constants.FD_BUKU + Constants.GET_DETAIL_BUKU, rakbuku_id);
        imageView = findViewById(R.id.imgV_book_butang);
        judul_buku = findViewById(R.id.txtV_judul_buku_rakbuku);
        harga = findViewById(R.id.txtV_harga_rakbuku);
        penyedia = findViewById(R.id.txtV_penyedia_rakbuku);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayoutRakbuku);
        tabLayout.addTab(tabLayout.newTab().setText("DESKRIPSI"));
        tabLayout.addTab(tabLayout.newTab().setText("DETIL PRODUK"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager)findViewById(R.id.pagerRakbuku);
        final PagerAdapterRakbuku adapter = new PagerAdapterRakbuku(getSupportFragmentManager(),tabLayout.getTabCount(), rakbuku_id);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        linearLayoutManagerLain = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewLain = findViewById(R.id.book_butang_lain);
        recyclerViewLain.setLayoutManager(linearLayoutManagerLain);
        bukuLainAdapter = new BukuLainAdapter(this, buku_lain);
        recyclerViewLain.setAdapter(bukuLainAdapter);
        reqPostBooksFromServer(Request.Method.POST, Constants.FD_KATEGORI_BUKU + Constants.GET_KATEGORI_BUKU + Constants.PARAM_LAIN, rakbuku_id);

    }

    private void reqDetailBuku(int method, String uri, String rakbuku_id) {
//

// Make call
        final String URL = "http://api.dribbble.com/shots/everyone";
        // pass second argument as "null" for GET requests
        JsonObjectRequest req = new JsonObjectRequest(URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            VolleyLog.v("Response:%n %s", response.toString(4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

        // add the request object to the queue to be executed
        ApplicationController.getInstance().addToRequestQueue(req);
//

        try {
            final String URL = "URL";
            // Data post
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("rakbuku_id", rakbuku_id);
            JsonObjectRequest request_json = new JsonObjectRequest(uri, new JSONObject(params),
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    VolleyLog.e("Error: ", error.getMessage());
                }
            });
            // Add the request object to queue
            Application
        } catch (Exception ex) {
            Log.e(TAG, "" + ex.getLocalizedMessage());
        }
    }

    public void setRakbuku(RakBuku rakBuku){
        System.out.println("RESPONSE===="+rakBuku.toString());
        judul_buku.setText(rakBuku.getJudul_buku());
        penyedia.setText(rakBuku.getUsername());
        harga.setText("Rp. "+rakBuku.getHarga());
        Glide.with(this)
                .load(rakBuku.getFoto())
                .placeholder(R.drawable.cover_buku_1)
                .error(R.drawable.ic_photo_camera_unactive_24dp)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .override(400, 600)
                .fitCenter() // scale to fit entire image within ImageView
                .into(imageView);
    }

    private void reqPostBooksFromServer(int method, String uri, String rakbuku_id) {
        System.out.println("MELAKUKAN REQ BUKU");
        try {
            StringRequest stringRequest = new StringRequest(method, uri,
                    new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // parse data from server and asign to the list view
                            Log.e(TAG, response);
                            try
                            {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = new JSONArray();
                                jsonArray = jsonObject.getJSONArray("buku_lain");

                                if(jsonArray!=null)
                                {
                                    for(int i=0;i<jsonArray.length();i++)
                                    {
                                        JSONObject jsonObjects = jsonArray.getJSONObject(i);
                                        String rakbuku_id  = String.valueOf(jsonObjects.getInt("rakbuku_id"));
                                        String judul_buku = jsonObjects.getString("judul_buku");
                                        String pengarang = jsonObjects.getString("pengarang");
                                        String penerbit = jsonObjects.getString("penerbit");
                                        String harga = String.valueOf(jsonObjects.getInt("harga"));
                                        String jumlah_stock = String.valueOf(jsonObjects.getInt("jumlah_stock"));
                                        String foto = jsonObjects.getString("foto");

                                        Buku buku = new Buku();
                                        buku.setRakbuku_id(rakbuku_id);
                                        buku.setJudul_buku(judul_buku);
                                        buku.setPengarang(pengarang);
                                        buku.setPenerbit(penerbit);
                                        buku.setHarga(harga);
                                        buku.setStock(jumlah_stock);
                                        buku.setFoto(foto);

                                        buku_lain.add(buku);
                                    }
                                }
                                bukuLainAdapter.notifyDataSetChanged();
                            }catch (Exception ex)
                            {
                                Log.e(TAG, ""+ex.getLocalizedMessage());
                            }

                        }
                    }, new com.android.volley.Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG,""+error.getLocalizedMessage());
                }
            })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put("username", "BigBox");
                    map.put("rakbuku_id", rakbuku_id);
                    return map;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
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
