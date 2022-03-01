package markus.wieland.huelssegymnasiumapp.modules.time_table.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class TimeTableDay {

    private final List<TimeTableColumn> columns;
    private final int day;

    public TimeTableDay(int day) {
        this.columns = new ArrayList<>();
        this.columns.add(new TimeTableColumn());
        this.day = day;
    }

    public TimeTableDay add(TimeTableSlotWithSubject timeTableSlotWithSubject) {
        for (TimeTableColumn column : columns) {
            if (column.add(timeTableSlotWithSubject)) return this;
        }
        TimeTableColumn timeTableColumn = new TimeTableColumn();
        if (!timeTableColumn.add(timeTableSlotWithSubject)) throw new IllegalStateException();
        this.columns.add(timeTableColumn);
        return this;
    }

}
