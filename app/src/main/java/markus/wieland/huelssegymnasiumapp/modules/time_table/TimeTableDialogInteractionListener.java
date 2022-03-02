package markus.wieland.huelssegymnasiumapp.modules.time_table;

import markus.wieland.defaultappelements.uielements.adapter.iteractlistener.OnItemInteractListener;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.TimeTableSlotWithSubject;

public interface TimeTableDialogInteractionListener extends OnItemInteractListener<TimeTableSlotWithSubject> {

    void onDelete(TimeTableSlotWithSubject timeTableSlotWithSubject);
    void onEdit(TimeTableSlotWithSubject timeTableSlotWithSubject);
}
