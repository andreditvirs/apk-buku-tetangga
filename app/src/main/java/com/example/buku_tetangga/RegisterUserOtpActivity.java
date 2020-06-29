package com.example.buku_tetangga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterUserOtpActivity extends AppCompatActivity {

    EditText kode1, kode2, kode3, kode4;
    Button btn_register_user_otp;
    private String otpStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_otp);

        kode1 = findViewById(R.id.kode1);
        kode2 = findViewById(R.id.kode2);
        kode3 = findViewById(R.id.kode3);
        kode4 = findViewById(R.id.kode4);
        otpStr = kode1.getText().toString() + kode2.getText().toString() + kode3.getText().toString() + kode4.getText().toString();
        btn_register_user_otp = findViewById(R.id.btn_register_user_otp);

        Bundle bundle = getIntent().getExtras();

        btn_register_user_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateVoiceCallOTP(bundle);
            }
        });
    }

    private void validateVoiceCallOTP(Bundle bundle){
        String accept = "application/json";
        String xapikey = "2tyFWgY7nA9KfKrl1brGPOhkzECC4HwF";
        String contentType = "application/json";
        VoicecallOtp2 voicecallOtp2 = new VoicecallOtp2(otpStr, 4);
        ApiVoicecallOtp apiVoicecallOtp = ApiClientVoicecallOtp.getRetrofitInstance().create(ApiVoicecallOtp.class);
        Call<VoicecallOtp2> call = apiVoicecallOtp.validateOTP(accept, xapikey, contentType, voicecallOtp2, bundle.getString("id"));
        call.enqueue(new Callback<VoicecallOtp2>() {
            @Override
            public void onResponse(Call<VoicecallOtp2> call, Response<VoicecallOtp2> response) {
                performRegistration(bundle);
            }

            @Override
            public void onFailure(Call<VoicecallOtp2> call, Throwable t) {
                System.out.println(t.getLocalizedMessage());
            }
        });
    }

    public void performRegistration(Bundle bundle){
        String nama = bundle.getString("nama");
        String username = bundle.getString("username");
        String userpass = bundle.getString("userpass");
        String email = bundle.getString("email");
        String notelp = bundle.getString("notelp");
        Call<ResponseBody> call = VerifyActivity.apiInterface.performRegistration(username, userpass, notelp, email, nama);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        System.out.println("Jadi JSON OBJECT");
                        if (!jsonObject.getBoolean("error")) {
                            System.out.println("INTENT");
                            toLogin();
                        } else {
                            System.out.println("GAGAL");
                            String error_message = jsonObject.getString("error_msg");
                            Toast.makeText(RegisterUserOtpActivity.this, error_message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.getMessage();
            }
        });
    }

    private void toLogin(){
        Toast.makeText(RegisterUserOtpActivity.this, "Anda Berhasil Registrasi", Toast.LENGTH_SHORT).show();
        this.finish();
        startActivity(new Intent(RegisterUserOtpActivity.this, Login.class));
    }
}