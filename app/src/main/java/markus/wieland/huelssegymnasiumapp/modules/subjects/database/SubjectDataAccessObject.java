package markus.wieland.huelssegymnasiumapp.modules.subjects.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import markus.wieland.databases.BaseDataAccessObject;
import markus.wieland.huelssegymnasiumapp.modules.subjects.models.Subject;
import markus.wieland.huelssegymnasiumapp.modules.subjects.models.SubjectWithGradesAndCalendar;

@Dao
public interface SubjectDataAccessObject extends BaseDataAccessObject<Subject> {

    @Query("SELECT * FROM Subject ORDER BY name ASC")
    LiveData<List<Subject>> getAllSubjects();

    @Transaction
    @Query("SELECT * FROM Subject WHERE subjectId = :subjectId")
    LiveData<SubjectWithGradesAndCalendar> getSubjectWithGradesAndEvents(long subjectId);

    @Transaction
    @Query("SELECT * FROM Subject ORDER BY name ASC")
    LiveData<List<SubjectWithGradesAndCalendar>> getAllSubjectsWithGradesAndEvents();


}
