package com.example.android3hwork3.ui.fargments.location;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.android3hwork3.base.BaseFragment;
import com.example.android3hwork3.databinding.FragmentLocationBinding;
import com.example.android3hwork3.model.LocationModel;
import com.example.android3hwork3.model.RickyAndMortyResponse;
import com.example.android3hwork3.ui.adapters.LocationAdapter;


public class LocationFragment extends BaseFragment<FragmentLocationBinding> {
    private FragmentLocationBinding binding;
    private LocationViewModel viewModel;
    private LocationAdapter locationAdapter = new LocationAdapter(LocationAdapter.diffCallback);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocationBinding.inflate(inflater , container ,false);
        viewModel = new ViewModelProvider(requireActivity()).get(LocationViewModel.class);
        return binding.getRoot();
    }

    @Override
    protected void setupViews() {
        binding.locationRecView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.locationRecView.setAdapter(locationAdapter) ;
    }

    @Override
    protected void setupRequest() {
        viewModel.getLocationList();
    }

    @Override
    protected void setupObserve() {
        viewModel.data.observe(getViewLifecycleOwner(), new Observer<RickyAndMortyResponse<LocationModel>>() {
            @Override
            public void onChanged(RickyAndMortyResponse<LocationModel> locationModelRickyAndMortyResponse) {
                locationAdapter.submitList(locationModelRickyAndMortyResponse.getResults());
            }
        });
    }
}