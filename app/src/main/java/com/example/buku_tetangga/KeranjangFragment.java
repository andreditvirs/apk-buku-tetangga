package com.example.buku_tetangga;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class KeranjangFragment extends Fragment {

    String number = "+62 82331744423";
    ImageButton btn_obrolan;

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
        return rootView;
    }

    public void setWhatsApp(String number){
        String url = "https://api.whatsapp.com/send?phone=" + number;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setPackage("com.whatsapp");
        i.setData(Uri.parse(url));
        startActivity(i);
    }

}
