package com.gmail.bukinmg.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.bukinmg.R;
import com.gmail.bukinmg.model.Entity.Day;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DatesAdapter extends RecyclerView.Adapter<DatesAdapter.DatesViewHolder> {

    private List<Day> days = new ArrayList<>();

    public DatesAdapter(List<Day> days) {
        this.days = days;
    }




    @NonNull
    @Override
    public DatesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item, parent, false);
        return new DatesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DatesViewHolder holder, int position) {
        holder.day.setText(String.valueOf(days.get(position).getDay()));
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public static class DatesViewHolder extends RecyclerView.ViewHolder{

        TextView day;

        public DatesViewHolder(@NonNull View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.day);
        }
    }
}
