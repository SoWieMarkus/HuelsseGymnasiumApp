package markus.wieland.huelssegymnasiumapp.modules.grades;

import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.modules.grades.database.GradeViewModel;
import markus.wieland.huelssegymnasiumapp.modules.grades.models.DefaultGrade;
import markus.wieland.huelssegymnasiumapp.modules.grades.models.Grade;
import markus.wieland.huelssegymnasiumapp.modules.grades.models.GradeFormat;
import markus.wieland.huelssegymnasiumapp.modules.grades.models.GradeType;
import markus.wieland.huelssegymnasiumapp.modules.grades.models.SecondaryOneGrade;
import markus.wieland.huelssegymnasiumapp.modules.grades.models.SecondaryTwoGrade;
import markus.wieland.huelssegymnasiumapp.modules.settings.Settings;
import markus.wieland.huelssegymnasiumapp.modules.subjects.database.SubjectViewModel;
import markus.wieland.huelssegymnasiumapp.modules.subjects.models.Subject;
import markus.wieland.huelssegymnasiumapp.ui.CreateItemActivity;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.calendar_input_widget.CalendarInputWidget;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.enum_input_widget.EnumInputWidget;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.grade_input_widget.GradeInputWidget;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.subject_input_widget.SubjectInputWidget;
import markus.wieland.huelssegymnasiumapp.ui.validator.Validation;

public class CreateGradeActivity extends CreateItemActivity<Grade> implements Observer<List<Subject>> {

    private GradeViewModel gradeViewModel;
    private SubjectViewModel subjectViewModel;
    private GradeInputWidget gradeInputWidget;
    private CalendarInputWidget calendarInputWidget;
    private SubjectInputWidget subjectInputWidget;
    private EnumInputWidget<GradeType> gradeTypeEnumInputWidget;
    private Settings settings;

    public CreateGradeActivity() {
        super(R.layout.activity_create_grade, R.string.activity_create_grade_title);
    }

    @Override
    public void bindViews() {
        gradeViewModel = ViewModelProviders.of(this).get(GradeViewModel.class);
        subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel.class);

        settings = new Settings(this);
        gradeTypeEnumInputWidget = findViewById(R.id.activity_create_grade_type);
        calendarInputWidget = findViewById(R.id.activity_create_grade_calendar);
        subjectInputWidget = findViewById(R.id.activity_create_grade_subject);
        initGradeInputWidget();
    }

    public void initGradeInputWidget() {
        if (settings.getGradeFormat() == GradeFormat.ABITUR) {
            gradeInputWidget = findViewById(R.id.activity_create_grade_secondary_two);
            findViewById(R.id.activity_create_grade_secondary_one).setVisibility(View.GONE);
            return;
        }
        gradeInputWidget = findViewById(R.id.activity_create_grade_secondary_one);
        findViewById(R.id.activity_create_grade_secondary_two).setVisibility(View.GONE);
    }

    @Override
    public void execute() {
        super.execute();
        subjectViewModel.getAllSubjects().observe(this, this);
    }

    // TODO move to DefaultGrade noargscontructor
    private DefaultGrade getDefaultGrade() {
        if (settings.getGradeFormat() == GradeFormat.ABITUR) {
            return new SecondaryTwoGrade(15);
        }
        return new SecondaryOneGrade(false).setUpFromDatabaseValue(11);
    }


    @Override
    public void initializeWidgets(Grade item) {
        gradeInputWidget.setValue(item.getGrade(settings.getGradeFormat()));
        gradeTypeEnumInputWidget.setList(GradeType.class.getEnumConstants());
        gradeTypeEnumInputWidget.setValue(item.getGradeType());
        calendarInputWidget.setValue(item.getLocalDate());
        gradeInputWidget.setExpanded(true);
        calendarInputWidget.setExpanded(false);
        gradeTypeEnumInputWidget.setExpanded(!isEditMode());
        subjectInputWidget.setExpanded(!isEditMode());
    }

    @Override
    public List<Validation> getValidations() {
        return new ArrayList<>(Collections.singleton(subjectInputWidget));
    }

    @Override
    public Grade getDefaultItem() {
        Grade grade = new Grade();
        grade.setGradeType(GradeType.TEST);
        grade.setValue(getDefaultGrade().getDatabaseValue());
        grade.setLocalDate(LocalDate.now());
        grade.setSubjectId(getSubjectId());
        return grade;
    }

    @Override
    public void setValues(Grade grade) {
        grade.setGradeType(gradeTypeEnumInputWidget.getValue());
        grade.setValue(gradeInputWidget.getValue().getDatabaseValue());
        grade.setLocalDate(calendarInputWidget.getValue());
        grade.setSubjectId(subjectInputWidget.getValue().getSubjectId());
    }

    @Override
    public void insert(Grade grade) {
        gradeViewModel.insert(grade);
    }

    @Override
    public void update(Grade grade) {
        gradeViewModel.update(grade);
    }

    @Override
    public void onChanged(List<Subject> subjects) {
        subjectInputWidget.setList(subjects);
        subjectInputWidget.select(getItem().getSubjectId());
        if (subjects.isEmpty()) {
            Toast.makeText(this, getString(R.string.activity_create_grade_error_no_subject), Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}