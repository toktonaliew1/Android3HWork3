package com.example.android3hwork3.ui.fargments.location;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.android3hwork3.model.LocationModel;
import com.example.android3hwork3.model.RickyAndMortyResponse;
import com.example.android3hwork3.ui.App;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationViewModel extends ViewModel {

    public MutableLiveData<RickyAndMortyResponse<LocationModel>> data  = new MutableLiveData<>();

    public void getLocationList(){
        App.locationApiServices.fetchLocationModel().enqueue(new Callback<RickyAndMortyResponse<LocationModel>>() {
            @Override
            public void onResponse(Call<RickyAndMortyResponse<LocationModel>> call, Response<RickyAndMortyResponse<LocationModel>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RickyAndMortyResponse<LocationModel>> call, Throwable t) {
                data.setValue(null);
            }
        });
    }
}
