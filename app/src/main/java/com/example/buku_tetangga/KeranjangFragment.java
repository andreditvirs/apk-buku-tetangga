package com.example.buku_tetangga;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.buku_tetangga.adapters.book.BukuRekomendasiAdapter;
import com.example.buku_tetangga.Items.Buku;

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

        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        bukuRekomendasiAdapter = new BukuRekomendasiAdapter(getActivity(), bukus);
        recyclerView.setAdapter(bukuRekomendasiAdapter);
        getProductsFromServer();

        return rootView;
    }

    public void setWhatsApp(String number){
        String url = "https://api.whatsapp.com/send?phone=" + number;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setPackage("com.whatsapp");
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    private void getProductsFromServer() {
        try {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.FD_KATEGORI_BUKU + Constants.GET_KATEGORI_BUKU,
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

                                        Buku buku = new Buku();
                                        buku.setJudul_buku(name);
                                        buku.setPenerbit(farmername);
                                        buku.setStock(stock);
                                        buku.setFoto(imageurl);

                                        bukus.add(buku);
                                    }
                                }

                                bukuRekomendasiAdapter.notifyDataSetChanged();
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
