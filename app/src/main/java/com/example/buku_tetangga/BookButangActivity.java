package com.example.buku_tetangga;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.buku_tetangga.adapters.book.BukuLainAdapter;
import com.example.buku_tetangga.model.book_butang_activity.Buku;
import com.example.buku_tetangga.model.book_butang_activity.Penyedia;
import com.example.buku_tetangga.model.book_butang_activity.RakBuku;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookButangActivity extends AppCompatActivity{

    private ViewPager viewPager;
    private ApiInterface apiInterface;
    private ImageView imageView;
    private TextView judul_buku, harga, nama_penyedia;
    private List<Buku> buku_lain = new ArrayList<Buku>();
    private List<RakBuku> rakbuku_lain = new ArrayList<RakBuku>();
    private BukuLainAdapter bukuLainAdapter;
    private RecyclerView recyclerViewLain;
    private LinearLayoutManager linearLayoutManagerLain;
    private String TAG = BookButangActivity.class.getSimpleName();

    private Button btn_sewa_buku;
    private Bundle bundle1 = new Bundle();

    public BookButangActivity(){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_butang);

        // Request dulu datanya
        Bundle bundle = getIntent().getExtras();
        String rakbuku_id = bundle.getString("rakbuku_id");
        reqDetailBuku(Constants.FD_BUKU + Constants.GET_DETAIL_BUKU, rakbuku_id);

        // Masukkan ke layout
        imageView = findViewById(R.id.imgV_book_butang);
        judul_buku = findViewById(R.id.txtV_judul_buku_rakbuku);
        harga = findViewById(R.id.txtV_harga_rakbuku);
        nama_penyedia = findViewById(R.id.txtV_penyedia_rakbuku);
        btn_sewa_buku = findViewById(R.id.btn_book_butang_sewa_buku);

        // Handler button ke keranjang
        btn_sewa_buku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Global variabel
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(BookButangActivity.this);
                String penyewa_id = prefs.getString("id", "loading...");

                // Masukkan bukunya dulu
                reqAddKeranjang(Constants.FD_KERANJANG+Constants.GET_DETAIL_KERANJANG, penyewa_id, rakbuku_id);

                // Pindah halaman dengan membawa data keranjang_id
                KeranjangFragment keranjangFragment = new KeranjangFragment();
                keranjangFragment.setArguments(bundle1);

                Intent i = new Intent(BookButangActivity.this, Navbar.class);
                i.putExtra("intentTo", "2");
                startActivity(i);
            }
        });

        // Set card Buku Lain
        linearLayoutManagerLain = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewLain = findViewById(R.id.book_butang_lain);
        recyclerViewLain.setLayoutManager(linearLayoutManagerLain);
        bukuLainAdapter = new BukuLainAdapter(this, buku_lain, rakbuku_lain);
        recyclerViewLain.setAdapter(bukuLainAdapter);
        reqGetBooksFromServer(Request.Method.GET, Constants.FD_KATEGORI_BUKU + Constants.GET_KATEGORI_BUKU + Constants.PARAM_LAIN, 'b');

    }

    private void reqAddKeranjang(String url, String penyewa_id, String rakbuku_id) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            JSONObject jsonObject = new JSONObject(response);
                            if(!jsonObject.getBoolean("error")){
                                bundle1.putString("keranjang_id", jsonObject.getString("penyewa_id"));
                            }
                        }catch (Exception ex)
                        {
                            Log.e(TAG, ""+ex.getLocalizedMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR");
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("penyewa_id", penyewa_id);
                params.put("rakbuku_id", rakbuku_id);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void reqDetailBuku(String url, String rakbuku_id) {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = new JSONArray();
                            jsonArray = jsonObject.getJSONArray("detail_buku");

                            JSONObject jsonObject1 =  jsonArray.getJSONObject(0);
                            // Ambil data PENYEDIA
                            JSONObject jsonObjectPenyedia = jsonObject1.getJSONObject("penyedia");
                            // Ambil data BUKU
                            JSONObject jsonObjectBuku = jsonObject1.getJSONObject("buku");
                            // Ambil data RAKBUKU
                            JSONObject jsonObjectRakBuku = jsonObject1.getJSONObject("rakbuku");
                            if(jsonObjectPenyedia!=null && jsonObjectBuku!=null && jsonObjectRakBuku!=null)
                            {
                                String username  = String.valueOf(jsonObjectPenyedia.getString("username"));
                                String notelp  = String.valueOf(jsonObjectPenyedia.getString("notelp"));
                                String alamat  = String.valueOf(jsonObjectPenyedia.getString("alamat"));
                                Penyedia penyedia = new Penyedia(username, notelp, alamat);
                                setPenyedia(penyedia);

                                String isbn  = String.valueOf(jsonObjectBuku.getString("isbn"));
                                String judul  = String.valueOf(jsonObjectBuku.getString("judul"));
                                String pengarang  = String.valueOf(jsonObjectBuku.getString("pengarang"));
                                String penerbit  = String.valueOf(jsonObjectBuku.getString("penerbit"));
                                String kategori  = String.valueOf(jsonObjectBuku.getString("kategori"));
                                String deskripsi  = String.valueOf(jsonObjectBuku.getString("deskripsi"));
                                Buku buku = new Buku(isbn, judul, pengarang, penerbit, kategori, deskripsi);
                                setBuku(buku);

                                String id  = String.valueOf(jsonObjectRakBuku.getString("id"));
                                String harga  = String.valueOf(jsonObjectRakBuku.getString("harga"));
                                String jumlah_stock  = String.valueOf(jsonObjectRakBuku.getString("jumlah_stock"));
                                String keterangan  = String.valueOf(jsonObjectRakBuku.getString("keterangan"));
                                String foto  = String.valueOf(jsonObjectRakBuku.getString("foto"));
                                RakBuku rakbuku = new RakBuku(id, harga, jumlah_stock, keterangan, foto);
                                setRakbuku(rakbuku);

                                TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayoutRakbuku);
                                setTabLayout(tabLayout, buku);
                            }
                        }catch (Exception ex)
                        {
                            Log.e(TAG, ""+ex.getLocalizedMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("ERROR");
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("rakbuku_id", rakbuku_id);
                return params;
            }
        };
        queue.add(stringRequest);
    }

    public void setPenyedia(Penyedia penyedia){
        nama_penyedia.setText(penyedia.getUsername());
//        notelp.setText(penyedia.getNotelp());
//        alamat.setText(penyedia.getAlamat());
    }

    public void setBuku(Buku buku){
        judul_buku.setText(buku.getJudul());
//        isbn.setText(buku.getIsbn());
//        pengarang.setText(buku.getPengarang());
//        penerbit.setText(buku.getPenerbit());
//        kategori.setText(buku.getKategori());
//        deskripsi.setText(buku.getDeskripsi());
    }

    public void setRakbuku(RakBuku rakBuku){
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

    private void reqGetBooksFromServer(int method, String uri, final char code) {
        try {
            StringRequest stringRequest = new StringRequest(method, uri,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // parse data from server and asign to the list view
                            Log.e(TAG, response);
                            try
                            {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = new JSONArray();
                                if(jsonArray!=null)
                                {
                                    for(int i=0;i<jsonArray.length();i++)
                                    {
                                        JSONObject jsonObjects = jsonArray.getJSONObject(i);
                                        String rakbuku_id  = String.valueOf(jsonObjects.getInt("rakbuku_id"));
                                        String judul = jsonObjects.getString("judul");
                                        String pengarang = jsonObjects.getString("pengarang");
                                        String penerbit = jsonObjects.getString("penerbit");
                                        String harga = String.valueOf(jsonObjects.getInt("harga"));
                                        String jumlah_stock = String.valueOf(jsonObjects.getInt("jumlah_stock"));
                                        String foto = jsonObjects.getString("foto");

                                        Buku buku = new Buku(judul, pengarang, penerbit);
                                        RakBuku rakbuku = new RakBuku(rakbuku_id, harga, jumlah_stock, foto);

                                        buku_lain.add(buku);
                                        rakbuku_lain.add(rakbuku);
                                    }
                                }
                                bukuLainAdapter.notifyDataSetChanged();
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
            }) ;

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

    public void setTabLayout(TabLayout tabLayout, Buku buku){
        tabLayout.addTab(tabLayout.newTab().setText("DESKRIPSI"));
        tabLayout.addTab(tabLayout.newTab().setText("DETIL PRODUK"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager)findViewById(R.id.pagerRakbuku);
        final PagerAdapterRakbuku adapter = new PagerAdapterRakbuku(getSupportFragmentManager(),tabLayout.getTabCount(), buku);
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
    }
}
