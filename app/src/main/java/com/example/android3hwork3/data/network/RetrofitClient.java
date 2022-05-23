package com.example.android3hwork3.data.network;

import com.example.android3hwork3.data.network.apiservisec.CharacterApiService;
import com.example.android3hwork3.data.network.apiservisec.EpisodeApiService;
import com.example.android3hwork3.data.network.apiservisec.LocationApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {


    private OkHttpClient okHttpClient = new OkHttpClient()
            .newBuilder()
            .addInterceptor(loggingInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30,TimeUnit.SECONDS)
            .writeTimeout(30,TimeUnit.SECONDS)
            .build();


    private HttpLoggingInterceptor loggingInterceptor(){
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder().
            baseUrl("https://rickandmortyapi.com/")
            .client(okHttpClient)
            .
            addConverterFactory(GsonConverterFactory.create()).build();

    public CharacterApiService provideCharacterApiService() {
        return retrofit.create(CharacterApiService.class);
    }

    public LocationApiService provideLocationApiService() {
        return retrofit.create(LocationApiService.class);
    }

    public EpisodeApiService providerEpisodeApiService() {
        return retrofit.create(EpisodeApiService.class);
    }


}
