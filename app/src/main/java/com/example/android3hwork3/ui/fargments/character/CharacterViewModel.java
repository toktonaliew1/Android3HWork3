package com.example.android3hwork3.ui.fargments.character;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.android3hwork3.model.CharacterModel;
import com.example.android3hwork3.model.RickyAndMortyResponse;
import com.example.android3hwork3.ui.App;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterViewModel extends ViewModel {
    public MutableLiveData<RickyAndMortyResponse<CharacterModel>> data  = new MutableLiveData<>();

    public void getList(){
        App.characterApiService.fetchCharacterModel().enqueue(new Callback<RickyAndMortyResponse<CharacterModel>>() {
            @Override
            public void onResponse(Call<RickyAndMortyResponse<CharacterModel>> call, Response<RickyAndMortyResponse<CharacterModel>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RickyAndMortyResponse<CharacterModel>> call, Throwable t) {
                data.setValue(null);
            }
        });
    }
}
