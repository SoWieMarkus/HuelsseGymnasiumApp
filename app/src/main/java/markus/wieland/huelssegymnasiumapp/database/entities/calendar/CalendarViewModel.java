package markus.wieland.huelssegymnasiumapp.database.entities.calendar;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

import markus.wieland.databases.BaseViewModel;
import markus.wieland.huelssegymnasiumapp.calendar.CalendarEntry;

public class CalendarViewModel extends BaseViewModel<CalendarEntry, CalendarEntryDataAccessObject, CalendarRepository> {
    public CalendarViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected CalendarRepository initRepository() {
        return new CalendarRepository(getApplication());
    }

    public LiveData<List<CalendarEntry>> getAllCalendarEntries() {
        return getRepository().getAllCalendarEntries();
    }
}
