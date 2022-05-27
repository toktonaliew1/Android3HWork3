package com.example.android3hwork3.data.network.apiservices;
import com.example.android3hwork3.model.EpisodeModel;
import com.example.android3hwork3.model.RickyAndMortyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EpisodeApiServices {

    @GET("api/episode")
    Call<RickyAndMortyResponse<EpisodeModel>> fetchEpisodeModel(
            @Query("page") int page
    );

    @GET("api/episode/{id}")
    Call<EpisodeModel> fetchEpisodeId(
            @Path("id") int id
    );
}