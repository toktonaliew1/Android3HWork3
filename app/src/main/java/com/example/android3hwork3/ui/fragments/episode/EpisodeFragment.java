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
import com.example.android3hwork3.model.CharacterModel;
import com.example.android3hwork3.model.EpisodeModel;
import com.example.android3hwork3.model.RickyAndMortyResponse;
import com.example.android3hwork3.ui.adapters.EpisodeAdapter;
import com.example.android3hwork3.ui.adapters.onItemClick;
import com.example.android3hwork3.ui.fragments.character.CharacterFragmentDirections;

import java.util.ArrayList;
import java.util.List;


public class EpisodeFragment extends BaseFragment<FragmentEpisodeBinding> {

    private EpisodeViewModel viewModel;
    private EpisodeAdapter episodeAdapter = new EpisodeAdapter(EpisodeAdapter.diffCallBack);

    private LinearLayoutManager linearLayoutManager;
    private boolean loading = true;
    public int pastVisible, visibleCount, totalCount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEpisodeBinding.inflate(inflater , container ,false);
        viewModel = new ViewModelProvider(requireActivity()).get(EpisodeViewModel.class);
        return binding.getRoot();
    }

    @Override
    protected void setupViews() {
        setupListener();
    }

    @Override
    protected void setupListener() {

        linearLayoutManager = new LinearLayoutManager(requireContext());
        binding.episodeRecView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.episodeRecView.setAdapter(episodeAdapter);

        binding.episodeRecView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                            fetchEpisode();
                            loading = true;
                        }
                    }
                }
            }
        });

            episodeAdapter.setItemClick(new onItemClick() {
                @Override
                public void itemClick(int position) {
                    Navigation.findNavController(requireView()).navigate(
                            EpisodeFragmentDirections.actionEpisodeFragmentToEpisodeDetailFragment()
                                    .setPosition(position + 1)
                    );
                }
            });


    }


    private void fetchEpisode() {
        if (isNetwork()) {
            viewModel.fetchEpisodes().observe(getViewLifecycleOwner(), new Observer<RickyAndMortyResponse<EpisodeModel>>() {
                @Override
                public void onChanged(RickyAndMortyResponse<EpisodeModel> episodeModelRickyAndMortyResponse) {
                    ArrayList<EpisodeModel> list = new ArrayList<>(episodeAdapter.getCurrentList());
                    try {
                        list.addAll(episodeModelRickyAndMortyResponse.getResults());
                    } catch (Exception e) {
                        if (loading) {

                            loading = false;
                        }
                    }
                    episodeAdapter.submitList(list);
                }
            });
        } else
            episodeAdapter.submitList((List<EpisodeModel>) viewModel.getEpisodes());

    }


    private boolean isNetwork() {
        ConnectivityManager connectivityManager =

                (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }


    @Override
    protected void setupRequest() {
        fetchEpisode();
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