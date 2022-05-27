package com.example.android3hwork3;

import android.app.Application;

import com.example.android3hwork3.data.db.daos.CharacterDao;
import com.example.android3hwork3.data.db.RoomClient;
import com.example.android3hwork3.data.db.daos.EpisodeDao;
import com.example.android3hwork3.data.db.daos.LocationDao;
import com.example.android3hwork3.data.network.RetrofitClient;
import com.example.android3hwork3.data.network.apiservisec.CharacterApiServices;
import com.example.android3hwork3.data.network.apiservisec.EpisodeApiServices;
import com.example.android3hwork3.data.network.apiservisec.LocationApiServices;

public class App extends Application {

    public static CharacterApiServices characterApiService;
    public static LocationApiServices locationApiServices;
    public static EpisodeApiServices episodeApiService;
    public RetrofitClient retrofitClient;
    public static CharacterDao characterDao;
    public static EpisodeDao episodeDao;
    public static LocationDao locationDao;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofitClient = new RetrofitClient();
        characterApiService = retrofitClient.provideCharacterApiService();
        locationApiServices = retrofitClient.provideLocationApiService();
        episodeApiService = retrofitClient.providerEpisodeApiService();
        RoomClient roomClient = new RoomClient();
        characterDao = roomClient.provideCharacterDao(roomClient.provideRoom(this));
        locationDao = roomClient.provideLocationDao(roomClient.provideRoom(this));
        episodeDao = roomClient.provideEpisodeDao(roomClient.provideRoom(this));
    }
}