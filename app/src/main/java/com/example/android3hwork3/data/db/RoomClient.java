package com.example.android3hwork3.data.db;

import android.content.Context;

import androidx.room.Room;

import com.example.android3hwork3.data.db.daos.CharacterDao;
import com.example.android3hwork3.data.db.daos.EpisodeDao;
import com.example.android3hwork3.data.db.daos.LocationDao;

public class RoomClient {

    public AppDataBase provideRoom(Context context){
        return Room
                .databaseBuilder(context, AppDataBase.class, "app_database")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }
    public CharacterDao provideCharacterDao(AppDataBase appDataBase){
        return appDataBase.characterDao();
    }

    public LocationDao provideLocationDao(AppDataBase appDataBase){
        return appDataBase.locationDao();
    }

    public EpisodeDao provideEpisodeDao(AppDataBase appDataBase){
        return appDataBase.episodeDao();
    }
}