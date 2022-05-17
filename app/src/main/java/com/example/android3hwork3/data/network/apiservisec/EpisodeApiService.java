package com.example.android3hwork3.data.network.apiservisec;

import com.example.android3hwork3.model.CharacterModel;
import com.example.android3hwork3.model.EpisodeModel;
import com.example.android3hwork3.model.RickyAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EpisodeApiService {

    @GET("api/episode")
    Call<RickyAndMortyResponse<EpisodeModel>> fetchEpisodeModel();
}
