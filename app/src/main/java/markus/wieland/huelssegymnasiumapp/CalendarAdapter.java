package markus.wieland.huelssegymnasiumapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

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
        return new CalendarViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_calendar_entry, parent, false));
    }

    public class CalendarViewHolder extends DefaultViewHolder<CalendarEntry> {

        private TextView date;
        private TextView title;
        private TextView description;
        private TextView type;

        public CalendarViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bindViews() {
            date = findViewById(R.id.item_calendar_date);
            title = findViewById(R.id.item_calendar_title);
            description = findViewById(R.id.item_calendar_with_subject_description);
            type = findViewById(R.id.item_calendar_type);
        }

        @Override
        public void bindItemToViewHolder(CalendarEntry calendarEntry) {
            itemView.setAlpha(calendarEntry.isDone() ? 0.6f : 1f);
            date.setText(LocalDateConverter.toDisplayString(calendarEntry.getLocalDate(), itemView.getContext()));
            title.setText(calendarEntry.getTitle());
            description.setVisibility(calendarEntry.getDescription() == null ? View.GONE : View.VISIBLE);
            description.setText(calendarEntry.getDescription());
            type.setText(calendarEntry.getCalendarEntryType().getDisplayNameAsString(itemView.getContext()));
        }
    }
}
