package markus.wieland.huelssegymnasiumapp.modules.grades.database;

import android.app.Application;

import androidx.annotation.NonNull;

import markus.wieland.databases.BaseRepository;
import markus.wieland.huelssegymnasiumapp.database.SchoolDatabase;
import markus.wieland.huelssegymnasiumapp.modules.grades.models.Grade;

public class GradeRepository extends BaseRepository<Grade, GradeDataAccessObject> {
    public GradeRepository(@NonNull Application application) {
        super(application);
    }

    @Override
    public GradeDataAccessObject initDataAccessObject(@NonNull Application application) {
        return SchoolDatabase.getInstance(application).getGradeDataAccessObject();
    }

    public void deleteAll() {
        new DeleteAllGradesTask(getDataAccessObject()).execute();
    }
}
