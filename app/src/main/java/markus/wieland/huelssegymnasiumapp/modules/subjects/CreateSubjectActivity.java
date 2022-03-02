package markus.wieland.huelssegymnasiumapp.modules.subjects;

import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.ValueChangeListener;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.color_input_widget.Color;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.color_input_widget.ColorInputWidget;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.weighting_input_widget.WeightingInputWidget;
import markus.wieland.huelssegymnasiumapp.ui.validator.Validation;
import markus.wieland.huelssegymnasiumapp.modules.subjects.database.SubjectViewModel;
import markus.wieland.huelssegymnasiumapp.modules.subjects.models.Subject;
import markus.wieland.huelssegymnasiumapp.ui.CreateItemActivity;
import markus.wieland.huelssegymnasiumapp.ui.validator.Validator;
import markus.wieland.huelssegymnasiumapp.ui.validator.string_validator.MaxLengthArgument;
import markus.wieland.huelssegymnasiumapp.ui.validator.string_validator.MinLengthArgument;
import markus.wieland.huelssegymnasiumapp.ui.validator.string_validator.NotNullArgument;

public class CreateSubjectActivity extends CreateItemActivity<Subject> implements ValueChangeListener<Color> {

    private WeightingInputWidget weightingInputWidget;
    private ColorInputWidget colorInputWidget;
    private SubjectViewModel subjectViewModel;

    private LinearLayout subjectColor;
    private EditText teacher;
    private EditText name;

    public CreateSubjectActivity() {
        super(R.layout.activity_create_subject, R.string.input_widget_subject_title);
    }

    @Override
    public void bindViews() {
        weightingInputWidget = findViewById(R.id.activity_create_subject_weighting);
        colorInputWidget = findViewById(R.id.activity_create_subject_color);
        subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel.class);

        name = findViewById(R.id.activity_create_subject_name);
        teacher = findViewById(R.id.activity_create_subject_teacher);
        subjectColor = findViewById(R.id.activity_create_subject_card_color);
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
        colorInputWidget.setValueChangeListener(this);
        colorInputWidget.setValue(new Color(item.getColorR(), item.getColorG(), item.getColorB()));
        colorInputWidget.setExpanded(!isEditMode());
        weightingInputWidget.setValue(item.getExamWeight());
        teacher.setText(item.getTeacher());
        name.setText(item.getName());
    }

    @Override
    public List<Validation> getValidations() {
        List<Validation> validations = new ArrayList<>();
        validations.add(new Validator<>(getTextFromEditText(name))
                .add(new NotNullArgument(getString(R.string.activity_create_subject_name_argument_name)))
                .add(new MinLengthArgument(getString(R.string.activity_create_subject_name_argument_name), 3))
                .add(new MaxLengthArgument(getString(R.string.activity_create_subject_name_argument_name), 100)));
        String description = getTextFromEditText(teacher);
        if (description != null)
            validations.add(new Validator<>(description).add(new MaxLengthArgument(
                    getString(R.string.activity_create_subject_teacher_argument_name), 100)));
        return validations;
    }

    @Override
    public void setValues(Subject subject) {
        subject.setName(getTextFromEditText(name));
        subject.setAbbreviation(getTextFromEditText(name).substring(0, 3));
        subject.setTeacher(getTextFromEditText(teacher));
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

    @Override
    public void onValueChanged(Color color) {
        subjectColor.setBackgroundColor(color.getColor());
    }
}