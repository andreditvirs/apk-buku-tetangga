package com.example.buku_tetangga;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SmsOtpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_otp);
//        SmsOtp smsOtp = new SmsOtp(2, "089687542332", 60, "OTP butang adalah {{otp}}", 4);
//        SmsOtp smsOtp = new SmsOtp(2, "1111", 60, 4);
//        SmsNotif smsNotif = new SmsNotif("089687542332", "Hello Lagi Andre");
        String accept = "application/json";
//        String accept = "application/x-www-form-urlencoded";
        String xapikey = "2tyFWgY7nA9KfKrl1brGPOhkzECC4HwF";
        String contentType = "application/json";
//        String contentType = "application/x-www-form-urlencoded";
//        ApiSmsOtp apiSmsOtp = ApiClientSmsOtp.getRetrofitInstance().create(ApiSmsOtp.class);
//        Call<SmsOtp> call = apiSmsOtp.getStatus(accept, xapikey, contentType, smsOtp);
//        Call<SmsOtp> call = apiSmsOtp.sendOTP(accept, xapikey, contentType, smsOtp);
//        call.enqueue(new Callback<SmsOtp>() {
//            @Override
//            public void onResponse(Call<SmsOtp> call, Response<SmsOtp> response) {
//                System.out.println("================"+response);
//            }
//
//            @Override
//            public void onFailure(Call<SmsOtp> call, Throwable t) {
//                System.out.println("++++++++++++"+t.getLocalizedMessage());
//            }
//        });
//        ApiSmsNotif apiSmsNotif = ApiClientSmsNotif.getRetrofitInstance().create(ApiSmsNotif.class);
//        Call<SmsNotif> call = apiSmsNotif.sendSMS(accept, xapikey, contentType, "089687542332", "Hello Lagi Andre");
//        Call<SmsNotif> call = apiSmsNotif.getStatus(accept, xapikey);
//        call.enqueue(new Callback<SmsNotif>() {
//            @Override
//            public void onResponse(Call<SmsNotif> call, Response<SmsNotif> response) {
//                System.out.println("================"+response);
//            }
//
//            @Override
//            public void onFailure(Call<SmsNotif> call, Throwable t) {
//                System.out.println("++++++++++++"+t.getLocalizedMessage());
//            }
//        });
        VoicecallOtp voicecallOtp = new VoicecallOtp("089687542332", 4);
//        VoicecallOtp voicecallOtp = new VoicecallOtp("0231", 4);
        ApiVoicecallOtp apiVoicecallOtp = ApiClientVoicecallOtp.getRetrofitInstance().create(ApiVoicecallOtp.class);
        Call<VoicecallOtp> call = apiVoicecallOtp.sendOTP(accept, xapikey, contentType, voicecallOtp);
//        Call<VoicecallOtp> call = apiVoicecallOtp.validateOTP(accept, xapikey, contentType, voicecallOtp);
        call.enqueue(new Callback<VoicecallOtp>() {
            @Override
            public void onResponse(Call<VoicecallOtp> call, Response<VoicecallOtp> response) {
                System.out.println("================"+response);
            }

            @Override
            public void onFailure(Call<VoicecallOtp> call, Throwable t) {
                System.out.println("++++++++++++"+t.getLocalizedMessage());
            }
        });

    }
}
