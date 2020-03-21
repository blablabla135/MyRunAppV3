package com.gmail.bukinmg.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.bukinmg.R;
import com.gmail.bukinmg.model.entity.MainEvent;

import java.util.ArrayList;
import java.util.List;


public class MainEventsAdapter extends RecyclerView.Adapter<MainEventsAdapter.MainEventsViewHolder> {

    private List<MainEvent> mainEventList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public MainEventsAdapter() {
    }

    @NonNull
    @Override
    public MainEventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_event_view_item, parent, false);
        return new MainEventsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainEventsViewHolder holder, int position) {
        MainEvent mainEvent = mainEventList.get(position);
        holder.textName.setText(mainEvent.getName());
        holder.textDate.setText(mainEvent.getDate());
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(mainEvent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mainEventList.size();
    }

    public void setMainEventList(List<MainEvent> mainEventList) {
        this.mainEventList = mainEventList;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public static class MainEventsViewHolder extends RecyclerView.ViewHolder {

        private TextView textName;
        private TextView textDate;

        public MainEventsViewHolder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.name_me);
            textDate = itemView.findViewById(R.id.date_me);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(MainEvent mainEvent);
    }


}
