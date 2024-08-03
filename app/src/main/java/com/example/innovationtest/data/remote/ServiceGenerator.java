package com.example.innovationtest.data.remote;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ServiceGenerator {
    private static  String BASE_URL="https://dummy.restapiexample.com/api/v1/" ;

    private static APIService servicio;

    public static APIService getApiService() {
        servicio=null;

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        OkHttpClient.Builder okbuilder=new OkHttpClient.Builder();
        OkHttpClient httpClient;

             httpClient = okbuilder
                    .readTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .build();


        if (servicio == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient)
                    .build();
            servicio = retrofit.create(APIService.class);
        }

        return servicio;
    }

}
