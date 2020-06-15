package com.example.buku_tetangga;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientSmsOtp {
    public static Retrofit retrofit;
    public static final String SMS_OTP = "https://api.thebigbox.id/sms-otp/1.0.0/";

    public static Retrofit getRetrofitInstance(){
        if (retrofit == null){
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(SMS_OTP)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}