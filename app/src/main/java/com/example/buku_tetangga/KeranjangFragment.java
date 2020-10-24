package com.example.buku_tetangga;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.buku_tetangga.adapters.book.BukuRekomendasiAdapter;
import com.example.buku_tetangga.adapters.keranjang.RcvPenyediaAdapter;
import com.example.buku_tetangga.model.book_butang_activity.Buku;
import com.example.buku_tetangga.model.keranjang.Penyedia;
import com.example.buku_tetangga.model.book_butang_activity.RakBuku;
import com.example.buku_tetangga.model.keranjang.BukuInPenyedia;

import java.util.ArrayList;
import java.util.List;


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

        bukuRekomendasiAdapter = new BukuRekomendasiAdapter(getActivity(), bukus, rakbukus);
        recyclerView.setAdapter(bukuRekomendasiAdapter);

        RecyclerView rvItem = rootView.findViewById(R.id.rcv_keranjang_penyedia);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        RcvPenyediaAdapter itemAdapter = new RcvPenyediaAdapter(buildItemList());
        rvItem.setAdapter(itemAdapter);
        rvItem.setLayoutManager(layoutManager);

        return rootView;
    }

    private List<Penyedia> buildItemList() {
        List<Penyedia> itemList = new ArrayList<>();
        for (int i=0; i<10; i++) {
            Penyedia item = new Penyedia("Item "+i, buildSubItemList());
            itemList.add(item);
        }
        return itemList;
    }

    private List<BukuInPenyedia> buildSubItemList() {
        List<BukuInPenyedia> subItemList = new ArrayList<>();
        for (int i=0; i<3; i++) {
            BukuInPenyedia subItem = new BukuInPenyedia("Sub Item "+i, "Description "+i);
            subItemList.add(subItem);
        }
        return subItemList;
    }

    public void setWhatsApp(String number){
        String url = "https://api.whatsapp.com/send?phone=" + number;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setPackage("com.whatsapp");
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}
