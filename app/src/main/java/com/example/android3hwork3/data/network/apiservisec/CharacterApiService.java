package com.example.android3hwork3.data.network.apiservisec;

import com.example.android3hwork3.model.CharacterModel;
import com.example.android3hwork3.model.RickyAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CharacterApiService {

    @GET("api/character")
    Call<RickyAndMortyResponse<CharacterModel>> fetchCharacterModel(
            @Query("page") int page
    );

    @GET("api/character/{id}")
    Call<CharacterModel> fetchCharacterId(
            @Path("id") int id
    );
}