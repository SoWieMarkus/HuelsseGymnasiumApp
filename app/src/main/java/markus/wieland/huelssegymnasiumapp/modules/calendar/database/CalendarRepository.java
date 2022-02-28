package markus.wieland.huelssegymnasiumapp.database.entities.calendar;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

import markus.wieland.databases.BaseRepository;
import markus.wieland.huelssegymnasiumapp.calendar.CalendarEntry;
import markus.wieland.huelssegymnasiumapp.calendar.CalendarEntryWithSubject;
import markus.wieland.huelssegymnasiumapp.database.SchoolDatabase;

public class CalendarRepository extends BaseRepository<CalendarEntry, CalendarEntryDataAccessObject> {

    public CalendarRepository(@NonNull Application application) {
        super(application);
    }

    @Override
    public CalendarEntryDataAccessObject initDataAccessObject(@NonNull Application application) {
        return SchoolDatabase.getInstance(application).getCalendarDataAccessObject();
    }

    public LiveData<List<CalendarEntry>> getAllCalendarEntries() {
        return getDataAccessObject().getAllCalendarEntries();
    }

    public LiveData<List<CalendarEntryWithSubject>> getCalendarEntryWithSubjects() {
        return getDataAccessObject().getCalendarEntryWithSubjects();
    }



}
