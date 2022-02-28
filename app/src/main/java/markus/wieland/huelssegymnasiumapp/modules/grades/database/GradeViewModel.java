package markus.wieland.huelssegymnasiumapp.modules.grades.database;

import android.app.Application;

import androidx.annotation.NonNull;

import markus.wieland.databases.BaseViewModel;
import markus.wieland.huelssegymnasiumapp.modules.grades.models.Grade;

public class GradeViewModel extends BaseViewModel<Grade, GradeDataAccessObject, GradeRepository> {

    public GradeViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected GradeRepository initRepository() {
        return new GradeRepository(getApplication());
    }

    public void deleteAll() {
        getRepository().deleteAll();
    }
}
