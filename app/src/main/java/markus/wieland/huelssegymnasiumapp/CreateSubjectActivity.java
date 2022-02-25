package markus.wieland.huelssegymnasiumapp;

import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import markus.wieland.huelssegymnasiumapp.database.entities.subject.SubjectViewModel;
import markus.wieland.huelssegymnasiumapp.helper.validator.Validation;
import markus.wieland.huelssegymnasiumapp.subjects.Subject;
import markus.wieland.huelssegymnasiumapp.ui.ValidationResult;
import markus.wieland.huelssegymnasiumapp.ui.color_input_widget.Color;
import markus.wieland.huelssegymnasiumapp.ui.color_input_widget.ColorInputWidget;
import markus.wieland.huelssegymnasiumapp.ui.weighting_input_widget.WeightingInputWidget;

public class CreateSubjectActivity extends CreateItemActivity<Subject> {

    private WeightingInputWidget weightingInputWidget;
    private ColorInputWidget colorInputWidget;
    private SubjectViewModel subjectViewModel;

    public CreateSubjectActivity() {
        super(R.layout.activity_create_subject, R.string.input_widget_subject_title);
    }

    @Override
    public void bindViews() {
        weightingInputWidget = findViewById(R.id.activity_create_subject_weighting);
        colorInputWidget = findViewById(R.id.activity_create_subject_color);
        subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel.class);
    }

    @Override
    public Subject getDefaultItem() {
        Subject subject = new Subject();
        subject.setColorR(127);
        subject.setColorG(127);
        subject.setColorB(127);
        subject.setExamWeight(50);
        return subject;
    }

    @Override
    public void initializeWidgets(Subject item) {
        colorInputWidget.setValue(new Color(item.getColorR(), item.getColorG(), item.getColorB()));
        weightingInputWidget.setValue(item.getExamWeight());
    }

    // TODO
    @Override
    public List<Validation> getValidations() {
        return super.getValidations();
    }

    @Override
    public void setValues(Subject subject) {
        subject.setName("TEST"); //TODO
        subject.setAbbreviation("TES"); //TODO
        subject.setTeacher("TEST LEHRER"); //TODO
        Color color = colorInputWidget.getValue();

        subject.setColorR(color.getR());
        subject.setColorG(color.getG());
        subject.setColorB(color.getB());
        subject.setExamWeight(weightingInputWidget.getValue());
    }

    @Override
    public void insert(Subject subject) {
        subjectViewModel.insert(subject);
    }

    @Override
    public void update(Subject subject) {
        subjectViewModel.update(subject);
    }



}