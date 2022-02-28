package markus.wieland.huelssegymnasiumapp.modules.calendar.models;

import android.content.Context;

import androidx.room.TypeConverter;

import java.util.Objects;

import lombok.Getter;
import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.helper.Type;

@Getter
public enum CalendarEntryType implements Type {

    OTHERS(0, R.string.calendar_entry_type_others),
    TEST(1, R.string.calendar_entry_type_test),
    EXAM(2, R.string.calendar_entry_type_exam),
    HAND_IN(3, R.string.calendar_entry_type_hand_in),
    HOMEWORK(4, R.string.calendar_entry_type_homework);

    private final int id;
    private final int displayName;

    CalendarEntryType(int id, int displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    @TypeConverter
    public static int toInt(CalendarEntryType calendarEntryType) {
        return calendarEntryType.getId();
    }

    @TypeConverter
    public static CalendarEntryType toCalendarEntryType(int id) {
        for (CalendarEntryType calendarEntryType : Objects.requireNonNull(CalendarEntryType.class.getEnumConstants())) {
            if (calendarEntryType.getId() == id) return calendarEntryType;
        }
        return null;
    }

    @Override
    public String getDisplayNameAsString(Context context) {
        return context.getString(displayName);
    }
}
