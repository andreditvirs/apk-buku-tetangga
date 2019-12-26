package com.example.buku_tetangga;


import android.os.Bundle;

import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class RiwayatFragment extends Fragment {

    private TextView txtV_rating_value_book;
    private Button btn_submit_rating;
    private AppCompatRatingBar rating_bar_book;

    public RiwayatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_riwayat, container, false);
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
        return rootView;
    }

}
