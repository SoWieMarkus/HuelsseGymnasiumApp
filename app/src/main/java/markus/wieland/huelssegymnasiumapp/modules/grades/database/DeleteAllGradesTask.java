package markus.wieland.huelssegymnasiumapp.modules.grades.database;

import android.os.AsyncTask;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class DeleteAllGradesTask extends AsyncTask<Void, Void, Void> {

    private final GradeDataAccessObject gradeDataAccessObject;

    @Override
    protected Void doInBackground(Void... voids) {
        gradeDataAccessObject.deleteAll();
        return null;
    }
}
