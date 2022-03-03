package markus.wieland.huelssegymnasiumapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import markus.wieland.huelssegymnasiumapp.modules.calendar.database.CalendarEntryDataAccessObject;
import markus.wieland.huelssegymnasiumapp.modules.calendar.models.CalendarEntry;
import markus.wieland.huelssegymnasiumapp.modules.grades.database.GradeDataAccessObject;
import markus.wieland.huelssegymnasiumapp.modules.grades.models.Grade;
import markus.wieland.huelssegymnasiumapp.modules.subjects.database.SubjectDataAccessObject;
import markus.wieland.huelssegymnasiumapp.modules.subjects.models.Subject;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.TimeTableSlot;
import markus.wieland.huelssegymnasiumapp.modules.time_table.database.TimeTableSlotDataAccessObject;

@Database(entities = {
        Subject.class,
        TimeTableSlot.class,
        Grade.class,
        CalendarEntry.class},
        version = 8)
public abstract class SchoolDatabase extends RoomDatabase {

    private static SchoolDatabase instance;

    public abstract SubjectDataAccessObject getSubjectDataAccessObject();

    public abstract CalendarEntryDataAccessObject getCalendarDataAccessObject();

    public abstract TimeTableSlotDataAccessObject getTimeTableDataAccessObject();

    public abstract GradeDataAccessObject getGradeDataAccessObject();

    public static synchronized SchoolDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), SchoolDatabase.class, "school_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }


}
