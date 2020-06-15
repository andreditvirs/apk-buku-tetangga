package com.example.buku_tetangga;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiVoicecallOtp {
    @PUT("otp/wew")
    Call<VoicecallOtp> sendOTP(@Header("Accept") String accept,
                               @Header("x-api-key") String key,
                               @Header("Content-Type") String contentType,
                               @Body VoicecallOtp voicecallOtp);

    @POST("otp/adit/verifications")
    Call<VoicecallOtp> validateOTP(@Header("Accept") String accept,
                                   @Header("x-api-key") String key,
                                   @Header("Content-Type") String contentType,
                                   @Body VoicecallOtp voicecallOtp);
}
