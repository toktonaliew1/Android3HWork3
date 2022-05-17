package com.example.android3hwork3.ui;

import android.app.Application;

import com.example.android3hwork3.data.network.RetrofitClient;
import com.example.android3hwork3.data.network.apiservisec.CharacterApiService;
import com.example.android3hwork3.data.network.apiservisec.EpisodeApiService;
import com.example.android3hwork3.data.network.apiservisec.LocationApiService;

public class App extends Application {
    public static CharacterApiService characterApiService;
    public static LocationApiService locationApiServices;
    public static EpisodeApiService episodeApiService;
    public RetrofitClient retrofitClient;

    @Override
    public void onCreate(){
        super.onCreate();
        retrofitClient = new RetrofitClient();
        characterApiService = retrofitClient.provideCharacterApiService();
        locationApiServices = retrofitClient.provideLocationApiService();
        episodeApiService = retrofitClient.providerEpisodeApiService();
    }
}
