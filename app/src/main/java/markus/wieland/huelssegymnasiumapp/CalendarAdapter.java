package markus.wieland.huelssegymnasiumapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.time.LocalDate;

import markus.wieland.defaultappelements.uielements.adapter.DefaultViewHolder;
import markus.wieland.defaultappelements.uielements.adapter.QueryableAdapter;
import markus.wieland.defaultappelements.uielements.adapter.iteractlistener.OnItemInteractListener;
import markus.wieland.huelssegymnasiumapp.calendar.CalendarEntry;
import markus.wieland.huelssegymnasiumapp.calendar.LocalDateConverter;

public class CalendarAdapter extends QueryableAdapter<Long, CalendarEntry, CalendarAdapter.CalendarViewHolder> {

    public CalendarAdapter(OnItemInteractListener<CalendarEntry> onItemInteractListener) {
        super(onItemInteractListener);
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CalendarViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendar_entry, parent, false));
    }

    public class CalendarViewHolder extends DefaultViewHolder<CalendarEntry> {

        private TextView date;

        public CalendarViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bindViews() {
            date = findViewById(R.id.item_calendar_date);
        }

        @Override
        public void bindItemToViewHolder(CalendarEntry calendarEntry) {
            date.setText(LocalDateConverter.toDisplayString(calendarEntry.getLocalDate()));
        }
    }
}
