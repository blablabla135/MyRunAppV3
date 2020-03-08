package com.gmail.bukinmg.ui.adapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.bukinmg.R;
import com.gmail.bukinmg.databinding.EventViewItemBinding;
import com.gmail.bukinmg.model.entity.Event;
import com.gmail.bukinmg.utility.EventDayComparator;
import com.gmail.bukinmg.utility.EventMonthComparator;
import com.gmail.bukinmg.utility.EventYearComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

@RequiresApi(api = Build.VERSION_CODES.N)
public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {

    private List<Event> events = new ArrayList<>();
    Comparator<Event> comparator = new EventYearComparator().thenComparing(new EventMonthComparator().thenComparing(new EventDayComparator()));


    @Inject
    public EventsAdapter() {
    }


    public void setEvents(List<Event> events) {
        this.events = events;
        Collections.sort(events, comparator);
    }

    @NonNull
    @Override
    public EventsAdapter.EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        EventViewItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.event_view_item, parent, false);
        return new EventsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsAdapter.EventsViewHolder holder, int position) {
        Event event = events.get(position);
        holder.bind(event);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class EventsViewHolder extends RecyclerView.ViewHolder {

        private EventViewItemBinding binding;

        public EventsViewHolder(EventViewItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Event event) {
            binding.setEvents(event);
            binding.executePendingBindings();
        }
    }
}
