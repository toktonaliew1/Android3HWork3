package com.example.android3hwork3.ui.fragments.character;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.android3hwork3.data.repositories.CharacterRepository;
import com.example.android3hwork3.model.CharacterModel;
import com.example.android3hwork3.model.RickyAndMortyResponse;

import java.util.ArrayList;

public class CharacterViewModel extends ViewModel {

    private final  CharacterRepository characterRepository = new CharacterRepository();
    public int characterPage = 1;

    public MutableLiveData<RickyAndMortyResponse<CharacterModel>> fetchCharacters(){
        return characterRepository.fetchCharacters(characterPage);
    }

    public MutableLiveData<CharacterModel>  fetchCharacterId(int id) {
        return characterRepository.fetchCharacterId(id);
    }

    public ArrayList<CharacterModel> getCharacters() {
        return characterRepository.getCharacters();
    }
}
