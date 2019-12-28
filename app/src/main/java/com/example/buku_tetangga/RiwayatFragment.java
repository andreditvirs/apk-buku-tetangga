package com.example.buku_tetangga;


import android.os.Bundle;

import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RiwayatFragment extends Fragment {

    //set rating_bar
    private TextView txtV_rating_value_book;
    private Button btn_submit_rating;
    private AppCompatRatingBar rating_bar_book;

    //set list_expand
    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    private List<String> expandableListNombres;
    private HashMap<String, ModelBukuKeranjang> listaContactos;
    private int lastExpandedPosition = -1;


    public RiwayatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_riwayat, container, false);
        //set rating_bar
        txtV_rating_value_book = rootView.findViewById(R.id.txtV_rating_value_book);
        btn_submit_rating = (Button) rootView.findViewById(R.id.btn_submit_rating);
        rating_bar_book = rootView.findViewById(R.id.rating_bar_book);

        rating_bar_book.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float value, boolean b) {
                txtV_rating_value_book.setText("Rating : "+ value);
            }
        });

        btn_submit_rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Rating " + rating_bar_book, Toast.LENGTH_SHORT).show();
            }
        });

        //set list_expand
        this.expandableListView = rootView.findViewById(R.id.expandableListView);
        this.listaContactos = getContactos();
        this.expandableListNombres = new ArrayList<>(listaContactos.keySet());
        this.expandableListAdapter = new CustomExpandableListAdapter(getActivity(),
                expandableListNombres, listaContactos);

        expandableListView.setAdapter(expandableListAdapter);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if(lastExpandedPosition != -1 && groupPosition != lastExpandedPosition){
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });

        return rootView;
    }

    private HashMap<String, ModelBukuKeranjang> getContactos() {
        HashMap<String, ModelBukuKeranjang> listaC = new HashMap<>();

        listaC.put("Juan", new ModelBukuKeranjang("111-111-111",
                "juan@correo.com", "Calle 1, 11 - 4D", R.drawable.img_11));
        listaC.put("Sara", new ModelBukuKeranjang("222-222-222",
                "sara@correo.com", "Calle 2, 1 - 1A", R.drawable.img_22));
        listaC.put("Raquel", new ModelBukuKeranjang("333-333-333",
                "raquel@correo.com", "Calle 3, 3 - 6B", R.drawable.img_33));
        listaC.put("Juan 2", new ModelBukuKeranjang("444-444-444",
                "juan@correo.com", "Calle 1, 11 - 4D", R.drawable.img_11));
        listaC.put("Sara 2", new ModelBukuKeranjang("555-555-555",
                "sara@correo.com", "Calle 2, 1 - 1A", R.drawable.img_22));
        listaC.put("Raquel 2", new ModelBukuKeranjang("666-666-666",
                "raquel@correo.com", "Calle 3, 3 - 6B", R.drawable.img_33));
        listaC.put("Juan 3", new ModelBukuKeranjang("777-777-777",
                "juan@correo.com", "Calle 1, 11 - 4D", R.drawable.img_11));
        listaC.put("Sara 3", new ModelBukuKeranjang("888-888-888",
                "sara@correo.com", "Calle 2, 1 - 1A", R.drawable.img_22));
        listaC.put("Raquel 3", new ModelBukuKeranjang("999-999-999",
                "raquel@correo.com", "Calle 3, 3 - 6B", R.drawable.img_33));


        return listaC;
    }

}
