package markus.wieland.huelssegymnasiumapp.modules.time_table;

import androidx.annotation.NonNull;

import java.util.Locale;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Time {

    private int hour;
    private int minute;

    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "%02d", hour) + ":" + String.format(Locale.getDefault(), "%02d", minute);
    }

    public boolean isBefore(Time time) {
        if (hour == time.getHour()) return minute < time.getMinute();
        return hour < time.getHour();
    }

    public boolean isAfter(Time time) {
        if (hour == time.getHour()) return minute > time.getMinute();
        return hour > time.getHour();
    }
}
