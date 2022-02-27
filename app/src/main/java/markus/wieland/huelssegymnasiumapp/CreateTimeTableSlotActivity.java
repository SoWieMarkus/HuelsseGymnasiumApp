package markus.wieland.huelssegymnasiumapp;

import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.ArrayList;
import java.util.List;

import markus.wieland.huelssegymnasiumapp.database.entities.subject.SubjectViewModel;
import markus.wieland.huelssegymnasiumapp.database.entities.time_table.TimeTableSlotViewModel;
import markus.wieland.huelssegymnasiumapp.helper.validator.Validation;
import markus.wieland.huelssegymnasiumapp.subjects.Subject;
import markus.wieland.huelssegymnasiumapp.time_table.TimePeriod;
import markus.wieland.huelssegymnasiumapp.time_table.TimeTableSlot;
import markus.wieland.huelssegymnasiumapp.ui.subject_input_widget.SubjectInputWidget;
import markus.wieland.huelssegymnasiumapp.ui.time_table_slot_input_widget.TimePeriodInputWidget;
import markus.wieland.huelssegymnasiumapp.ui.week_day_input_widget.WeekDayInputWidget;

public class CreateTimeTableSlotActivity extends CreateItemActivity<TimeTableSlot> implements Observer<List<Subject>> {

    private TimePeriodInputWidget timePeriodInputWidget;
    private SubjectInputWidget subjectInputWidget;
    private SubjectViewModel subjectViewModel;
    private TimeTableSlotViewModel timeTableSlotViewModel;
    private WeekDayInputWidget weekDayInputWidget;

    public CreateTimeTableSlotActivity() {
        super(R.layout.activity_create_time_table, R.string.activity_create_time_table_slot_title);
    }

    @Override
    public void bindViews() {
        timePeriodInputWidget = findViewById(R.id.activity_create_time_table_slot_time_period);
        subjectInputWidget = findViewById(R.id.activity_create_time_table_slot_subject);
        subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel.class);
        timeTableSlotViewModel = ViewModelProviders.of(this).get(TimeTableSlotViewModel.class);
        weekDayInputWidget = findViewById(R.id.activity_create_time_table_slot_day);
    }

    @Override
    public void execute() {
        super.execute();
        subjectViewModel.getAllSubjects().observe(this, this);
    }

    @Override
    public void initializeWidgets(TimeTableSlot item) {
        timePeriodInputWidget.setValue(item.getTimePeriod());
        weekDayInputWidget.setValue(item.getDay());
        subjectInputWidget.select(item.getSubjectId());

    }

    @Override
    public TimeTableSlot getDefaultItem() {
        TimeTableSlot timeTableSlot = new TimeTableSlot();
        timeTableSlot.setDay(1);
        timeTableSlot.setTimePeriod(new TimePeriod());
        timeTableSlot.setSubjectId(null);
        return timeTableSlot;
    }

    @Override
    public List<Validation> getValidations() {
        List<Validation> validations = new ArrayList<>();
        validations.add(subjectInputWidget);
        validations.add(timePeriodInputWidget);
        return validations;
    }

    @Override
    public void setValues(TimeTableSlot timeTableSlot) {
        timeTableSlot.setTimePeriod(timePeriodInputWidget.getValue());
        timeTableSlot.setSubjectId(subjectInputWidget.getValue().getSubjectId());
        timeTableSlot.setDay(weekDayInputWidget.getValue());
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
}