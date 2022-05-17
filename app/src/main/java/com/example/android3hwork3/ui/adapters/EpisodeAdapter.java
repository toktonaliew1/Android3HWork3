package com.example.android3hwork3.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;


import com.example.android3hwork3.databinding.ItemEpisodeBinding;
import com.example.android3hwork3.model.EpisodeModel;

public class EpisodeAdapter extends ListAdapter<EpisodeModel, EpisodeAdapter.ViewHolder> {

    public EpisodeAdapter(@NonNull DiffUtil.ItemCallback<EpisodeModel> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                ItemEpisodeBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(getItem(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private  ItemEpisodeBinding binding;

        public ViewHolder(@NonNull ItemEpisodeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(EpisodeModel model){
            binding.itemEpisodeName.setText(model.getName());
            binding.itemEpisodeAirDate.setText(model.getAir_date());
            binding.itemEpisodeEpisode.setText(model.getEpisode());
        }
    }

    public static DiffUtil.ItemCallback<EpisodeModel> diffCallBack = new DiffUtil.ItemCallback<EpisodeModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull EpisodeModel oldItem, @NonNull EpisodeModel newItem) {
            return oldItem.getId() == newItem.getId();
        }
        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull EpisodeModel oldItem, @NonNull EpisodeModel newItem) {
            return oldItem == newItem;
        }
    };
}