package markus.wieland.huelssegymnasiumapp.modules.calendar.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.defaultappelements.uielements.adapter.QueryableEntity;
import markus.wieland.huelssegymnasiumapp.helper.Filterable;
import markus.wieland.huelssegymnasiumapp.helper.Type;
import markus.wieland.huelssegymnasiumapp.modules.subjects.models.Subject;

@Getter
@Setter
public class CalendarEntryWithSubject implements QueryableEntity<Long>, Filterable {

    @Embedded
    private CalendarEntry calendarEntry;
    @Relation(parentColumn = "subjectId", entityColumn = "subjectId")
    private Subject subject;

    @Override
    public Long getId() {
        return calendarEntry.getId();
    }

    @Override
    public String getStringToApplyQuery() {
        return calendarEntry.getStringToApplyQuery();
    }

    @Override
    public Type getType() {
        return calendarEntry.getCalendarEntryType();
    }
}
