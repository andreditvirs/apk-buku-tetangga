package com.example.buku_tetangga;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiSmsNotif {
    @GET("messages/9513603993/status")
    Call<SmsNotif> getStatus(@Header("Accept") String accept,
                             @Header("x-api-key") String key);

    @FormUrlEncoded
    @POST("messages")
    Call<SmsNotif> sendSMS(@Header("Accept") String accept,
                           @Header("x-api-key") String key,
                           @Header("Content-Type") String contentType,
                           @Field("msisdn") String msisdn,
                           @Field("content") String content);
}
