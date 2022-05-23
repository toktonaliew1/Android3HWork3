package com.example.android3hwork3.di;


import com.example.android3hwork3.data.network.RetrofitClient;
import com.example.android3hwork3.data.network.apiservisec.CharacterApiService;
import com.example.android3hwork3.data.network.apiservisec.EpisodeApiService;
import com.example.android3hwork3.data.network.apiservisec.LocationApiService;

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
    CharacterApiService provideCharacterApiService() {
         return retrofitClient.provideCharacterApiService();
     }


    @Singleton
    @Provides
    EpisodeApiService provideEpisodeApiService() {
        return retrofitClient.providerEpisodeApiService();
    }

    @Singleton
    @Provides
    LocationApiService provideLocationApiService() {
        return retrofitClient.provideLocationApiService();
    }
}


