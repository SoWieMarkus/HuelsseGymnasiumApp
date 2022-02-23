package markus.wieland.huelssegymnasiumapp.database.entities.grade;

import android.app.Application;

import androidx.annotation.NonNull;

import markus.wieland.databases.BaseRepository;
import markus.wieland.huelssegymnasiumapp.database.SchoolDatabase;
import markus.wieland.huelssegymnasiumapp.grades.Grade;

public class GradeRepository extends BaseRepository<Grade, GradeDataAccessObject> {
    public GradeRepository(@NonNull Application application) {
        super(application);
    }

    @Override
    public GradeDataAccessObject initDataAccessObject(@NonNull Application application) {
        return SchoolDatabase.getInstance(application).getGradeDataAccessObject();
    }
}
