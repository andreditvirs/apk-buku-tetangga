package com.example.buku_tetangga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.buku_tetangga.activities.SearchAddBookActivity;

public class AddDescBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_desc_book);
    }

    public void toAddBook(View view) {
        finish();
        onBackPressed();
        Animatoo.animateSlideRight(this);
    }

    public void mainBtn(View view) {
        finish();
        onBackPressed();
        Animatoo.animateSlideRight(this);
    }
}
