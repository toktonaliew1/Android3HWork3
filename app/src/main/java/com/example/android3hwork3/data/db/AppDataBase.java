package com.example.android3hwork3.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.android3hwork3.data.db.daos.CharacterDao;
import com.example.android3hwork3.data.db.daos.EpisodeDao;
import com.example.android3hwork3.data.db.daos.LocationDao;
import com.example.android3hwork3.model.CharacterModel;
import com.example.android3hwork3.model.EpisodeModel;
import com.example.android3hwork3.model.LocationModel;

@Database(entities = {CharacterModel.class, LocationModel.class, EpisodeModel.class }, version = 3)
abstract class AppDataBase extends RoomDatabase {

    public abstract CharacterDao characterDao();

    public abstract LocationDao locationDao();

    public abstract EpisodeDao episodeDao();
}
