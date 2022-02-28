package markus.wieland.huelssegymnasiumapp.modules.subjects.database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.List;

import markus.wieland.databases.BaseViewModel;
import markus.wieland.huelssegymnasiumapp.modules.subjects.models.Subject;
import markus.wieland.huelssegymnasiumapp.modules.subjects.models.SubjectWithGradesAndCalendar;

public class SubjectViewModel extends BaseViewModel<Subject, SubjectDataAccessObject, SubjectRepository> {

    public SubjectViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected SubjectRepository initRepository() {
        return new SubjectRepository(getApplication());
    }

    public LiveData<List<Subject>> getAllSubjects() {
        return getRepository().getAllSubjects();
    }

    public LiveData<SubjectWithGradesAndCalendar> getSubjectWithGradesAndEvents(long subjectId) {
        return getRepository().getSubjectWithGradesAndEvents(subjectId);
    }

    public LiveData<List<SubjectWithGradesAndCalendar>> getAllSubjectsWithGradesAndEvents() {
        return getRepository().getAllSubjectsWithGradesAndEvents();
    }
}
