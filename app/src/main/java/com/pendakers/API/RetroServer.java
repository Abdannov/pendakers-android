package com.pendakers.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetroServer {
    public static final String baseURLLocal = "http://192.168.0.110:8000/api/";
    public static final String baseURL = "http://pendatakers.com/api/";
    public static Retrofit retro;

    public static Gson gson = new GsonBuilder()
    .setDateFormat("")
        .setLenient()
        .create();

    public static Retrofit konekRetrofit(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);

        if(retro == null){
            retro = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient.build())
                    .build();
        }

        return retro;
    }
}
