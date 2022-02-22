package markus.wieland.huelssegymnasiumapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import markus.wieland.huelssegymnasiumapp.calendar.CalendarEntry;
import markus.wieland.huelssegymnasiumapp.grades.Grade;
import markus.wieland.huelssegymnasiumapp.subjects.Subject;
import markus.wieland.huelssegymnasiumapp.time_table.TimeTableSlot;


@Database(entities = {
        Subject.class,
        TimeTableSlot.class,
        Grade.class,
        CalendarEntry.class},
        version = 1)
public abstract class SchoolDatabase extends RoomDatabase {

    private static SchoolDatabase instance;

    public static synchronized SchoolDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), SchoolDatabase.class, "school_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }


}
