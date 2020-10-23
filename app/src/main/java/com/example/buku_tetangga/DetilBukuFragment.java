package com.example.buku_tetangga;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.buku_tetangga.model.BookButangActivity.Buku;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetilBukuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetilBukuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String rakbuku_id;
    private ApiInterface apiInterface;
    private TextView isbn, pengarang, penerbit, bahasa, berat, panjang, lebar;
    public DetilBukuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetilBukuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetilBukuFragment newInstance(String param1, String param2) {
        DetilBukuFragment fragment = new DetilBukuFragment();
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
        // Inflate the layout for this fragment
        View rootView  = inflater.inflate(R.layout.fragment_detil_buku, container, false);
        isbn = rootView.findViewById(R.id.txtV_book_butang_isbn);
        pengarang = rootView.findViewById(R.id.txtV_book_butang_pengarang);
        penerbit = rootView.findViewById(R.id.txtV_book_butang_penerbit);
        bahasa = rootView.findViewById(R.id.txtV_book_butang_bahasa);
        berat = rootView.findViewById(R.id.txtV_book_butang_berat);
        panjang = rootView.findViewById(R.id.txtV_book_butang_panjang);
        lebar = rootView.findViewById(R.id.txtV_book_butang_lebar);
        return rootView;
    }

    public void setBuku(Buku buku){
        isbn.setText(buku.getIsbn());
        pengarang.setText(buku.getPengarang());
        penerbit.setText(buku.getPenerbit());
//        bahasa.setText(buku.getBahasa());
//        berat.setText(buku.getBerat());
//        panjang.setText(buku.getPanjang());
//        lebar.setText(buku.getLebar());
    }


    public void setRakbuku_id(String rakbuku_id) {
        this.rakbuku_id = rakbuku_id;
    }
}