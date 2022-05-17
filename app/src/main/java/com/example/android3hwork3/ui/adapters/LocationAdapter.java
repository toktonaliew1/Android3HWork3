package com.example.android3hwork3.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.android3hwork3.databinding.ItemLocationBinding;
import com.example.android3hwork3.model.LocationModel;

public class LocationAdapter extends ListAdapter<LocationModel, LocationAdapter.ViewHolder> {

    public LocationAdapter(@NonNull DiffUtil.ItemCallback<LocationModel> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                ItemLocationBinding.inflate(LayoutInflater.from(
                        parent.getContext()), parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(getItem(position));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemLocationBinding binding;

        public ViewHolder(@NonNull ItemLocationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(LocationModel model) {
            binding.itemLocationDirection.setText(model.getDimension());
            binding.itemLocationName.setText(model.getName());
        }
    }

        public static DiffUtil.ItemCallback<LocationModel> diffCallback = new DiffUtil.ItemCallback<LocationModel>() {
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