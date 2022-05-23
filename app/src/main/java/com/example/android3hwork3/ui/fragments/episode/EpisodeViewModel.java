package com.example.android3hwork3.ui.fragments.episode;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.android3hwork3.data.repositories.EpisodeRepository;
import com.example.android3hwork3.model.CharacterModel;
import com.example.android3hwork3.model.EpisodeModel;
import com.example.android3hwork3.model.RickyAndMortyResponse;

import java.util.ArrayList;

public class EpisodeViewModel  extends ViewModel {

    private EpisodeRepository episodeRepository = new EpisodeRepository();
    public  int page = 1;

    public MutableLiveData<RickyAndMortyResponse<EpisodeModel>> fetchEpisodes(){
        return episodeRepository.fetchEpisodes(page);
    }
    public MutableLiveData<EpisodeModel>  fetchEpisodeId(int id) {
        return episodeRepository.fetchEpisodeId(id);
    }

    public ArrayList<EpisodeModel> getEpisodes() {
        return episodeRepository.getEpisodes();
    }
}
