package com.example.buku_tetangga;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientSmsNotif {
    public static Retrofit retrofit;
    public static final String SMS_NOTIF = "https://api.thebigbox.id/sms-notification/1.0.0/";

    public static Retrofit getRetrofitInstance(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(SMS_NOTIF)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}