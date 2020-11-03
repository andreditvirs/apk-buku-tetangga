package com.example.buku_tetangga;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.buku_tetangga.adapters.book.BukuRekomendasiAdapter;
import com.example.buku_tetangga.adapters.keranjang.RcvPenyediaAdapter;
import com.example.buku_tetangga.model.Buku;
import com.example.buku_tetangga.model.DetailBuku;
import com.example.buku_tetangga.model.keranjang.Penyedia;
import com.example.buku_tetangga.model.RakBuku;
import com.example.buku_tetangga.model.keranjang.BukuInPenyedia;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class KeranjangFragment extends Fragment {

    String number = "+62 82331744423";
    ImageButton btn_obrolan;

    //set card_of_keranjang horizontal
    private ProgressDialog progressDialog;
    private List<Buku> bukus = new ArrayList<Buku>();
    private List<RakBuku> rakbukus = new ArrayList<RakBuku>();
    private List<DetailBuku> detailBukus = new ArrayList<>();

    private BukuRekomendasiAdapter bukuRekomendasiAdapter;
    private RecyclerView recyclerView;
    private String TAG = HomeFragment.class.getSimpleName();
    private LinearLayoutManager linearLayoutManager;

    public KeranjangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_keranjang, container, false);
        btn_obrolan = (ImageButton) rootView.findViewById(R.id.btn_obrolan);
        btn_obrolan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    setWhatsApp(number);
                }catch (Exception e){
                    Toast.makeText(getActivity(), "Aplikasi Whatsapp belum terinstal", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        recyclerView = rootView.findViewById(R.id.home_buku_rekomendasi);

        // Global variabel
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String penyewa_id = prefs.getString("id", "loading...");

        reqDetailBuku(Request.Method.GET, Constants.FD_KERANJANG + Constants.GET_DETAIL_KERANJANG, penyewa_id);

        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        bukuRekomendasiAdapter = new BukuRekomendasiAdapter(getActivity(), bukus, rakbukus);
        recyclerView.setAdapter(bukuRekomendasiAdapter);

        RecyclerView rvItem = rootView.findViewById(R.id.rcv_keranjang_penyedia);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        RcvPenyediaAdapter itemAdapter = new RcvPenyediaAdapter(buildItemList(this.detailBukus));
        rvItem.setAdapter(itemAdapter);
        rvItem.setLayoutManager(layoutManager);

        return rootView;
    }

    private void reqDetailBuku(int method, String url, String penyewa_id) {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(method, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = new JSONArray();
                            jsonArray = jsonObject.getJSONArray("detail_buku");

                            List<DetailBuku> detailBukus = new ArrayList<DetailBuku>();
                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                // Ambil data KERANJANG
                                JSONObject jsonObjectKeranjang = jsonObject1.getJSONObject("keranjang");
                                // Ambil data PENYEDIA
                                JSONObject jsonObjectPenyedia = jsonObject1.getJSONObject("penyedia");
                                // Ambil data BUKU
                                JSONObject jsonObjectBuku = jsonObject1.getJSONObject("buku");
                                // Ambil data RAKBUKU
                                JSONObject jsonObjectRakBuku = jsonObject1.getJSONObject("rakbuku");

                                String keranjang_id = String.valueOf(jsonObjectKeranjang.getString("id"));

                                String nama_lengkap = String.valueOf(jsonObjectPenyedia.getString("nama_lengkap"));
                                String username = String.valueOf(jsonObjectPenyedia.getString("username"));
                                String foto_penyedia  = String.valueOf(jsonObjectPenyedia.getString("foto"));
                                Penyedia penyedia = new Penyedia(nama_lengkap, username, foto_penyedia);

                                String judul  = String.valueOf(jsonObjectBuku.getString("judul"));
                                Buku buku = new Buku(judul);

                                String harga  = String.valueOf(jsonObjectRakBuku.getString("harga"));;
                                String foto_rakbuku  = String.valueOf(jsonObjectRakBuku.getString("foto"));
                                RakBuku rakBuku = new RakBuku(harga, foto_rakbuku);
                                detailBukus.add(new DetailBuku(keranjang_id, penyedia, rakBuku, buku));
                            }
                            setDetailBukus(detailBukus);
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
                return params;
            }
        };
        queue.add(stringRequest);
    }

    private void setDetailBukus(List<DetailBuku> detailBukus){
        this.detailBukus = detailBukus;
    }

    public void setWhatsApp(String number){
        String url = "https://api.whatsapp.com/send?phone=" + number;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setPackage("com.whatsapp");
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private List<BukuInPenyedia> buildSubItemList(DetailBuku detailBuku) {
        List<BukuInPenyedia> subItemList = new ArrayList<>();
        for (int i=0; i<3; i++) {
            BukuInPenyedia subItem = new BukuInPenyedia(detailBuku.getKeranjang_id(), detailBuku.getBuku(), detailBuku.getRakBuku());
            subItemList.add(subItem);
        }
        return subItemList;
    }

    private List<Penyedia> buildItemList(List<DetailBuku> detailBukus) {
        List<Penyedia> itemList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Penyedia item = new Penyedia(detailBukus.get(i).getPenyedia().getNama_penyedia(), detailBukus.get(i).getPenyedia().getFoto(), buildSubItemList(detailBukus.get(i)));
            itemList.add(item);
        }
        return itemList;
    }

    }
