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
import com.example.android3hwork3.databinding.ItemLocationBinding;
import com.example.android3hwork3.model.CharacterModel;
import com.example.android3hwork3.model.LocationModel;

public class LocationAdapter extends ListAdapter<LocationModel, LocationAdapter.ViewHolder> {

    private onItemClick onItemClick;

    public LocationAdapter(@NonNull DiffUtil.ItemCallback<LocationModel> diffCallback) {
        super(diffCallback);
    }


    @NonNull
    @Override
    public LocationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                ItemLocationBinding.inflate(LayoutInflater.from(parent.getContext()),
                        parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.ViewHolder holder, int position) {
        holder.onBind(getItem(position));
    }

    public void setItemClick(onItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemLocationBinding binding;

        public ViewHolder(@NonNull ItemLocationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(LocationModel model) {
            binding.itemLocationName.setText(model.getName());
            binding.itemLocationDirection.setText(model.getDimension());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick.itemClick(getAdapterPosition());
                }
            });
        }
    }

    public static DiffUtil.ItemCallback<LocationModel> diffCallBack = new DiffUtil.ItemCallback<LocationModel>() {
        @Override
        public boolean areItemsTheSame(@NonNull LocationModel oldItem, @NonNull LocationModel newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull LocationModel oldItem, @NonNull LocationModel newItem) {
            return oldItem == newItem;
        }
    };
}