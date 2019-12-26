package com.example.buku_tetangga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class UnverifyForgetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unverify_forget_password);
    }

    public void toVerify(View view) {
        finish();
        startActivity(new Intent(UnverifyForgetPasswordActivity.this, VerifyActivity.class));
        Animatoo.animateSlideDown(this);
    }
}
