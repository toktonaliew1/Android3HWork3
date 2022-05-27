package com.example.android3hwork3.ui.fragments.location;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android3hwork3.base.BaseFragment;
import com.example.android3hwork3.databinding.FragmentLocationBinding;
import com.example.android3hwork3.model.LocationModel;
import com.example.android3hwork3.model.RickyAndMortyResponse;
import com.example.android3hwork3.ui.adapters.LocationAdapter;
import com.example.android3hwork3.ui.adapters.OnLocationClick;
import java.util.ArrayList;
import java.util.List;

public class LocationFragment extends BaseFragment<FragmentLocationBinding> {

    private LocationViewModel viewModel;
    private final LocationAdapter locationAdapter = new LocationAdapter(LocationAdapter.diffCallBack);
    private LinearLayoutManager linearLayoutManager;
    private boolean loading = true;
    public int pastVisible, visibleCount, totalCount;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocationBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(LocationViewModel.class);
        return binding.getRoot();
    }

    @Override
    protected void setupViews() {
        linearLayoutManager = new LinearLayoutManager(requireContext());
        binding.locationRecView.setLayoutManager(linearLayoutManager);
        binding.locationRecView.setAdapter(locationAdapter);
    }

    protected void fetchLocation() {
        binding.locationRecView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleCount = linearLayoutManager.getChildCount();
                    totalCount = linearLayoutManager.getItemCount();
                    pastVisible = linearLayoutManager.findFirstVisibleItemPosition();
                    if ((visibleCount + pastVisible) >= totalCount) {
                        if (viewModel.page != totalCount && (viewModel.page < totalCount)) {
                            viewModel.page++;
                            if (!loading && (viewModel.page < totalCount)) {
                                fetchLocations();
                            }
                        }
                    }
                }
            }
        });
        locationAdapter.setOnItemClick(new OnLocationClick() {
            @Override
            public void itemClick(LocationModel model ) {
                Navigation.findNavController((requireView())).navigate(
                        LocationFragmentDirections.actionLocationFragmentToLocationDetailFragment()
                                .setPosition(model.getId() )
                );
            }
        });
    }


    private void fetchLocations() {
        if (hasConnection()) {
            viewModel.fetchLocation().observe(getViewLifecycleOwner(), new Observer<RickyAndMortyResponse<LocationModel>>() {
                @Override
                public void onChanged(RickyAndMortyResponse<LocationModel> locationModelRickyAndMortyResponse) {
                    if (!loading) {
                        ArrayList<LocationModel> list = new ArrayList<>(locationAdapter.getCurrentList());
                        list.addAll(locationModelRickyAndMortyResponse.getResults());
                        locationAdapter.submitList(list);
                        if (list != locationAdapter.getCurrentList()) {
                            locationAdapter.submitList(list);
                        }
                    }
                }
            });
        } else
            locationAdapter.submitList((List<LocationModel>) viewModel.getLocations());
    }

    public boolean hasConnection() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNW = cm.getActiveNetworkInfo();
        if (activeNW != null && activeNW.isConnected()) {
            return true;
        } else
            return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.fetchLocation().observe(getViewLifecycleOwner(), new Observer<RickyAndMortyResponse<LocationModel>>() {
            @Override
            public void onChanged(RickyAndMortyResponse<LocationModel> locationModelRickyAndMortyResponse) {
                if (loading) {
                    ArrayList<LocationModel> list = new ArrayList<>(locationAdapter.getCurrentList());list.addAll(locationModelRickyAndMortyResponse.getResults());
                    locationAdapter.submitList(list);
                    loading = false;
                }
            }
        });
    }
}