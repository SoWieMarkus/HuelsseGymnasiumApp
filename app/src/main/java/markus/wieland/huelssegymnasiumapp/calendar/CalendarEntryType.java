package markus.wieland.huelssegymnasiumapp.calendar;

import androidx.room.TypeConverter;

import java.util.Objects;

import lombok.Getter;

@Getter
public enum CalendarEntryType {

    OTHERS(0),
    TEST(1),
    EXAM(2),
    HAND_IN(3),
    HOMEWORK(4);

    private final int id;

    CalendarEntryType(int id) {
        this.id = id;
    }

    @TypeConverter
    public static int toInt(CalendarEntryType calendarEntryType){
        return calendarEntryType.getId();
    }

    @TypeConverter
    public static CalendarEntryType toCalendarEntryType(int id){
        for (CalendarEntryType calendarEntryType : Objects.requireNonNull(CalendarEntryType.class.getEnumConstants())) {
            if (calendarEntryType.getId() == id) return calendarEntryType;
        }
        return null;
    }

}
