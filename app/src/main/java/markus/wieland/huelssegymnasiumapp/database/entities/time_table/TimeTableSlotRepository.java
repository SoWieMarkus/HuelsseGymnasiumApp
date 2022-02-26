package markus.wieland.huelssegymnasiumapp.database.entities.time_table;

import android.app.Application;

import androidx.annotation.NonNull;

import markus.wieland.databases.BaseRepository;
import markus.wieland.huelssegymnasiumapp.database.SchoolDatabase;
import markus.wieland.huelssegymnasiumapp.time_table.TimeTableSlot;

public class TimeTableSlotRepository extends BaseRepository<TimeTableSlot, TimeTableSlotDataAccessObject> {

    public TimeTableSlotRepository(@NonNull Application application) {
        super(application);
    }

    @Override
    public TimeTableSlotDataAccessObject initDataAccessObject(@NonNull Application application) {
        return SchoolDatabase.getInstance(application).getTimeTableDataAccessObject();
    }
}
