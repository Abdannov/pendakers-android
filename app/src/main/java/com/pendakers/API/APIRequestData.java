package com.pendakers.API;

import com.pendakers.Model.CreateDataSamarinda;
import com.pendakers.Model.DataSamarinda;
import com.pendakers.Model.ResponseData;
import com.pendakers.Model.ResponseLogin;
import com.pendakers.Model.ResponseModelBalikpapan;
import com.pendakers.Model.ResponseModelBerau;
import com.pendakers.Model.ResponseModelBontang;
import com.pendakers.Model.ResponseModelKubar;
import com.pendakers.Model.ResponseModelKukar;
import com.pendakers.Model.ResponseModelKutim;
import com.pendakers.Model.ResponseModelMKU;
import com.pendakers.Model.ResponseModelPPU;
import com.pendakers.Model.ResponseModelPaser;
import com.pendakers.Model.ResponseModelSamarinda;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIRequestData {
    @POST("login")
    @FormUrlEncoded
    @Headers({
            "Accept: Application/json",
            "Content-Type: application/x-www-form-urlencoded"
    })
    Call<ResponseLogin> getLoginApi(@Field("email") String email, @Field("password") String password);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("dataPendaker")
    Call<ResponseModelSamarinda> ardSelectData(@Header ("Authorization") String token,
                                               @Query("kabkota") String kabkota);

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("searchPendaker")
    Call<ResponseModelSamarinda> ardSearchData(@Header ("Authorization") String token,
                                               @Query("kabkota") String kabkota,
                                               @Query("keyword") String keyword);

    @Multipart
    @Headers({
            "Accept: application/json",
    })
    @POST("dataPendaker")
    Call<ResponseData> ardInsertsmd(@Header ("Authorization") String token,
                                    @Part("kabkota") RequestBody kabkota,
                                    @Part("tentang") RequestBody tentang,
                                    @Part("mou") RequestBody mou,
                                    @Part("pks") RequestBody pks,
                                    @Part("tanggal") RequestBody tanggal,
                                    @Part("jangka_waktu") RequestBody jangkaWaktu,
                                    @Part("unitkerja") RequestBody unitkerja,
                                    @Part("mitrakerja") RequestBody mitrakerja,
                                    @Part("tahapan") RequestBody tahapan,
                                    @Part("tahun") RequestBody tahun,
                                    @Part MultipartBody.Part file);

    @Multipart
    @Headers({
            "Accept: application/json",
    })
    @POST("updatePendaker/{id}")
    Call<ResponseData> ardUpdatesmd(@Path("id") Long id,
                                    @Header ("Authorization") String token,
                                    @Part("kabkota") RequestBody kabkota,
                                    @Part("tentang") RequestBody tentang,
                                    @Part("mou") RequestBody mou,
                                    @Part("pks") RequestBody pks,
                                    @Part("tanggal") RequestBody tanggal,
                                    @Part("jangka_waktu") RequestBody jangkaWaktu,
                                    @Part("unitkerja") RequestBody unitkerja,
                                    @Part("mitrakerja") RequestBody mitrakerja,
                                    @Part("tahapan") RequestBody tahapan,
                                    @Part("tahun") RequestBody tahun,
                                    @Part MultipartBody.Part file);


}
