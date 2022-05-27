package com.example.android3hwork3.ui.fragments.location;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.android3hwork3.data.repositories.LocationRepository;
import com.example.android3hwork3.model.LocationModel;
import com.example.android3hwork3.model.RickyAndMortyResponse;

import java.util.ArrayList;

public class LocationViewModel extends ViewModel {

    private final LocationRepository locationRepository = new LocationRepository();
    public  int page = 1;

    public MutableLiveData<RickyAndMortyResponse<LocationModel>> fetchLocation(){
        return locationRepository.fetchLocations(page);
    }

    public MutableLiveData<LocationModel>  fetchLocationId(int id) {
        return locationRepository.fetchLocationId(id);
    }

    public ArrayList<LocationModel> getLocations() {
        return locationRepository.getLocations();
    }
}