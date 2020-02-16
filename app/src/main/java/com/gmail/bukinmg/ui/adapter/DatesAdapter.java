package com.gmail.bukinmg.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.bukinmg.R;
import com.gmail.bukinmg.databinding.ViewItemBinding;
import com.gmail.bukinmg.model.entity.Day;

import java.util.ArrayList;
import java.util.List;

public class DatesAdapter extends RecyclerView.Adapter<DatesAdapter.DatesViewHolder> {



    private List<Day> days = new ArrayList<>();

    public DatesAdapter(List<Day> days) {
        this.days = days;
    }

    @NonNull
    @Override
    public DatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.view_item, parent, false);
        return new DatesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DatesViewHolder holder, int position) {
        Day day = days.get(position);
        holder.binding.setDays(day);
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public static class DatesViewHolder extends RecyclerView.ViewHolder {

        private ViewItemBinding binding;

        public DatesViewHolder(ViewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Day day) {
            binding.setDays(day);
            binding.executePendingBindings();
        }

    }
}
