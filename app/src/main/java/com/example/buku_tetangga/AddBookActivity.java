package com.example.buku_tetangga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.buku_tetangga.activities.SearchAddBookActivity;

public class AddBookActivity extends AppCompatActivity {

    private TextView counterText;
    private Button minusBtn;
    private Button plusBtn;
    private int counter;

    private View.OnClickListener clickListener = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.minusBtn : minusCounter(); break;
                case R.id.plusBtn : plusCounter(); break;
                default: initCounter(); break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        //set counter
        counterText = (TextView) findViewById(R.id.counterTxt_jumlah);
        minusBtn = (Button) findViewById(R.id.minusBtn);
        minusBtn.setOnClickListener(clickListener);
        plusBtn = (Button) findViewById(R.id.plusBtn);
        plusBtn.setOnClickListener(clickListener);


        initCounter();
    }

    private void initCounter(){
        counter = 0;
        counterText.setText(0 + "");
    }

    private void plusCounter(){
        if (counter >= 100 ){
            counter = 0;
            counterText.setText(counter + "");
        }else{
            counter++;
            counterText.setText(counter + "");
        }

    }

    private void minusCounter(){
        if (counter <= 0){
            counter = 0;
            counterText.setText(counter + "");
        }else {
            counter--;
            counterText.setText(counter + "");
        }
    }

    public void toAkun(View view) {
        onBackPressed();
        Animatoo.animateSlideLeft(this);
    }

    public void mainBtn(View view) {
        startActivity(new Intent(this, VerifyActivity.class));
        Animatoo.animateSlideLeft(this);
    }

    public void toSearchAddDescBook(View view) {
        startActivity(new Intent(this, SearchAddBookActivity.class));
        Animatoo.animateSlideUp(this);
    }
}
