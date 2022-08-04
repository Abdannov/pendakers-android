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

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIRequestData {
    @POST("api/login")
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
    @GET("selectsmd.php")
    Call<ResponseModelSamarinda> ardSelectData();

    @GET("selectbpp.php")
    Call<ResponseModelBalikpapan> ardSelectDatabpp();

    @GET("selectbtg.php")
    Call<ResponseModelBontang> ardSelectDatabtg();

    @GET("selectberau.php")
    Call<ResponseModelBerau> ardSelectDataBerau();

    @GET("selectkubar.php")
    Call<ResponseModelKubar> ardSelectDataKubar();

    @GET("selectkukar.php")
    Call<ResponseModelKukar> ardSelectDataKukar();

    @GET("selectkutim.php")
    Call<ResponseModelKutim> ardSelectDataKutim();

    @GET("selectmu.php")
    Call<ResponseModelMKU> ardSelectDataMU();

    @GET("selectpaser.php")
    Call<ResponseModelPaser> ardSelectDataPaser();

    @GET("selectppu.php")
    Call<ResponseModelPPU> ardSelectDataPPU();

    @Headers({
            "Content-Type: application/json"
    })
    @POST("insertsmd.php")
    Call<ResponseData> ardInsertsmd(@Body CreateDataSamarinda dataSamarinda);

    @FormUrlEncoded
    @POST("getsmd.php")
    Call<ResponseModelSamarinda> ardGetsmd(
            @Field("id") int id
    );

    @FormUrlEncoded
    @POST("updatesmd.php")
    Call<ResponseModelSamarinda> ardUpdatesmd(
            @Field("id") int id,
            @Field("tahapan") String tahapan
    );

}
