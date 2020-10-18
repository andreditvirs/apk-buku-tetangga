package com.example.buku_tetangga;

import com.example.buku_tetangga.Items.Buku;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseBody> performRegistration(
            @Field("username") String username,
            @Field("password") String password,
            @Field("notelp") String notelp,
            @Field("email") String email,
            @Field("nama_lengkap") String nama_lengkap
    );

    @FormUrlEncoded
    @POST("login.php")
    Call<ResponseBody> performLogin(
            @Field("username") String username,
            @Field("password") String password
    );

    @GET("rakbuku/get_user_buku.php")
    Call<List<RakBuku>> getRakBuku(
            @Query("username") String username
    );

    @GET("crud/cek_user_valid.php")
    Call<StatusUser> cekStatusUser(
            @Query("username") String username
    );

    @Multipart
    @POST("crud/upload_foto_buku.php")
    Call<FotoBuku> uploadFotoBuku(@Part MultipartBody.Part file, @Part("file") RequestBody name);
}
