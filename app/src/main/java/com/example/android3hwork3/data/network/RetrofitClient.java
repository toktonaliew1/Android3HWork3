package com.example.android3hwork3.data.network;

import com.example.android3hwork3.data.network.apiservisec.CharacterApiService;
import com.example.android3hwork3.data.network.apiservisec.EpisodeApiService;
import com.example.android3hwork3.data.network.apiservisec.LocationApiService;

import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder().
            baseUrl("https://rickandmortyapi.com/").
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
