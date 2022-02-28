package markus.wieland.huelssegymnasiumapp;

import android.widget.EditText;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import markus.wieland.huelssegymnasiumapp.calendar.CalendarEntry;
import markus.wieland.huelssegymnasiumapp.calendar.CalendarEntryType;
import markus.wieland.huelssegymnasiumapp.database.entities.calendar.CalendarViewModel;
import markus.wieland.huelssegymnasiumapp.database.entities.subject.SubjectViewModel;
import markus.wieland.huelssegymnasiumapp.helper.validator.Validation;
import markus.wieland.huelssegymnasiumapp.helper.validator.Validator;
import markus.wieland.huelssegymnasiumapp.helper.validator.string_validator.MaxLengthArgument;
import markus.wieland.huelssegymnasiumapp.helper.validator.string_validator.MinLengthArgument;
import markus.wieland.huelssegymnasiumapp.helper.validator.string_validator.NotNullArgument;
import markus.wieland.huelssegymnasiumapp.subjects.Subject;
import markus.wieland.huelssegymnasiumapp.ui.calendar_input_widget.CalendarInputWidget;
import markus.wieland.huelssegymnasiumapp.ui.enum_input_widget.EnumInputWidget;
import markus.wieland.huelssegymnasiumapp.ui.subject_input_widget.SubjectInputWidget;

public class CreateCalendarEntryActivity extends CreateItemActivity<CalendarEntry> implements Observer<List<Subject>> {

    private SubjectInputWidget subjectInputWidget;
    private CalendarInputWidget calendarInputWidget;
    private EnumInputWidget<CalendarEntryType> entryTypeEnumInputWidget;
    private EditText titleEditText;
    private EditText descriptionEditText;

    private CalendarViewModel calendarViewModel;
    private SubjectViewModel subjectViewModel;

    public CreateCalendarEntryActivity() {
        super(R.layout.activity_create_calendar_entry, R.string.activity_create_calendar_entry_title);
    }

    @Override
    public void bindViews() {
        entryTypeEnumInputWidget = findViewById(R.id.activity_settings_format);
        subjectInputWidget = findViewById(R.id.activity_settings_course);
        calendarInputWidget = findViewById(R.id.activity_create_calendar_entry_calendar_widget);
        titleEditText = findViewById(R.id.activity_create_calendar_entry_title);
        descriptionEditText = findViewById(R.id.activity_create_calendar_entry_description);

        calendarViewModel = ViewModelProviders.of(this).get(CalendarViewModel.class);
        subjectViewModel = ViewModelProviders.of(this).get(SubjectViewModel.class);


    }

    @Override
    public void execute() {
        subjectViewModel.getAllSubjects().observe(this, this);
    }

    @Override
    public List<Validation> getValidations() {
        List<Validation> validations = new ArrayList<>();
        validations.add(new Validator<>(getTextFromEditText(titleEditText))
                .add(new NotNullArgument(getString(R.string.activity_create_calendar_entry_title_argument_name)))
                .add(new MinLengthArgument(getString(R.string.activity_create_calendar_entry_title_argument_name), 1))
                .add(new MaxLengthArgument(getString(R.string.activity_create_calendar_entry_title_argument_name), 100)));
        String description = getTextFromEditText(descriptionEditText);
        if (description != null)
            validations.add(new Validator<>(description).add(new MaxLengthArgument(
                    getString(R.string.activity_create_calendar_entry_description_argument_name), 1000)));
        return validations;
    }

    @Override
    public void initializeWidgets(CalendarEntry calendarEntry) {
        entryTypeEnumInputWidget.setList(CalendarEntryType.class.getEnumConstants());
        entryTypeEnumInputWidget.setValue(calendarEntry.getCalendarEntryType());

        titleEditText.setText(calendarEntry.getTitle());
        descriptionEditText.setText(calendarEntry.getDescription());

        calendarInputWidget.setValue(calendarEntry.getLocalDate());
        calendarInputWidget.setExpanded(true);
    }


    public String getTextFromEditText(EditText editText) {
        String text = editText.getText().toString().trim();
        return text.isEmpty() ? null : text;
    }

    @Override
    public CalendarEntry getDefaultItem() {
        CalendarEntry calendarEntry = new CalendarEntry();
        calendarEntry.setSubjectId(getSubjectId());
        calendarEntry.setTitle(getTextFromEditText(titleEditText));
        calendarEntry.setDescription(getTextFromEditText(descriptionEditText));
        calendarEntry.setLocalDate(LocalDate.now().plusDays(1));
        calendarEntry.setCalendarEntryType(CalendarEntryType.OTHERS);
        return calendarEntry;
    }

    @Override
    public void setValues(CalendarEntry calendarEntry) {
        calendarEntry.setCalendarEntryType(entryTypeEnumInputWidget.getValue());
        calendarEntry.setLocalDate(calendarInputWidget.getValue());

        calendarEntry.setTitle(getTextFromEditText(titleEditText));
        calendarEntry.setDescription(getTextFromEditText(descriptionEditText));

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
        subjectInputWidget.select(getItem().getSubjectId());
    }
}