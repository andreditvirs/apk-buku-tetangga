package com.example.buku_tetangga;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiVoicecallOtp {
    @PUT("otp/{id}")
    Call<VoicecallOtp> sendOTP(@Header("Accept") String accept,
                               @Header("x-api-key") String key,
                               @Header("Content-Type") String contentType,
                               @Body VoicecallOtp voicecallOtp,
                               @Path("id") String id);

    @POST("otp/{id}/verifications")
    Call<VoicecallOtp2> validateOTP(@Header("Accept") String accept,
                                   @Header("x-api-key") String key,
                                   @Header("Content-Type") String contentType,
                                   @Body VoicecallOtp2 voicecallOtp2,
                                   @Path("id") String id);
}
