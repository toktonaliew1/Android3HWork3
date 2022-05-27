package com.example.android3hwork3.data.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.android3hwork3.App;
import com.example.android3hwork3.model.LocationModel;
import com.example.android3hwork3.model.RickyAndMortyResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationRepository {

    public MutableLiveData<RickyAndMortyResponse<LocationModel>>  fetchLocations(int page) {

    MutableLiveData<RickyAndMortyResponse<LocationModel>> data = new MutableLiveData<>();
        App.locationApiServices.fetchLocationModel(page).enqueue(new Callback<RickyAndMortyResponse<LocationModel>>() {
        @Override
        public void onResponse(@NonNull Call<RickyAndMortyResponse<LocationModel>> call, @NonNull Response<RickyAndMortyResponse<LocationModel>> response) {
            if (response.body() != null) {
                App.locationDao.insertAll(response.body().getResults());
                data.setValue(response.body());
            }
        }

        @Override
        public void onFailure(@NonNull Call<RickyAndMortyResponse<LocationModel>> call, @NonNull Throwable t) {
            data.setValue(null);
        }
    });
        return data;
}

    public ArrayList<LocationModel> getLocations(){
        ArrayList<LocationModel> list = new ArrayList<>();
        list.addAll(App.locationDao.getAll());
        return list;
    }

    public MutableLiveData<LocationModel> fetchLocationId(int id ){
        MutableLiveData<LocationModel> dataId = new MutableLiveData<>();
        App.locationApiServices.fetchLocationId(id).enqueue(new Callback<LocationModel>() {
            @Override
            public void onResponse(@NonNull Call<LocationModel> call, @NonNull Response<LocationModel> response) {
                if (response.body() != null)
                    dataId.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<LocationModel> call, @NonNull Throwable t) {
                dataId.setValue(null);
            }
        });
        return dataId;
    }
}