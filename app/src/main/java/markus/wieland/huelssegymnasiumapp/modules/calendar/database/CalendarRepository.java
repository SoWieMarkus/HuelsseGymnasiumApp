package markus.wieland.huelssegymnasiumapp.modules.calendar.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

import markus.wieland.databases.BaseRepository;
import markus.wieland.huelssegymnasiumapp.database.SchoolDatabase;
import markus.wieland.huelssegymnasiumapp.modules.calendar.models.CalendarEntry;
import markus.wieland.huelssegymnasiumapp.modules.calendar.models.CalendarEntryWithSubject;

public class CalendarRepository extends BaseRepository<CalendarEntry, CalendarEntryDataAccessObject> {

    public CalendarRepository(@NonNull Application application) {
        super(application);
    }

    @Override
    public CalendarEntryDataAccessObject initDataAccessObject(@NonNull Application application) {
        return SchoolDatabase.getInstance(application).getCalendarDataAccessObject();
    }

    public LiveData<List<CalendarEntryWithSubject>> getCalendarEntryWithSubjects() {
        return getDataAccessObject().getCalendarEntryWithSubjects();
    }


}
