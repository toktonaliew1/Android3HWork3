package com.example.android3hwork3.ui.fragments.location.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.android3hwork3.databinding.FragmentLocationDetailBinding;
import com.example.android3hwork3.model.LocationModel;
import com.example.android3hwork3.ui.fragments.episode.detail.EpisodeDetailFragmentArgs;
import com.example.android3hwork3.ui.fragments.location.LocationViewModel;


public class LocationDetailFragment extends Fragment {

    private FragmentLocationDetailBinding binding;
    private LocationViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLocationDetailBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(LocationViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int  args = EpisodeDetailFragmentArgs.fromBundle(getArguments()).getPosition();
        viewModel.fetchLocationId(args).observe(getViewLifecycleOwner(), new Observer<LocationModel>() {
            @Override
            public void onChanged(LocationModel locationModel) {
                binding.name.setText(locationModel.getName());
                binding.direction.setText(locationModel.getDimension());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding  = null;
    }
}