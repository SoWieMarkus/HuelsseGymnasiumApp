package markus.wieland.huelssegymnasiumapp.modules.time_table.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

import markus.wieland.databases.BaseViewModel;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.TimeTableSlot;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.TimeTableSlotWithSubject;

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
