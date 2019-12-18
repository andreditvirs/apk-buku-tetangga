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
        startActivity(new Intent(this, AddBookActivity.class));
        Animatoo.animateSlideLeft(this);
    }

    public void mainBtn(View view) {
        startActivity(new Intent(this, VerifyActivity.class));
        Animatoo.animateSlideLeft(this);
    }
}
