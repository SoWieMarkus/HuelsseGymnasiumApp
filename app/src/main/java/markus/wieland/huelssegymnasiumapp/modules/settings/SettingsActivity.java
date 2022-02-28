package markus.wieland.huelssegymnasiumapp.modules.settings;

import android.content.Intent;

import androidx.lifecycle.ViewModelProviders;

import markus.wieland.huelssegymnasiumapp.ui.CreateItemActivity;
import markus.wieland.huelssegymnasiumapp.MainActivity;
import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.modules.grades.database.GradeViewModel;
import markus.wieland.huelssegymnasiumapp.modules.grades.models.GradeFormat;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.course_input_widget.CourseInputWidget;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.enum_input_widget.EnumInputWidget;

public class SettingsActivity extends CreateItemActivity<String> {

    private Settings settings;
    private CourseInputWidget courseInputWidget;
    private EnumInputWidget<GradeFormat> gradeFormatEnumInputWidget;
    private GradeViewModel gradeViewModel;

    public SettingsActivity() {
        super(R.layout.activity_settings, R.string.activity_settings_title);
    }

    @Override
    public void bindViews() {
        courseInputWidget = findViewById(R.id.activity_settings_course);
        gradeFormatEnumInputWidget = findViewById(R.id.activity_settings_format);
        gradeViewModel = ViewModelProviders.of(this).get(GradeViewModel.class);
    }

    @Override
    public void initializeWidgets(String item) {
        settings = new Settings(this);
        courseInputWidget.setValue(settings.getCourse());
        courseInputWidget.setExpanded(true);


        gradeFormatEnumInputWidget.setList(GradeFormat.class.getEnumConstants());
        gradeFormatEnumInputWidget.setValue(settings.getGradeFormat());
        gradeFormatEnumInputWidget.setExpanded(true);
    }

    @Override
    public String getDefaultItem() {
        return null;
    }

    @Override
    public void setValues(String s) {
        if (gradeFormatEnumInputWidget.getValue() != settings.getGradeFormat()) {
            settings.saveGradeFormat(gradeFormatEnumInputWidget.getValue());
            gradeViewModel.deleteAll();
        }

        settings.saveCourse(courseInputWidget.getValue());
    }

    @Override
    public void check() {
        setValues(null);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void insert(String s) {
        // isn't needed because saving is done inside setValues
    }

    @Override
    public void update(String s) {
        // isn't needed because saving is done inside setValues
    }


}
