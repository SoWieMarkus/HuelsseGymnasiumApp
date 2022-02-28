package markus.wieland.huelssegymnasiumapp.time_table;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lombok.Getter;

@Getter
public class TimeTableColumn implements Iterable<TimeTableSlotWithSubject> {

    private final List<TimeTableSlotWithSubject> slots;

    public TimeTableColumn() {
        this.slots = new ArrayList<>();
    }

    private boolean canBeAdded(TimeTableSlotWithSubject timeTableSlotWithSubject) {
        for (TimeTableSlotWithSubject timeTableSlot : slots) {
            if (timeTableSlotWithSubject
                    .getTimeTableSlot()
                    .getTimePeriod()
                    .overlaps(timeTableSlot
                            .getTimeTableSlot()
                            .getTimePeriod())) {
                return false;
            }
        }
        return true;
    }

    public boolean add(TimeTableSlotWithSubject timeTableSlotWithSubject) {
        boolean canBeAdded = canBeAdded(timeTableSlotWithSubject);
        if (canBeAdded)
            this.slots.add(timeTableSlotWithSubject);
        return canBeAdded;
    }

    @NonNull
    @Override
    public Iterator<TimeTableSlotWithSubject> iterator() {
        return slots.iterator();
    }
}
