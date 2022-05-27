package com.example.android3hwork3.data.network;

import com.example.android3hwork3.data.network.apiservisec.CharacterApiServices;
import com.example.android3hwork3.data.network.apiservisec.EpisodeApiServices;
import com.example.android3hwork3.data.network.apiservisec.LocationApiServices;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private final OkHttpClient okHttpClient = new OkHttpClient()
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
            .addConverterFactory(GsonConverterFactory.create()).build();

    public CharacterApiServices provideCharacterApiService() {
        return retrofit.create(CharacterApiServices.class);
    }

    public LocationApiServices provideLocationApiService() {
        return retrofit.create(LocationApiServices.class);
    }

    public EpisodeApiServices providerEpisodeApiService() {
        return retrofit.create(EpisodeApiServices.class);
    }
}