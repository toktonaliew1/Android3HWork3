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
import com.example.android3hwork3.model.EpisodeModel;
import com.example.android3hwork3.model.LocationModel;
import com.example.android3hwork3.model.RickyAndMortyResponse;
import com.example.android3hwork3.ui.adapters.LocationAdapter;
import com.example.android3hwork3.ui.adapters.onItemClick;
import com.example.android3hwork3.ui.fragments.episode.EpisodeFragmentDirections;

import java.util.ArrayList;
import java.util.List;

public class LocationFragment extends BaseFragment<FragmentLocationBinding> {

    private FragmentLocationBinding binding;
    private LocationViewModel viewModel;
    private LocationAdapter locationAdapter = new LocationAdapter(LocationAdapter.diffCallBack);

    private LinearLayoutManager linearLayoutManager;
    private boolean loading = true;
    public int pastVisible, visibleCount, totalCount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocationBinding.inflate(inflater , container ,false);
        viewModel = new ViewModelProvider(requireActivity()).get(LocationViewModel.class);
        return binding.getRoot();
    }

    @Override
    protected void setupViews() {
        setupListener();
    }

    @Override
    protected void setupListener() {

        linearLayoutManager = new LinearLayoutManager(requireContext());
        binding.locationRecView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.locationRecView.setAdapter(locationAdapter);

        binding.locationRecView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleCount = linearLayoutManager.getChildCount();
                    totalCount = linearLayoutManager.getItemCount();
                    pastVisible = linearLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleCount + pastVisible) >= totalCount) {
                            loading = false;
                            viewModel.page++;
                            fetchLocation();
                            loading = true;
                        }
                    }
                }
            }
        });

        locationAdapter.setItemClick(new onItemClick() {
            @Override
            public void itemClick(int position) {
                Navigation.findNavController(requireView()).navigate(
                        LocationFragmentDirections.actionLocationFragmentToLocationDetailFragment()
                                .setPosition(position + 1)
                );
            }
        });
    }


    private void fetchLocation() {
        if (isNetwork()) {
            viewModel.fetchLocation().observe(getViewLifecycleOwner(), new Observer<RickyAndMortyResponse<LocationModel>>() {
                @Override
                public void onChanged(RickyAndMortyResponse<LocationModel> episodeModelRickyAndMortyResponse) {
                    ArrayList<LocationModel> list = new ArrayList<>(locationAdapter.getCurrentList());
                    try {
                        list.addAll(episodeModelRickyAndMortyResponse.getResults());
                    } catch (Exception e) {
                        if (loading) {

                            loading = false;
                        }
                    }
                    locationAdapter.submitList(list);
                }
            });
        } else
            locationAdapter.submitList((List<LocationModel>) viewModel.getLocations());

    }

    private boolean isNetwork() {
        ConnectivityManager connectivityManager =

                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }


    @Override
    protected void setupRequest() {
        fetchLocation();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (viewModel.page != 1)
            viewModel.page--;
    }

}