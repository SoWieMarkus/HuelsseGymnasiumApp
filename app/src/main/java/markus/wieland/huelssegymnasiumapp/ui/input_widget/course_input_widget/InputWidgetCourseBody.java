package markus.wieland.huelssegymnasiumapp.ui.input_widget.course_input_widget;

import android.content.Context;
import android.widget.NumberPicker;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.InputWidgetBody;

public class InputWidgetCourseBody extends InputWidgetBody<String> implements NumberPicker.OnValueChangeListener {

    private NumberPicker numberPicker;
    private static final char[] courseLetters = new char[]{'a', 'b', 'c', 'd', 'e'};

    private static final int LOWEST_COURSE_SECONDARY_1 = 5;
    private static final int HIGHEST_COURSE_SECONDARY_1 = 10;
    private static final int AMOUNT_COURSES_LEVEL_SECONDARY_1 = 6;
    private static final int AMOUNT_COURSES_LEVEL_SECONDARY_2 = 2;
    private static final int AMOUNT_COURSES = AMOUNT_COURSES_LEVEL_SECONDARY_1 * courseLetters.length + AMOUNT_COURSES_LEVEL_SECONDARY_2;
    private String[] courses;

    public InputWidgetCourseBody(Context context) {
        super(context);
    }

    @Override
    protected void initialize() {
        super.initialize();
        int index = 0;
        courses = new String[AMOUNT_COURSES];
        for (int i = LOWEST_COURSE_SECONDARY_1; i <= HIGHEST_COURSE_SECONDARY_1; i++) {
            for (char name : courseLetters) {
                courses[index] = String.valueOf(i) + name;
                index++;
            }
        }

        courses[AMOUNT_COURSES - 2] = "11";
        courses[AMOUNT_COURSES - 1] = "12";

        numberPicker = findViewById(R.id.input_widget_number_picker_body_number_picker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(AMOUNT_COURSES - 1);
        numberPicker.setDisplayedValues(courses);
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setOnValueChangedListener(this);
    }

    @Override
    public void onValueSet(String s) {
        if (s == null) {
            numberPicker.setValue(0);
            return;
        }
        for (int i = 0; i < courses.length; i++) {
            if (courses[i].equals(s)) {
                numberPicker.setValue(i);
                return;
            }
        }
        throw new IllegalStateException("No class found with the name: " + s);
    }

    @Override
    public String getValue() {
        return courses[numberPicker.getValue()];
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_input_widget_number_picker_body;
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int i, int i1) {
        getValueChangeListener().onValueChanged(getValue());
    }
}
