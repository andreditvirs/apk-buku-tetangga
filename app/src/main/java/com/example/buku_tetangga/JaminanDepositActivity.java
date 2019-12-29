package com.example.buku_tetangga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class JaminanDepositActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jaminan_deposit);
    }

    public void toJaminan(View view) {
        startActivity(new Intent(this, JaminanDepositActivity.class));
        finish();
        Animatoo.animateSlideRight(this);
    }
}
