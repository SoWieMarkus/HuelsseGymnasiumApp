package markus.wieland.huelssegymnasiumapp.modules.calendar;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.time.LocalDate;

import markus.wieland.defaultappelements.uielements.adapter.DefaultViewHolder;
import markus.wieland.defaultappelements.uielements.adapter.QueryableAdapter;
import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.modules.calendar.database.LocalDateConverter;
import markus.wieland.huelssegymnasiumapp.modules.calendar.models.CalendarEntry;
import markus.wieland.huelssegymnasiumapp.modules.calendar.models.CalendarEntryWithSubject;
import markus.wieland.huelssegymnasiumapp.modules.subjects.models.Subject;
import markus.wieland.huelssegymnasiumapp.ui.ContextMenu;
import markus.wieland.huelssegymnasiumapp.ui.OnCalendarContextMenu;

public class CalendarWithSubjectAdapter extends QueryableAdapter<Long, CalendarEntryWithSubject, CalendarWithSubjectAdapter.CalendarViewHolder> {

    public CalendarWithSubjectAdapter(OnCalendarContextMenu<CalendarEntryWithSubject> onContextMenuListener) {
        super(onContextMenuListener);
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CalendarViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendar_entry_with_subject, parent, false));
    }

    @Override
    public OnCalendarContextMenu<CalendarEntryWithSubject> getOnItemInteractListener() {
        return (OnCalendarContextMenu<CalendarEntryWithSubject>) super.getOnItemInteractListener();
    }

    public class CalendarViewHolder extends DefaultViewHolder<CalendarEntryWithSubject> {

        private TextView date;
        private TextView type;
        private TextView subjectName;
        private TextView title;
        private TextView description;
        private CheckBox done;
        private LinearLayout color;

        public CalendarViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bindViews() {
            date = findViewById(R.id.item_calendar_with_subject_date);
            type = findViewById(R.id.item_calendar_with_subject_type);
            subjectName = findViewById(R.id.item_calendar_with_subject_subject);
            title = findViewById(R.id.item_calendar_with_subject_title);
            description = findViewById(R.id.item_calendar_with_subject_description);
            color = findViewById(R.id.item_calendar_with_subject_color);
            done = findViewById(R.id.item_calendar_with_subject_check_box);
        }

        @Override
        public void bindItemToViewHolder(CalendarEntryWithSubject calendarEntryWithSubject) {

            CalendarEntry calendarEntry = calendarEntryWithSubject.getCalendarEntry();
            Subject subject = calendarEntryWithSubject.getSubject();
            itemView.setOnClickListener(view -> getOnItemInteractListener().onClick(calendarEntryWithSubject));
            itemView.setOnCreateContextMenuListener(new ContextMenu<>(getOnItemInteractListener(), calendarEntryWithSubject));
            done.setOnCheckedChangeListener(null);
            done.setChecked(calendarEntryWithSubject.getCalendarEntry().isDone());
            done.setOnCheckedChangeListener((compoundButton, b) -> {
                calendarEntryWithSubject.getCalendarEntry().setDone(b);
                getOnItemInteractListener().onDone(calendarEntryWithSubject);
            });

            date.setText(LocalDateConverter.toDisplayString(calendarEntry.getLocalDate(), itemView.getContext()));
            date.setTextColor(getColor(calendarEntry.getLocalDate()));
            description.setVisibility(calendarEntry.getDescription() == null ? View.GONE : View.VISIBLE);
            description.setText(calendarEntry.getDescription());
            type.setText(calendarEntry.getCalendarEntryType().getDisplayName());
            title.setText(calendarEntry.getTitle());
            subjectName.setText(subject == null ? "-" : subject.getName());
            color.setBackgroundColor(subject == null
                    ? itemView.getContext().getColor(R.color.default_subject_color)
                    : subject.getColor());
        }

        private int getColor(LocalDate localDate) {
            LocalDate now = LocalDate.now();
            if (localDate.isBefore(now.plusDays(1))) return Color.parseColor("#ffcc0000");
            if (localDate.isBefore(now.plusDays(3))) return Color.YELLOW;
            return Color.GREEN;
        }
    }
}
