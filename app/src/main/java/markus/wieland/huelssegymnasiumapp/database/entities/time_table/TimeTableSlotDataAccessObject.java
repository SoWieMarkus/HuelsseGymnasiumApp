package markus.wieland.huelssegymnasiumapp.database.entities.time_table;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import markus.wieland.databases.BaseDataAccessObject;
import markus.wieland.huelssegymnasiumapp.time_table.TimeTableSlot;

@Dao
public interface TimeTableSlotDataAccessObject extends BaseDataAccessObject<TimeTableSlot> {

    @Query("SELECT * FROM TimeTableSlot WHERE week = :week")
    LiveData<List<TimeTableSlot>> getTimeTableSlotsByWeek(int week);

}
