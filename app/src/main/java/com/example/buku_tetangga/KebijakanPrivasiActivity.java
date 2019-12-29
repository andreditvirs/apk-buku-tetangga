package com.example.buku_tetangga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class KebijakanPrivasiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kebijakan_privasi);
    }

    public void toBack(View view) {
        onBackPressed();
        finish();
        Animatoo.animateSlideDown(this);
    }

    public void toJaminan(View view) {
        onBackPressed();
        finish();
        Animatoo.animateSlideDown(this);
    }
}
