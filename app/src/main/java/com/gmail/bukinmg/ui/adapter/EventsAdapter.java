package com.gmail.bukinmg.ui.adapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.gmail.bukinmg.R;
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
    private Comparator<Event> comparator = new EventYearComparator().thenComparing(new EventMonthComparator().thenComparing(new EventDayComparator()));


    @Inject
    public EventsAdapter() {
    }

    public Event getEventAt(int position) {
        return events.get(position);
    }

    public void setEvents(List<Event> events) {
        this.events = events;
        Collections.sort(events, comparator);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_view_item, parent, false);
        return new EventsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsAdapter.EventsViewHolder holder, int position) {
        Event event = events.get(position);
        String date = event.getDay() + " " + event.getStringMonth() + " " + event.getYear();
        holder.textViewDate.setText(date);
        String distance = event.getDistance() + "km";
        holder.textViewDistance.setText(distance);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static class EventsViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewDate;
        private TextView textViewDistance;

        public EventsViewHolder(View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.date);
            textViewDistance = itemView.findViewById(R.id.distance);
        }
    }
}
