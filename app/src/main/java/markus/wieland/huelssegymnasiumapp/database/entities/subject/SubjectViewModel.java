package markus.wieland.huelssegymnasiumapp.database.entities.subject;

import android.app.Application;

import androidx.annotation.NonNull;

import markus.wieland.databases.BaseViewModel;
import markus.wieland.huelssegymnasiumapp.subjects.Subject;

public class SubjectViewModel extends BaseViewModel<Subject, SubjectDataAccessObject, SubjectRepository> {

    public SubjectViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    protected SubjectRepository initRepository() {
        return new SubjectRepository(getApplication());
    }
}
