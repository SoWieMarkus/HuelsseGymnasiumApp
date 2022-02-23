package markus.wieland.huelssegymnasiumapp;

import markus.wieland.huelssegymnasiumapp.database.entities.subject.SubjectViewModel;
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

    }

    @Override
    public void execute() {

    }

    @Override
    public void initializeWidgets(Subject subject) {

    }

    @Override
    public ValidationResult validate() {
        // Don't have to check color and weighting because by default they always return valid


        return new ValidationResult();
    }

    @Override
    public Subject getDefaultItem() {
        return new Subject();
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