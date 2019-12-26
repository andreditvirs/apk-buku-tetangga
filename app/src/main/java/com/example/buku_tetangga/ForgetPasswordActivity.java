package com.example.buku_tetangga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class ForgetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

    }

    public void toVerify(View view) {
        onBackPressed();
        Animatoo.animateSlideRight(this);
    }

    public void toUnverifyForgetPassword(View view) {
        finish();
        startActivity(new Intent(ForgetPasswordActivity.this, UnverifyForgetPasswordActivity.class));
        Animatoo.animateSlideUp(this);
    }
}
