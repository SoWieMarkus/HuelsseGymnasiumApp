package markus.wieland.huelssegymnasiumapp.modules.calendar.database;

import android.content.Context;

import androidx.room.TypeConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import markus.wieland.huelssegymnasiumapp.R;

public class LocalDateConverter {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private LocalDateConverter() {
    }

    @TypeConverter
    public static String toString(LocalDate localDate) {
        return localDate.format(dateTimeFormatter);
    }

    public static String toDisplayString(LocalDate localDate) {
        return localDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String toDisplayString(LocalDate localDate, Context context) {
        LocalDate now = LocalDate.now();
        if (localDate.equals(now))
            return context.getString(R.string.calendar_date_today);
        if (localDate.equals(now.plusDays(1)))
            return context.getString(R.string.calendar_date_tomorrow);
        if (localDate.equals(now.minusDays(1)))
            return context.getString(R.string.calendar_date_yesterday);
        return localDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @TypeConverter
    public static LocalDate toLocalDate(String time) {
        return LocalDate.parse(time, dateTimeFormatter);
    }

}
