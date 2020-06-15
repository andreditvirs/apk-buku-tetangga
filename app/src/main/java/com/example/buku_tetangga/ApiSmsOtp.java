package com.example.buku_tetangga;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiSmsOtp {
    @PUT("otp/Hello")
    Call<SmsOtp> sendOTP(@Header("Accept") String accept,
                         @Header("x-api-key") String key,
                         @Header("Content-Type") String contentType,
                         @Body SmsOtp smsOtp);

    @POST("otp/Hello/verifications")
    Call<SmsOtp> getStatus(@Header("Accept") String accept,
                           @Header("x-api-key") String key,
                           @Header("Content-Type") String contentType,
                           @Body SmsOtp smsOtp);
}
