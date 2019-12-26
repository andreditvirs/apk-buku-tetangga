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

public class AddCategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        final RadioGroup rg_kategori = (RadioGroup) findViewById(R.id.rg_kategori);
        Button btSubmit = (Button) findViewById(R.id.bt_submit);
        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = rg_kategori.getCheckedRadioButtonId();
                switch (id){
                    case R.id.rb_novel :
                        Toast.makeText(AddCategoryActivity.this,"Clicked "+((RadioButton)findViewById(id)).getText(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_komik :
                        Toast.makeText(AddCategoryActivity.this,"Clicked "+((RadioButton)findViewById(id)).getText(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_biografi :
                        Toast.makeText(AddCategoryActivity.this,"Clicked "+((RadioButton)findViewById(id)).getText(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_penelitian :
                        Toast.makeText(AddCategoryActivity.this,"Clicked "+((RadioButton)findViewById(id)).getText(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_ilmu_sains :
                        Toast.makeText(AddCategoryActivity.this,"Clicked "+((RadioButton)findViewById(id)).getText(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_ilmu_agama :
                        Toast.makeText(AddCategoryActivity.this,"Clicked "+((RadioButton)findViewById(id)).getText(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_ilmu_sastra :
                        Toast.makeText(AddCategoryActivity.this,"Clicked "+((RadioButton)findViewById(id)).getText(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_teknologi :
                        Toast.makeText(AddCategoryActivity.this,"Clicked "+((RadioButton)findViewById(id)).getText(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_hukum :
                        Toast.makeText(AddCategoryActivity.this,"Clicked "+((RadioButton)findViewById(id)).getText(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_ekonomi :
                        Toast.makeText(AddCategoryActivity.this,"Clicked "+((RadioButton)findViewById(id)).getText(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_psikologi :
                        Toast.makeText(AddCategoryActivity.this,"Clicked "+((RadioButton)findViewById(id)).getText(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_seni :
                        Toast.makeText(AddCategoryActivity.this,"Clicked "+((RadioButton)findViewById(id)).getText(), Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    public void toAddBook(View view) {
        finish();
        onBackPressed();
        Animatoo.animateSlideRight(this);
    }
}