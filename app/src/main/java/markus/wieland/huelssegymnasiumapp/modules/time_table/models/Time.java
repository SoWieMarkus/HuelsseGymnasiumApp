package markus.wieland.huelssegymnasiumapp.modules.time_table.models;

import androidx.annotation.NonNull;

import java.util.Locale;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Time {

    public static final int MINUTES_PER_HOUR = 60;
    public static final int MAX_HOUR = 24;

    private int hour;
    private int minute;

    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "%02d", hour) + ":" + String.format(Locale.getDefault(), "%02d", minute);
    }

    public boolean isBefore(Time time) {
        if (time.equals(this)) return true;
        if (hour == time.getHour()) return minute < time.getMinute();
        return hour < time.getHour();
    }

    public boolean isAfter(Time time) {
        if (time.equals(this)) return true;
        if (hour == time.getHour()) return minute > time.getMinute();
        return hour > time.getHour();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return hour == time.hour && minute == time.minute;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hour, minute);
    }
}
