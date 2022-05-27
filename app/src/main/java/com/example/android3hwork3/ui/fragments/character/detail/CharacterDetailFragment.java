package com.example.android3hwork3.ui.fragments.character.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.android3hwork3.databinding.FragmentCharacterDetailBinding;
import com.example.android3hwork3.model.CharacterModel;
import com.example.android3hwork3.ui.fragments.character.CharacterViewModel;

public class CharacterDetailFragment extends Fragment {

    private FragmentCharacterDetailBinding binding;
    private CharacterViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(CharacterViewModel.class);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int  args = CharacterDetailFragmentArgs.fromBundle(getArguments()).getPosition();
        viewModel.fetchCharacterId(args).observe(getViewLifecycleOwner(), new Observer<CharacterModel>() {
            @Override
            public void onChanged(CharacterModel characterModel) {
                Glide.with(binding.image).load(characterModel.getImage()).into(binding.image);
                binding.name.setText(characterModel.getName());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding  = null;
    }
}
