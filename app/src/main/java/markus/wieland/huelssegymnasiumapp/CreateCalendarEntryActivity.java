package markus.wieland.huelssegymnasiumapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import java.time.LocalDate;
import java.util.List;

import markus.wieland.huelssegymnasiumapp.calendar.CalendarEntry;
import markus.wieland.huelssegymnasiumapp.calendar.CalendarEntryType;
import markus.wieland.huelssegymnasiumapp.database.entities.calendar.CalendarViewModel;
import markus.wieland.huelssegymnasiumapp.database.entities.subject.SubjectViewModel;
import markus.wieland.huelssegymnasiumapp.grades.GradeType;
import markus.wieland.huelssegymnasiumapp.subjects.Subject;
import markus.wieland.huelssegymnasiumapp.ui.ValidationResult;
import markus.wieland.huelssegymnasiumapp.ui.calendar_input_widget.CalendarInputWidget;
import markus.wieland.huelssegymnasiumapp.ui.enum_input_widget.EnumInputWidget;
import markus.wieland.huelssegymnasiumapp.ui.subject_input_widget.SubjectInputWidget;

public class CreateCalendarEntryActivity extends CreateItemActivity<CalendarEntry> implements Observer<List<Subject>> {

    // TODO hide subjects if there aren't any

    private SubjectInputWidget subjectInputWidget;
    private CalendarInputWidget calendarInputWidget;
    private EnumInputWidget<CalendarEntryType> entryTypeEnumInputWidget;

    private CalendarViewModel calendarViewModel;
    private SubjectViewModel subjectViewModel;

    public CreateCalendarEntryActivity() {
        super(R.layout.activity_create_calendar_entry, R.string.activity_create_calendar_entry_title);
    }

    @Override
    public void bindViews() {
        entryTypeEnumInputWidget = findViewById(R.id.activity_create_calendar_entry_enum_widget);
        subjectInputWidget = findViewById(R.id.activity_create_calendar_entry_subject_widget);
        calendarInputWidget = findViewById(R.id.activity_create_calendar_entry_calendar_widget);

        calendarViewModel = ViewModelProviders.of(this).get(CalendarViewModel.class);
        subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel.class);
    }

    @Override
    public void execute() {
        subjectViewModel.getAllSubjects().observe(this, this);
    }

    @Override
    public void initializeWidgets(CalendarEntry calendarEntry) {
        entryTypeEnumInputWidget.setList(CalendarEntryType.class.getEnumConstants());
        entryTypeEnumInputWidget.setValue(calendarEntry.getCalendarEntryType());
        // TODO subjectInputWidget.setValue(cal)
        calendarInputWidget.setValue(calendarEntry.getLocalDate());
        calendarInputWidget.setExpanded(true);
    }

    @Override
    public ValidationResult validate() {
        // ignore SubjectCheck because it can be null
        return new ValidationResult();
    }

    @Override
    public CalendarEntry getDefaultItem() {
        CalendarEntry calendarEntry = new CalendarEntry();
        calendarEntry.setSubjectId(null);
        calendarEntry.setLocalDate(LocalDate.now().plusDays(1));
        calendarEntry.setCalendarEntryType(CalendarEntryType.OTHERS);
        return calendarEntry;
    }

    @Override
    public void setValues(CalendarEntry calendarEntry) {
        calendarEntry.setCalendarEntryType(entryTypeEnumInputWidget.getValue());
        calendarEntry.setLocalDate(calendarInputWidget.getValue());
        calendarEntry.setTitle("TEST");

        Subject subject = subjectInputWidget.getValue();
        calendarEntry.setSubjectId(subject == null ? null : subject.getSubjectId());


    }

    @Override
    public void insert(CalendarEntry calendarEntry) {
        calendarViewModel.insert(calendarEntry);
    }

    @Override
    public void update(CalendarEntry calendarEntry) {
        calendarViewModel.update(calendarEntry);
    }

    @Override
    public void onChanged(List<Subject> subjects) {
        subjectInputWidget.setList(subjects);
    }
}