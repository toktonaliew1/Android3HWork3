package com.example.android3hwork3.data.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import com.example.android3hwork3.App;
import com.example.android3hwork3.model.CharacterModel;
import com.example.android3hwork3.model.RickyAndMortyResponse;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterRepository {

    public MutableLiveData<RickyAndMortyResponse<CharacterModel>>  fetchCharacters(int page) {
        MutableLiveData<RickyAndMortyResponse<CharacterModel>> data = new MutableLiveData<>();
        App.characterApiService.fetchCharacterModel(page).enqueue(new Callback<RickyAndMortyResponse<CharacterModel>>() {
            @Override
            public void onResponse( Call<RickyAndMortyResponse<CharacterModel>> call,  Response<RickyAndMortyResponse<CharacterModel>> response) {
                if (response.body() != null) {
                    App.characterDao.insertAll(response.body().getResults());
                    data.setValue(response.body());
                }
            }
            @Override
            public void onFailure( Call<RickyAndMortyResponse<CharacterModel>> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public ArrayList<CharacterModel> getCharacters(){
        ArrayList<CharacterModel> list = new ArrayList<>();
        list.addAll(App.characterDao.getAll());
        return list;
    }

    public MutableLiveData<CharacterModel> fetchCharacterId(int id ){
        MutableLiveData<CharacterModel> dataId = new MutableLiveData<>();
        App.characterApiService.fetchCharacterId(id).enqueue(new Callback<CharacterModel>() {
            @Override
            public void onResponse(@NonNull Call<CharacterModel> call, @NonNull Response<CharacterModel> response) {
                if (response.body() != null)
                    dataId.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<CharacterModel> call, @NonNull Throwable t) {
                dataId.setValue(null);

            }
        });
        return dataId;
    }
}