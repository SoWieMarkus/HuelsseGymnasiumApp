package markus.wieland.huelssegymnasiumapp.database.entities.calendar;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import markus.wieland.databases.BaseDataAccessObject;
import markus.wieland.huelssegymnasiumapp.calendar.CalendarEntry;
import markus.wieland.huelssegymnasiumapp.subjects.Subject;

@Dao
public interface CalendarEntryDataAccessObject extends BaseDataAccessObject<CalendarEntry> {

    @Query("SELECT * FROM CalendarEntry ORDER BY localDate ASC")
    LiveData<List<CalendarEntry>> getAllCalendarEntries();
}
