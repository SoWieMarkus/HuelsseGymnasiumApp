package markus.wieland.huelssegymnasiumapp.database.entities.calendar;

import androidx.room.Dao;

import markus.wieland.databases.BaseDataAccessObject;
import markus.wieland.huelssegymnasiumapp.calendar.CalendarEntry;

@Dao
public interface CalendarEntryDataAccessObject extends BaseDataAccessObject<CalendarEntry> {
}
