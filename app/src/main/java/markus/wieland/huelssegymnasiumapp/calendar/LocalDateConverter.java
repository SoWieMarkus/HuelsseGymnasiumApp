package markus.wieland.huelssegymnasiumapp.calendar;

import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateConverter {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private LocalDateConverter(){}

    @TypeConverter
    public static String toString(LocalDate localDate) {
        return localDate.format(dateTimeFormatter);
    }

    @TypeConverter
    public static LocalDate toLocalDate(String time) {
        return LocalDate.parse(time, dateTimeFormatter);
    }

}
