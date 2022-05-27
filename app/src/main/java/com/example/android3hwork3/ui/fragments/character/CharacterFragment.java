package com.example.android3hwork3.ui.fragments.character;

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
import com.example.android3hwork3.databinding.FragmentCharacterBinding;
import com.example.android3hwork3.model.CharacterModel;
import com.example.android3hwork3.model.RickyAndMortyResponse;
import com.example.android3hwork3.ui.adapters.CharacterAdapter;
import com.example.android3hwork3.ui.adapters.onItemClick;

import java.util.ArrayList;

public class CharacterFragment extends BaseFragment<FragmentCharacterBinding> {

    private CharacterViewModel viewModel;
    private final CharacterAdapter characterAdapter = new CharacterAdapter(CharacterAdapter.diffCallBack);
    private LinearLayoutManager linearLayoutManager;
    private boolean loading = true;
    public int pastVisible, visibleCount, totalCount;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharacterBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
        return binding.getRoot();
    }

    @Override
    protected void setupViews() {
        linearLayoutManager = new LinearLayoutManager(requireContext());
        binding.characterRecView.setLayoutManager(linearLayoutManager);
        binding.characterRecView.setAdapter(characterAdapter);
    }

    protected void fetchCharacter() {
        binding.characterRecView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleCount = linearLayoutManager.getChildCount();
                    totalCount = linearLayoutManager.getItemCount();
                    pastVisible = linearLayoutManager.findFirstVisibleItemPosition();
                    if ((visibleCount + pastVisible) >= totalCount) {
                        if (viewModel.characterPage != totalCount && (viewModel.characterPage < totalCount)) {
                            viewModel.characterPage++;
                            if (!loading && (viewModel.characterPage < totalCount)) {
                                fetchCharacters();
                            }
                        }
                    }
                }
            }
        });

        characterAdapter.setItemClick(new onItemClick() {
            @Override
            public void itemClick(CharacterModel model ) {
                Navigation.findNavController((requireView())).navigate(
                        CharacterFragmentDirections.actionCharacterFragmentToCharacterDetailFragment()
                                .setPosition(model.getId() )
                );
            }
        });
    }


    private void fetchCharacters() {
        if (hasConnection()) {
            viewModel.fetchCharacters().observe(getViewLifecycleOwner(), new Observer<RickyAndMortyResponse<CharacterModel>>() {
                @Override
                public void onChanged(RickyAndMortyResponse<CharacterModel> characterModelRickAndMortyResponse) {
                    if (!loading) {
                        ArrayList<CharacterModel> list = new ArrayList<>(characterAdapter.getCurrentList());
                        list.addAll(characterModelRickAndMortyResponse.getResults());
                        characterAdapter.submitList(list);
                        if (list != characterAdapter.getCurrentList()) {
                            characterAdapter.submitList(list);
                        }
                    }
                }
            });
        } else
            characterAdapter.submitList(viewModel.getCharacters());
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
        viewModel.fetchCharacters().observe(getViewLifecycleOwner(), new Observer<RickyAndMortyResponse<CharacterModel>>() {
            @Override
            public void onChanged(RickyAndMortyResponse<CharacterModel> characterModelRickAndMortyResponse) {
                if (loading) {
                    ArrayList<CharacterModel> list = new ArrayList<>(characterAdapter.getCurrentList());
                    list.addAll(characterModelRickAndMortyResponse.getResults());
                    characterAdapter.submitList(list);
                    loading = false;
                }
            }
        });
    }
}