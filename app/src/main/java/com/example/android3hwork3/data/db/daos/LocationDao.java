package com.example.android3hwork3.data.db.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.android3hwork3.model.LocationModel;

import java.util.List;

@Dao
public interface LocationDao {

    @Query("SELECT * FROM locationModel")
    List<LocationModel> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<LocationModel> locationModels);
}
