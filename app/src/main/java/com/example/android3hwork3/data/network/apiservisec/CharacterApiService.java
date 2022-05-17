package com.example.android3hwork3.data.network.apiservisec;

import com.example.android3hwork3.model.CharacterModel;
import com.example.android3hwork3.model.RickyAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CharacterApiService {

    @GET("api/character")
    Call<RickyAndMortyResponse<CharacterModel>> fetchCharacterModel();
}
