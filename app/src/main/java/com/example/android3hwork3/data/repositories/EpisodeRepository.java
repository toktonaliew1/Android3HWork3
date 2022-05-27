package com.example.android3hwork3.data.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.android3hwork3.App;
import com.example.android3hwork3.model.EpisodeModel;
import com.example.android3hwork3.model.RickyAndMortyResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeRepository{

    public MutableLiveData<RickyAndMortyResponse<EpisodeModel>>  fetchEpisodes(int page) {

        MutableLiveData<RickyAndMortyResponse<EpisodeModel>> data = new MutableLiveData<>();
        App.episodeApiService.fetchEpisodeModel(page).enqueue(new Callback<RickyAndMortyResponse<EpisodeModel>>() {
            @Override
            public void onResponse(@NonNull Call<RickyAndMortyResponse<EpisodeModel>> call, @NonNull Response<RickyAndMortyResponse<EpisodeModel>> response) {
                if (response.body() != null) {
                    App.episodeDao.insertAll(response.body().getResults());
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<RickyAndMortyResponse<EpisodeModel>> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public ArrayList<EpisodeModel> getEpisodes(){
        ArrayList<EpisodeModel> list = new ArrayList<>();
        list.addAll(App.episodeDao.getAll());
        return list;
    }

    public MutableLiveData<EpisodeModel> fetchEpisodeId(int id ){
        MutableLiveData<EpisodeModel> dataId = new MutableLiveData<>();
        App.episodeApiService.fetchEpisodeId(id).enqueue(new Callback<EpisodeModel>() {
            @Override
            public void onResponse(@NonNull Call<EpisodeModel> call, @NonNull Response<EpisodeModel> response) {
                if (response.body() != null)
                    dataId.setValue(response.body());
            }
            @Override
            public void onFailure(@NonNull Call<EpisodeModel> call, @NonNull Throwable t) {
                dataId.setValue(null);

            }
        });
        return dataId;
    }
}