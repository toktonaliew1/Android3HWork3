package com.example.android3hwork3.ui.fragments.episode;

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
import com.example.android3hwork3.databinding.FragmentEpisodeBinding;
import com.example.android3hwork3.model.EpisodeModel;
import com.example.android3hwork3.model.RickyAndMortyResponse;
import com.example.android3hwork3.ui.adapters.EpisodeAdapter;
import com.example.android3hwork3.ui.adapters.onEpisodeClick;
import java.util.ArrayList;

public class EpisodeFragment extends BaseFragment<FragmentEpisodeBinding> {

    private EpisodeViewModel viewModel;
    private final EpisodeAdapter episodeAdapter = new EpisodeAdapter(EpisodeAdapter.diffCallBack);
    private LinearLayoutManager linearLayoutManager;
    private boolean loading = true;
    public int pastVisible, visibleCount, totalCount;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodeBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(EpisodeViewModel.class);
        return binding.getRoot();
    }

    @Override
    protected void setupViews() {
        linearLayoutManager = new LinearLayoutManager(requireContext());
        binding.episodeRecView.setLayoutManager(linearLayoutManager);
        binding.episodeRecView.setAdapter(episodeAdapter);
    }

    protected void fetchEpisode() {
        binding.episodeRecView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                                fetchEpisodes();
                            }
                        }
                    }
                }
            }
        });

        episodeAdapter.setItemClick(new onEpisodeClick() {
            @Override
            public void itemClick(EpisodeModel model) {
                Navigation.findNavController(requireView()).navigate(
                        EpisodeFragmentDirections.actionEpisodeFragmentToEpisodeDetailFragment()
                        .setPosition(model.getId())
                );
            }
        });
    }

    private void fetchEpisodes() {
        if (hasConnection()) {
            viewModel.fetchEpisodes().observe(getViewLifecycleOwner(), new Observer<RickyAndMortyResponse<EpisodeModel>>() {
                @Override
                public void onChanged(RickyAndMortyResponse<EpisodeModel> episodeModelRickyAndMortyResponse) {
                    if (!loading) {
                        ArrayList<EpisodeModel> list = new ArrayList<>(episodeAdapter.getCurrentList());
                        list.addAll(episodeModelRickyAndMortyResponse.getResults());
                        episodeAdapter.submitList(list);
                        if (list != episodeAdapter.getCurrentList()) {
                            episodeAdapter.submitList(list);
                        }
                    }
                }
            });
        } else
            episodeAdapter.submitList(viewModel.getEpisodes());
    }

    public boolean hasConnection() {
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNW = cm.getActiveNetworkInfo();
        if (activeNW != null && activeNW.isConnected()) {
            return true;
        }else
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
        viewModel.fetchEpisodes().observe(getViewLifecycleOwner(), new Observer<RickyAndMortyResponse<EpisodeModel>>() {
            @Override
            public void onChanged(RickyAndMortyResponse<EpisodeModel> episodeModelRickyAndMortyResponse) {
                if (loading) {
                    ArrayList<EpisodeModel> list = new ArrayList<>(episodeAdapter.getCurrentList());
                    list.addAll(episodeModelRickyAndMortyResponse.getResults());
                    episodeAdapter.submitList(list);
                    loading = false;
                }
            }
        });
    }
}