package com.example.android3hwork3.model;

import com.google.gson.annotations.SerializedName;

public class LocationModel {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("dimension")
    private String dimension;

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
