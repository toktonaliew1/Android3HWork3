package com.example.android3hwork3.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RickyAndMortyResponse<T> {

    public List<T> getResults() {
        return results;
    }

    public Info getInfo() {
        return info;
    }

    @SerializedName("results")
    private List<T> results;

    @SerializedName("info")
    private Info info;
}
