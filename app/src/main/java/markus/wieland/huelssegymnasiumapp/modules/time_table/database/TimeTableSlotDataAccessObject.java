package markus.wieland.huelssegymnasiumapp.modules.time_table.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import markus.wieland.databases.BaseDataAccessObject;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.TimeTableSlot;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.TimeTableSlotWithSubject;

@Dao
public interface TimeTableSlotDataAccessObject extends BaseDataAccessObject<TimeTableSlot> {

    @Query("SELECT * FROM TimeTableSlot")
    LiveData<List<TimeTableSlotWithSubject>> getAllTimeTableSlots();

}
