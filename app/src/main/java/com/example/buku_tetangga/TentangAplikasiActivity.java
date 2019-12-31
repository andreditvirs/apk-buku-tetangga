package com.example.buku_tetangga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class TentangAplikasiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang_aplikasi);
    }

    public void toVerify(View view) {
        onBackPressed();
        finish();
        Animatoo.animateSlideUp(this);
    }
}
