package com.gmail.bukinmg.ui.adapter;

import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.bukinmg.R;
import com.gmail.bukinmg.databinding.DayViewItemBinding;
import com.gmail.bukinmg.model.entity.Day;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DatesAdapter extends RecyclerView.Adapter<DatesAdapter.DatesViewHolder> {


    private List<Day> days = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    @Inject
    public DatesAdapter() {
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public DatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DayViewItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.day_view_item, parent, false);
        return new DatesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DatesViewHolder holder, int position) {
        Day day = days.get(position);
        holder.bind(day, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public static class DatesViewHolder extends RecyclerView.ViewHolder {


        private DayViewItemBinding binding;

        public DatesViewHolder(DayViewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Day day, OnItemClickListener onItemClickListener) {
            binding.setDays(day);
            binding.executePendingBindings();
            itemView.setOnClickListener(view -> {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(day);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Day day);
    }

    @BindingAdapter({"app:colorDay"})
    public static void setColor(LinearLayout layout, int color) {

        if (color == 1) {
            layout.setBackgroundResource(R.drawable.line_blue);;
        } else if (color == 2) {
            layout.setBackgroundResource(R.drawable.line_red);;
        } else if (color == 3) {
            layout.setBackgroundResource(R.drawable.line_green);
        }
    }

}
