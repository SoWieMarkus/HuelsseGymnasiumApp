package markus.wieland.huelssegymnasiumapp.database.entities.calendar;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import markus.wieland.databases.BaseDataAccessObject;
import markus.wieland.huelssegymnasiumapp.calendar.CalendarEntry;
import markus.wieland.huelssegymnasiumapp.calendar.CalendarEntryWithSubject;

@Dao
public interface CalendarEntryDataAccessObject extends BaseDataAccessObject<CalendarEntry> {

    @Query("SELECT * FROM CalendarEntry ORDER BY localDate ASC")
    LiveData<List<CalendarEntry>> getAllCalendarEntries();

    @Transaction
    @Query("SELECT * FROM CalendarEntry ORDER BY localDate ASC")
    LiveData<List<CalendarEntryWithSubject>> getCalendarEntryWithSubjects();
}
