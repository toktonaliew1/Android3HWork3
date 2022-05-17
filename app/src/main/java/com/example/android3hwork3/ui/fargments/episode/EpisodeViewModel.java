package com.example.android3hwork3.ui.fargments.episode;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.android3hwork3.model.EpisodeModel;
import com.example.android3hwork3.model.RickyAndMortyResponse;
import com.example.android3hwork3.ui.App;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeViewModel  extends ViewModel {
    public MutableLiveData<RickyAndMortyResponse<EpisodeModel>> data  = new MutableLiveData<>();

    public void getEpisodeList(){
        App.episodeApiService.fetchEpisodeModel().enqueue(new Callback<RickyAndMortyResponse<EpisodeModel>>() {
            @Override
            public void onResponse(Call<RickyAndMortyResponse<EpisodeModel>> call, Response<RickyAndMortyResponse<EpisodeModel>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RickyAndMortyResponse<EpisodeModel>> call, Throwable t) {
                data.setValue(null);
            }
        });
    }
}

