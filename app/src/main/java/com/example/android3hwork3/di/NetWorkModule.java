package com.example.android3hwork3.di;


import com.example.android3hwork3.data.network.RetrofitClient;
import com.example.android3hwork3.data.network.apiservisec.CharacterApiServices;
import com.example.android3hwork3.data.network.apiservisec.EpisodeApiServices;
import com.example.android3hwork3.data.network.apiservisec.LocationApiServices;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class NetWorkModule {

    @Singleton
    RetrofitClient retrofitClient = new RetrofitClient();

    @Singleton
    @Provides
    CharacterApiServices provideCharacterApiService() {
        return retrofitClient.provideCharacterApiService();
    }

    @Singleton
    @Provides
    EpisodeApiServices provideEpisodeApiService() {
        return retrofitClient.providerEpisodeApiService();
    }

    @Singleton
    @Provides
    LocationApiServices provideLocationApiService() {
        return retrofitClient.provideLocationApiService();
    }
}