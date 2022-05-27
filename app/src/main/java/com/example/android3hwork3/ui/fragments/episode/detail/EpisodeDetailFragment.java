package com.example.android3hwork3.ui.fragments.episode.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.android3hwork3.databinding.FragmentEpisodeDetailBinding;

import com.example.android3hwork3.model.EpisodeModel;

import com.example.android3hwork3.ui.fragments.episode.EpisodeViewModel;

public class EpisodeDetailFragment extends Fragment {

    private FragmentEpisodeDetailBinding binding;
    private EpisodeViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodeDetailBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(EpisodeViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int args = EpisodeDetailFragmentArgs.fromBundle(getArguments()).getPosition();
        viewModel.fetchEpisodeId(args).observe(getViewLifecycleOwner(), new Observer<EpisodeModel>() {
            @Override
            public void onChanged(EpisodeModel episodeModel) {
                binding.airDate.setText(episodeModel.getAir_date());
                binding.name.setText(episodeModel.getName());
                binding.episode.setText(episodeModel.getEpisode());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}