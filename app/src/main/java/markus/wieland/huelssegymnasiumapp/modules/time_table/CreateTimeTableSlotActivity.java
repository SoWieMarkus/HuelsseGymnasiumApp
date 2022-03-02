package markus.wieland.huelssegymnasiumapp.modules.time_table;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.modules.settings.Settings;
import markus.wieland.huelssegymnasiumapp.modules.subjects.database.SubjectViewModel;
import markus.wieland.huelssegymnasiumapp.modules.subjects.models.Subject;
import markus.wieland.huelssegymnasiumapp.modules.time_table.database.TimeTableSlotViewModel;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.TimePeriod;
import markus.wieland.huelssegymnasiumapp.modules.time_table.models.TimeTableSlot;
import markus.wieland.huelssegymnasiumapp.ui.CreateItemActivity;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.subject_input_widget.SubjectInputWidget;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.time_period_input_widget.TimePeriodInputWidget;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.week_day_input_widget.WeekDayInputWidget;
import markus.wieland.huelssegymnasiumapp.ui.validator.Validation;
import markus.wieland.huelssegymnasiumapp.ui.validator.Validator;
import markus.wieland.huelssegymnasiumapp.ui.validator.string_validator.MaxLengthArgument;

public class CreateTimeTableSlotActivity extends CreateItemActivity<TimeTableSlot> implements Observer<List<Subject>> {

    private TimePeriodInputWidget timePeriodInputWidget;
    private SubjectInputWidget subjectInputWidget;
    private SubjectViewModel subjectViewModel;
    private TimeTableSlotViewModel timeTableSlotViewModel;
    private WeekDayInputWidget weekDayInputWidget;
    private Settings settings;

    private EditText room;
    private TextView subjectName;
    private LinearLayout subjectColor;
    private TextView subjectTeacher;
    private TextView subjectPeriod;

    public CreateTimeTableSlotActivity() {
        super(R.layout.activity_create_time_table, R.string.activity_create_time_table_slot_title);
    }

    @Override
    public void bindViews() {
        timePeriodInputWidget = findViewById(R.id.activity_create_time_table_slot_time_period);
        room = findViewById(R.id.activity_create_time_table_slot_subject_room);
        subjectName = findViewById(R.id.activity_create_time_table_slot_subject_name);
        subjectTeacher = findViewById(R.id.activity_create_time_table_slot_subject_teacher);
        subjectColor = findViewById(R.id.activity_create_time_table_slot_subject_color);
        subjectPeriod = findViewById(R.id.activity_create_time_table_slot_subject_period);
        subjectInputWidget = findViewById(R.id.activity_create_time_table_slot_subject);
        subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel.class);
        timeTableSlotViewModel = ViewModelProviders.of(this).get(TimeTableSlotViewModel.class);
        weekDayInputWidget = findViewById(R.id.activity_create_time_table_slot_day);
        settings = new Settings(this);
    }

    @Override
    public void execute() {
        super.execute();
        subjectViewModel.getAllSubjects().observe(this, this);
    }

    @Override
    public void initializeWidgets(TimeTableSlot item) {
        subjectInputWidget.setValueChangeListener(this::onValueChanged);
        timePeriodInputWidget.setValueChangeListener(this::onValueChanged);

        timePeriodInputWidget.setValue(item.getTimePeriod());
        weekDayInputWidget.setValue(item.getDay());
        subjectInputWidget.select(item.getSubjectId());

        timePeriodInputWidget.setExpanded(true);
        weekDayInputWidget.setExpanded(true);
        subjectInputWidget.setExpanded(!isEditMode());

        room.setText(item.getRoom());
    }

    @Override
    public TimeTableSlot getDefaultItem() {
        TimeTableSlot timeTableSlot = new TimeTableSlot();
        timeTableSlot.setDay(settings.getLastSelectedDay());
        timeTableSlot.setTimePeriod(new TimePeriod());
        timeTableSlot.setSubjectId(null);
        timeTableSlot.setRoom(null);
        return timeTableSlot;
    }

    @Override
    public List<Validation> getValidations() {
        List<Validation> validations = new ArrayList<>();
        validations.add(subjectInputWidget);
        validations.add(timePeriodInputWidget);
        if (getTextFromEditText(room) != null)
            validations.add(new Validator<>(getTextFromEditText(room))
                    .add(new MaxLengthArgument(getString(R.string.activity_create_time_table_slot_room_argument_name), 100)));
        return validations;
    }

    @Override
    public void setValues(TimeTableSlot timeTableSlot) {
        timeTableSlot.setTimePeriod(timePeriodInputWidget.getValue());
        timeTableSlot.setSubjectId(subjectInputWidget.getValue().getSubjectId());
        timeTableSlot.setDay(weekDayInputWidget.getValue());
        timeTableSlot.setRoom(getTextFromEditText(room));

        settings.saveLastSelectedDay(weekDayInputWidget.getValue());
    }

    @Override
    public void insert(TimeTableSlot timeTableSlot) {
        timeTableSlotViewModel.insert(timeTableSlot);
    }

    @Override
    public void update(TimeTableSlot timeTableSlot) {
        timeTableSlotViewModel.update(timeTableSlot);
    }

    @Override
    public void onChanged(List<Subject> subjects) {
        if (subjects.isEmpty()) {
            Toast.makeText(this, getString(R.string.activity_create_grade_error_no_subject), Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        subjectInputWidget.setList(subjects);
        subjectInputWidget.select(getItem().getSubjectId());
    }

    public void onValueChanged(Subject subject) {

        subjectColor.setBackgroundColor(subject == null ? getColor(R.color.default_subject_color) : subject.getColor());
        subjectName.setText(subject == null ? null : subject.getName());
        subjectTeacher.setText(subject == null ? null : subject.teacherToString());
    }

    public void onValueChanged(TimePeriod timePeriod) {
        subjectPeriod.setText(timePeriod.toString());
    }
}