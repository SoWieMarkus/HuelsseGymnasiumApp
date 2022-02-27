package markus.wieland.huelssegymnasiumapp.database.entities.time_table;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

import markus.wieland.databases.BaseViewModel;
import markus.wieland.huelssegymnasiumapp.time_table.TimeTableSlot;
import markus.wieland.huelssegymnasiumapp.time_table.TimeTableSlotWithSubject;

public class TimeTableSlotViewModel extends BaseViewModel<TimeTableSlot, TimeTableSlotDataAccessObject, TimeTableSlotRepository> {

    public TimeTableSlotViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected TimeTableSlotRepository initRepository() {
        return new TimeTableSlotRepository(getApplication());
    }

    public LiveData<List<TimeTableSlotWithSubject>> getAllTimeTableSlots() {
        return getRepository().getAllTimeTableSlots();
    }
}
