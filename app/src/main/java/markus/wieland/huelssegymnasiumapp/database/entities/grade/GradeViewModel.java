package markus.wieland.huelssegymnasiumapp.database.entities.grade;

import android.app.Application;

import androidx.annotation.NonNull;

import markus.wieland.databases.BaseViewModel;
import markus.wieland.huelssegymnasiumapp.grades.Grade;

public class GradeViewModel extends BaseViewModel<Grade, GradeDataAccessObject, GradeRepository> {

    public GradeViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected GradeRepository initRepository() {
        return new GradeRepository(getApplication());
    }
}
