package com.example.buku_tetangga;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.textfield.TextInputEditText;
import com.example.buku_tetangga.AddBookActivity;

public class AddCategoryActivity extends AppCompatActivity {

    RadioGroup rg_kategori;
    RadioButton rb_kategori;
    TextInputEditText txtI_kategori;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        rg_kategori = findViewById(R.id.rg_kategori);
        Button btSubmit = findViewById(R.id.bt_submit);
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = rg_kategori.getCheckedRadioButtonId();
                rb_kategori = findViewById(id);
                displayToast(rb_kategori.getText());
//                txtI_kategori = findViewById(R.id.textI_kategori);
//                txtI_kategori.setText(rb_kategori.getText());
                finish();
                onBackPressed();
            }
        });
    }

    public void toAddBook(View view) {
        finish();
        onBackPressed();
        Animatoo.animateSlideRight(this);
    }

    public void displayToast(CharSequence text){
        Toast.makeText(AddCategoryActivity.this, "Katgeori : "+ text + "terpilih", Toast.LENGTH_SHORT).show();
    }

    public void checkButton(View view) {
        int radioId = rg_kategori.getCheckedRadioButtonId();
        rb_kategori = findViewById(radioId);
        Toast.makeText(AddCategoryActivity.this, rb_kategori.getText(), Toast.LENGTH_SHORT).show();
    }
}