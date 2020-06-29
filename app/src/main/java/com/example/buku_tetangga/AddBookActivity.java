package com.example.buku_tetangga;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.buku_tetangga.activities.SearchAddBookActivity;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBookActivity extends AppCompatActivity {

    private TextView counterText, unggah_foto_nama;
    private Button minusBtn, plusBtn, btn_add_book;
    private int counter;
    private ImageButton imgBtn_add_book;
    String imagePath;
    private ApiInterface apiInterface;

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
        counterText = (TextView) findViewById(R.id.counterTxt_add_book_jumlah_stock);
        minusBtn = (Button) findViewById(R.id.minusBtn);
        minusBtn.setOnClickListener(clickListener);
        plusBtn = (Button) findViewById(R.id.plusBtn);
        plusBtn.setOnClickListener(clickListener);
        initCounter();


        btn_add_book = findViewById(R.id.btn_add_book);

        btn_add_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFotoBuku();
            }
        });

        unggah_foto_nama = findViewById(R.id.txtV_unggah_foto);
        imgBtn_add_book = findViewById(R.id.foto_add_book);
        imgBtn_add_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 0);
            }
        });
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

    public void uploadFotoBuku(){
        File file = new File(imagePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<FotoBuku> call = apiInterface.uploadFotoBuku(body, filename);
        call.enqueue(new Callback<FotoBuku>() {
            @Override
            public void onResponse(Call<FotoBuku> call, Response<FotoBuku> response) {
                if(response.isSuccessful()){
                    Toast.makeText(AddBookActivity.this, "Image upload successfully!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FotoBuku> call, Throwable t) {
                Toast.makeText(AddBookActivity.this, "ERROR! : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if (data == null){
                Toast.makeText(AddBookActivity.this, "Unable to choose image!", Toast.LENGTH_SHORT).show();
                return;
            }

            Uri imageUri = data.getData();
            imagePath = getRealPathFromUri(imageUri);
            File file = new File(imagePath);
            unggah_foto_nama.setText(file.getName());
        }
    }

    private String getRealPathFromUri(Uri uri){
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(AddBookActivity.this, uri, projection, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_idx);
        cursor.close();
        return result;
    }

    public void toAkun(View view) {
        finish();
        onBackPressed();
        Animatoo.animateSlideDown(this);
    }

    public void mainBtn(View view) {
        startActivity(new Intent(this, MapsActivity.class));
        Animatoo.animateSlideLeft(this);
    }

    public void toSearchAddDescBook(View view) {
        startActivity(new Intent(this, SearchAddBookActivity.class));
        Animatoo.animateSlideUp(this);
    }

    public void toAddDescBook(View view) {
        startActivity(new Intent(this, AddDescBookActivity.class));
        Animatoo.animateSlideLeft(this);
    }

    public void toAddCategory(View view) {
        startActivity(new Intent(this, AddCategoryActivity.class));
        Animatoo.animateSlideLeft(this);
    }
}
