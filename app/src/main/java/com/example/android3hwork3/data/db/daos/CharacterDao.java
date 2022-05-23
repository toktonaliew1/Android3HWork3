package com.example.android3hwork3.data.db.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.android3hwork3.model.CharacterModel;

import java.util.List;

@Dao
public interface CharacterDao {

    @Query("SELECT * FROM characterModel")
    List<CharacterModel> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<CharacterModel> characterModels);
}
