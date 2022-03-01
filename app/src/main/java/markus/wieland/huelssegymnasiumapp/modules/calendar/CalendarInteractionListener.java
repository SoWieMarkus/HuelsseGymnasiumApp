package markus.wieland.huelssegymnasiumapp.modules.calendar;

import android.app.Activity;
import android.content.Intent;

import lombok.AllArgsConstructor;
import markus.wieland.huelssegymnasiumapp.modules.calendar.database.CalendarViewModel;
import markus.wieland.huelssegymnasiumapp.modules.calendar.models.CalendarEntryWithSubject;
import markus.wieland.huelssegymnasiumapp.ui.CreateItemActivity;
import markus.wieland.huelssegymnasiumapp.ui.OnCalendarContextMenu;

@AllArgsConstructor
public class CalendarInteractionListener implements OnCalendarContextMenu<CalendarEntryWithSubject> {

    private final Activity context;
    private final CalendarViewModel calendarViewModel;

    @Override
    public void onDone(CalendarEntryWithSubject calendarEntryWithSubject) {
        calendarViewModel.delete(calendarEntryWithSubject.getCalendarEntry());
    }

    @Override
    public void onEdit(CalendarEntryWithSubject calendarEntryWithSubject) {
        if (context == null) return;
        context.startActivity(new Intent(context, CreateCalendarEntryActivity.class).putExtra(
                CreateItemActivity.OBJECT_TO_EDIT, calendarEntryWithSubject.getCalendarEntry()));
    }

    @Override
    public void onDelete(CalendarEntryWithSubject calendarEntryWithSubject) {
        calendarViewModel.delete(calendarEntryWithSubject.getCalendarEntry());
    }

    @Override
    public void onClick(CalendarEntryWithSubject calendarEntryWithSubject) {
        onEdit(calendarEntryWithSubject);
    }
}
