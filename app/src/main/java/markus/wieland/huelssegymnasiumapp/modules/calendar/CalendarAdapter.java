package markus.wieland.huelssegymnasiumapp.modules.calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;

import markus.wieland.defaultappelements.uielements.adapter.DefaultViewHolder;
import markus.wieland.defaultappelements.uielements.adapter.QueryableAdapter;
import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.modules.calendar.database.LocalDateConverter;
import markus.wieland.huelssegymnasiumapp.modules.calendar.models.CalendarEntry;
import markus.wieland.huelssegymnasiumapp.ui.ContextMenu;
import markus.wieland.huelssegymnasiumapp.ui.OnCalendarContextMenu;

public class CalendarAdapter extends QueryableAdapter<Long, CalendarEntry, CalendarAdapter.CalendarViewHolder> {

    public CalendarAdapter(OnCalendarContextMenu<CalendarEntry> onItemInteractListener) {
        super(onItemInteractListener);
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CalendarViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_calendar_entry, parent, false));
    }

    @Override
    public OnCalendarContextMenu<CalendarEntry> getOnItemInteractListener() {
        return (OnCalendarContextMenu<CalendarEntry>) super.getOnItemInteractListener();
    }

    public class CalendarViewHolder extends DefaultViewHolder<CalendarEntry> {

        private TextView date;
        private TextView title;
        private TextView description;
        private TextView type;
        private CheckBox done;

        public CalendarViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bindViews() {
            date = findViewById(R.id.item_calendar_date);
            title = findViewById(R.id.item_calendar_title);
            description = findViewById(R.id.item_calendar_with_subject_description);
            type = findViewById(R.id.item_calendar_type);
            done = findViewById(R.id.item_calendar_check_box);
        }

        @Override
        public void bindItemToViewHolder(CalendarEntry calendarEntry) {
            itemView.setAlpha(calendarEntry.isDone() ? 0.6f : 1f);
            itemView.setOnCreateContextMenuListener(new ContextMenu<>(getOnItemInteractListener(), calendarEntry));
            itemView.setOnClickListener(view -> getOnItemInteractListener().onClick(calendarEntry));
            done.setOnCheckedChangeListener(null);
            done.setChecked(calendarEntry.isDone());
            done.setOnCheckedChangeListener((compoundButton, b) -> {
                calendarEntry.setDone(b);
                getOnItemInteractListener().onDone(calendarEntry);
            });
            date.setText(LocalDateConverter.toDisplayString(calendarEntry.getLocalDate(), itemView.getContext()));
            title.setText(calendarEntry.getTitle());
            description.setVisibility(calendarEntry.getDescription() == null ? View.GONE : View.VISIBLE);
            description.setText(calendarEntry.getDescription());
            type.setText(calendarEntry.getCalendarEntryType().getDisplayNameAsString(itemView.getContext()));
        }
    }
}
