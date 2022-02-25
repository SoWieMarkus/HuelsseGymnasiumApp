package markus.wieland.huelssegymnasiumapp;

import markus.wieland.huelssegymnasiumapp.grades.Grade;
import markus.wieland.huelssegymnasiumapp.ui.ValidationResult;

public class CreateGradeActivity extends CreateItemActivity<Grade> {

    public CreateGradeActivity() {
        super(R.layout.activity_create_grade, R.string.activity_create_grade_title);
    }

    @Override
    public void bindViews() {

    }

    @Override
    public void initializeWidgets(Grade item) {

    }

    @Override
    public Grade getDefaultItem() {
        return null;
    }

    @Override
    public void setValues(Grade grade) {

    }

    @Override
    public void insert(Grade grade) {

    }

    @Override
    public void update(Grade grade) {

    }
}