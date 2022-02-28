package markus.wieland.huelssegymnasiumapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import markus.wieland.huelssegymnasiumapp.calendar.CalendarEntry;
import markus.wieland.huelssegymnasiumapp.database.entities.calendar.CalendarEntryDataAccessObject;
import markus.wieland.huelssegymnasiumapp.database.entities.grade.GradeDataAccessObject;
import markus.wieland.huelssegymnasiumapp.database.entities.subject.SubjectDataAccessObject;
import markus.wieland.huelssegymnasiumapp.database.entities.time_table.TimeTableSlotDataAccessObject;
import markus.wieland.huelssegymnasiumapp.grades.Grade;
import markus.wieland.huelssegymnasiumapp.subjects.Subject;
import markus.wieland.huelssegymnasiumapp.time_table.TimeTableSlot;


@Database(entities = {
        Subject.class,
        TimeTableSlot.class,
        Grade.class,
        CalendarEntry.class},
        version = 5)
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
