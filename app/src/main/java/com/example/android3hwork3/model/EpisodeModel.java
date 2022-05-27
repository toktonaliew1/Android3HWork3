package com.example.android3hwork3.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "episodeModel")
public class EpisodeModel {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("air_date")
    public String air_date;

    @SerializedName("episode")
    public String episode;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAir_date() {
        return air_date;
    }

    public String getEpisode() {
        return episode;
    }
}