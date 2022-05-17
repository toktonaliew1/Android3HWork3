package com.example.android3hwork3.ui.fargments.character;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;


import com.example.android3hwork3.base.BaseFragment;
import com.example.android3hwork3.databinding.FragmentCharacterBinding;
import com.example.android3hwork3.model.CharacterModel;
import com.example.android3hwork3.model.RickyAndMortyResponse;
import com.example.android3hwork3.ui.adapters.CharacterAdapter;

public class CharacterFragment extends BaseFragment<FragmentCharacterBinding> {
    private FragmentCharacterBinding binding;
    private CharacterViewModel viewModel;
    private CharacterAdapter characterAdapter = new CharacterAdapter(CharacterAdapter.diffCallBack);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharacterBinding.inflate(inflater , container ,false);
        viewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
        return binding.getRoot();
    }

    @Override
    protected void setupViews() {
        binding.characterRecView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.characterRecView.setAdapter(characterAdapter) ;
    }

    @Override
    protected void setupRequest() {
        viewModel.getList();
    }

    @Override
    protected void setupObserve() {
        viewModel.data.observe(getViewLifecycleOwner(), new Observer<RickyAndMortyResponse<CharacterModel>>() {
            @Override
            public void onChanged(RickyAndMortyResponse<CharacterModel> characterModelRickyAndMortyResponse) {
                characterAdapter.submitList(characterModelRickyAndMortyResponse.getResults());
            }
        });
    }
}