package com.example.android3hwork3;

import android.app.Application;

import com.example.android3hwork3.data.db.daos.CharacterDao;
import com.example.android3hwork3.data.db.RoomClient;
import com.example.android3hwork3.data.db.daos.EpisodeDao;
import com.example.android3hwork3.data.db.daos.LocationDao;
import com.example.android3hwork3.data.network.RetrofitClient;
import com.example.android3hwork3.data.network.apiservisec.CharacterApiService;
import com.example.android3hwork3.data.network.apiservisec.EpisodeApiService;
import com.example.android3hwork3.data.network.apiservisec.LocationApiService;

public class App extends Application {

    public static CharacterApiService characterApiService;
    public static LocationApiService locationApiServices;
    public static EpisodeApiService episodeApiService;
    public RetrofitClient retrofitClient;
    private RoomClient roomClient;
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
        roomClient = new RoomClient();
        characterDao = roomClient.provideCharacterDao(roomClient.provideRoom(this));
        locationDao = roomClient.provideLocationDao(roomClient.provideRoom(this));
        episodeDao = roomClient.provideEpisodeDao(roomClient.provideRoom(this));

    }
}
