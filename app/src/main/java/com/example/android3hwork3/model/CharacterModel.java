package com.example.android3hwork3.model;

import com.google.gson.annotations.SerializedName;

public class CharacterModel {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("image")
    private String image;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
