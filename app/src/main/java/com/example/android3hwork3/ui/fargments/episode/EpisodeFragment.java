package com.example.android3hwork3.ui.fargments.episode;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.android3hwork3.base.BaseFragment;
import com.example.android3hwork3.databinding.FragmentEpisodeBinding;
import com.example.android3hwork3.model.EpisodeModel;
import com.example.android3hwork3.model.RickyAndMortyResponse;
import com.example.android3hwork3.ui.adapters.EpisodeAdapter;


public class EpisodeFragment extends BaseFragment<FragmentEpisodeBinding> {
    private FragmentEpisodeBinding binding;
    private EpisodeViewModel viewModel;
    private EpisodeAdapter episodeAdapter = new EpisodeAdapter(EpisodeAdapter.diffCallBack);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodeBinding.inflate(inflater , container ,false);
        viewModel = new ViewModelProvider(requireActivity()).get(EpisodeViewModel.class);
        return binding.getRoot();
    }

    @Override
    protected void setupViews() {
        binding.episodeRecView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.episodeRecView.setAdapter(episodeAdapter) ;
    }

    @Override
    protected void setupRequest() {
        viewModel.getEpisodeList();
    }

    @Override
    protected void setupObserve() {
        viewModel.data.observe(getViewLifecycleOwner(), new Observer<RickyAndMortyResponse<EpisodeModel>>() {
            @Override
            public void onChanged(RickyAndMortyResponse<EpisodeModel> episodeModelRickyAndMortyResponse) {
                episodeAdapter.submitList(episodeModelRickyAndMortyResponse.getResults());
            }
        });
    }
}