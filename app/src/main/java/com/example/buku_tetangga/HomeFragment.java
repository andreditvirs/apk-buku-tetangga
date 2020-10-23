package com.example.buku_tetangga;


import android.animation.ArgbEvaluator;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.buku_tetangga.adapters.book.BukuRekomendasiAdapter;
import com.example.buku_tetangga.adapters.book.BukuTerbaruAdapter;
import com.example.buku_tetangga.adapters.book.BukuTerpopulerAdapter;
import com.example.buku_tetangga.model.BookButangActivity.Buku;
import com.example.buku_tetangga.model.BookButangActivity.Penyedia;
import com.example.buku_tetangga.model.BookButangActivity.RakBuku;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
    private List<Buku> buku_rekomendasi = new ArrayList<Buku>();
    private List<Buku> buku_terpopuler = new ArrayList<Buku>();
    private List<Buku> buku_terbaru = new ArrayList<Buku>();
    private List<RakBuku> rakbuku_rekomendasi = new ArrayList<RakBuku>();
    private List<RakBuku> rakbuku_terpopuler = new ArrayList<RakBuku>();
    private List<RakBuku> rakbuku_terbaru = new ArrayList<RakBuku>();

    private BukuRekomendasiAdapter bukuRekomendasiAdapter;
    private BukuTerpopulerAdapter bukuTerpopulerAdapter;
    private BukuTerbaruAdapter bukuTerbaruAdapter;
    private RecyclerView recyclerViewRekomendasi, recyclerViewTerpopuler, recyclerViewTerbaru;
    private String TAG = HomeFragment.class.getSimpleName();
    private LinearLayoutManager linearLayoutManagerRekomendasi, linearLayoutManagerTerpopuler, linearLayoutManagerTerbaru;

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

        // Global variabel
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String nama = prefs.getString("nama", "loading...");
        String username = prefs.getString("username", "loading...");

        // Set card Buku Rekomendasi
        linearLayoutManagerRekomendasi = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewRekomendasi = rootView.findViewById(R.id.home_buku_rekomendasi);
        recyclerViewRekomendasi.setLayoutManager(linearLayoutManagerRekomendasi);
        bukuRekomendasiAdapter = new BukuRekomendasiAdapter(getActivity(), buku_rekomendasi, rakbuku_rekomendasi);
        recyclerViewRekomendasi.setAdapter(bukuRekomendasiAdapter);
        reqGetBooksFromServer(Request.Method.GET, Constants.FD_KATEGORI_BUKU + Constants.GET_KATEGORI_BUKU + Constants.PARAM_REKOMENDASI + username, 'r');

        // Set card Buku Terpopuler
        linearLayoutManagerTerpopuler = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewTerpopuler = rootView.findViewById(R.id.home_buku_terpopuler);
        recyclerViewTerpopuler.setLayoutManager(linearLayoutManagerTerpopuler);
        bukuTerpopulerAdapter = new BukuTerpopulerAdapter(getActivity(), buku_terpopuler, rakbuku_terpopuler);
        recyclerViewTerpopuler.setAdapter(bukuTerpopulerAdapter);
        reqGetBooksFromServer(Request.Method.GET, Constants.FD_KATEGORI_BUKU + Constants.GET_KATEGORI_BUKU + Constants.PARAM_TERPOPULER, 'p');

        // Set card Buku Terbaru
        linearLayoutManagerTerbaru = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewTerbaru = rootView.findViewById(R.id.home_buku_terbaru);
        recyclerViewTerbaru.setLayoutManager(linearLayoutManagerTerbaru);
        bukuTerbaruAdapter = new BukuTerbaruAdapter(getActivity(), buku_terbaru, rakbuku_terbaru);
        recyclerViewTerbaru.setAdapter(bukuTerbaruAdapter);
        reqGetBooksFromServer(Request.Method.GET, Constants.FD_KATEGORI_BUKU + Constants.GET_KATEGORI_BUKU + Constants.PARAM_TERBARU, 'b');

        return rootView;
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
                                if(code == 'r'){
                                    jsonArray = jsonObject.getJSONArray("buku_rekomendasi");
                                }else if(code == 'p'){
                                    jsonArray = jsonObject.getJSONArray("buku_terpopuler");
                                }else if(code == 'b'){
                                    jsonArray = jsonObject.getJSONArray("buku_terbaru");
                                }
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

                                        if(code == 'r'){
                                            buku_rekomendasi.add(buku);
                                            rakbuku_rekomendasi.add(rakbuku);
                                        }else if(code == 'p'){
                                            buku_terpopuler.add(buku);
                                            rakbuku_terpopuler.add(rakbuku);
                                        }else if(code == 'b'){
                                            buku_terbaru.add(buku);
                                            rakbuku_terbaru.add(rakbuku);
                                        }
                                    }
                                }
                                if(code == 'r'){
                                    bukuRekomendasiAdapter.notifyDataSetChanged();;
                                }else if(code == 'p') {
                                    bukuTerpopulerAdapter.notifyDataSetChanged();
                                }else if(code == 'b'){
                                    bukuTerbaruAdapter.notifyDataSetChanged();
                                }
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
