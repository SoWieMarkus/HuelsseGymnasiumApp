package markus.wieland.huelssegymnasiumapp.modules.subjects.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

import markus.wieland.databases.BaseRepository;
import markus.wieland.huelssegymnasiumapp.database.SchoolDatabase;
import markus.wieland.huelssegymnasiumapp.modules.subjects.models.Subject;
import markus.wieland.huelssegymnasiumapp.modules.subjects.models.SubjectWithGradesAndCalendar;

public class SubjectRepository extends BaseRepository<Subject, SubjectDataAccessObject> {
    public SubjectRepository(@NonNull Application application) {
        super(application);
    }

    @Override
    public SubjectDataAccessObject initDataAccessObject(@NonNull Application application) {
        return SchoolDatabase.getInstance(application).getSubjectDataAccessObject();
    }

    public LiveData<List<Subject>> getAllSubjects() {
        return getDataAccessObject().getAllSubjects();
    }

    public LiveData<SubjectWithGradesAndCalendar> getSubjectWithGradesAndEvents(long subjectId) {
        return getDataAccessObject().getSubjectWithGradesAndEvents(subjectId);
    }

    public LiveData<List<SubjectWithGradesAndCalendar>> getAllSubjectsWithGradesAndEvents() {
        return getDataAccessObject().getAllSubjectsWithGradesAndEvents();
    }
}
