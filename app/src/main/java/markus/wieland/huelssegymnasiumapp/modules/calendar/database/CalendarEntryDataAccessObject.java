package markus.wieland.huelssegymnasiumapp.modules.calendar.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import markus.wieland.databases.BaseDataAccessObject;
import markus.wieland.huelssegymnasiumapp.modules.calendar.models.CalendarEntry;
import markus.wieland.huelssegymnasiumapp.modules.calendar.models.CalendarEntryWithSubject;

@Dao
public interface CalendarEntryDataAccessObject extends BaseDataAccessObject<CalendarEntry> {
    @Transaction
    @Query("SELECT * FROM CalendarEntry ORDER BY localDate ASC")
    LiveData<List<CalendarEntryWithSubject>> getCalendarEntryWithSubjects();
}
