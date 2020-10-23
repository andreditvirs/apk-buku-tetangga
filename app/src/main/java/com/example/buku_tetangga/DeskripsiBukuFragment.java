package com.example.buku_tetangga;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.buku_tetangga.model.Book;
import com.example.buku_tetangga.model.BookButangActivity.Buku;
import com.example.buku_tetangga.model.BookButangActivity.Penyedia;
import com.example.buku_tetangga.model.BookButangActivity.RakBuku;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeskripsiBukuFragment extends Fragment{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView deskripsi;
    private Bundle bundle;
    private ApiInterface apiInterface;
    public DeskripsiBukuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_deskripsi_buku, container, false);

        deskripsi = rootView.findViewById(R.id.txtV_deskripsi_rakbuku);
        bundle = this.getArguments();
        String deskripsi_temp = bundle.getString("deskripsi");
        deskripsi.setText(deskripsi_temp);
        return rootView;
    }
}