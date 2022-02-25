package markus.wieland.huelssegymnasiumapp.calendar;

import android.content.Context;

import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import markus.wieland.huelssegymnasiumapp.R;

public class LocalDateConverter {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private LocalDateConverter(){}

    @TypeConverter
    public static String toString(LocalDate localDate) {
        return localDate.format(dateTimeFormatter);
    }

    public static String toDisplayString(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String toDisplayString(LocalDate localDate, Context context) {
        if (localDate.equals(LocalDate.now())) return context.getString(R.string.calendar_date_today);
        if (localDate.equals(LocalDate.now().plusDays(1))) return context.getString(R.string.calendar_date_tomorrow);
        return localDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @TypeConverter
    public static LocalDate toLocalDate(String time) {
        return LocalDate.parse(time, dateTimeFormatter);
    }

}
