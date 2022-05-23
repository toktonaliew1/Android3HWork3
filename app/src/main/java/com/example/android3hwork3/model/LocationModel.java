package com.example.android3hwork3.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "locationModel")
public class LocationModel {


    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("dimension")
    public String dimension;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDimension() {
        return dimension;
    }
}
