package com.example.android3hwork3.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.android3hwork3.databinding.ItemCharacterBinding;
import com.example.android3hwork3.model.CharacterModel;

public class CharacterAdapter  extends ListAdapter<CharacterModel, CharacterAdapter.ViewHolder> {

    private onItemClick onItemClick;

    public CharacterAdapter(@NonNull DiffUtil.ItemCallback<CharacterModel> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                ItemCharacterBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(getItem(position));
    }

    public void setItemClick(onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemCharacterBinding binding;

        public ViewHolder(@NonNull ItemCharacterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(CharacterModel model) {
            Glide.with(binding.itemCharacterImage).load(model.getImage()).into(binding.itemCharacterImage);
            binding.itemCharacterName.setText(model.getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick.itemClick(model);
                }
            });
        }
    }

    public static DiffUtil.ItemCallback<CharacterModel> diffCallBack = new DiffUtil.ItemCallback<CharacterModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull CharacterModel oldItem, @NonNull CharacterModel newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull CharacterModel oldItem, @NonNull CharacterModel newItem) {
            return oldItem == newItem;
        }
    };
}