package markus.wieland.huelssegymnasiumapp.modules.settings;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import markus.wieland.huelssegymnasiumapp.modules.grades.models.GradeFormat;

public class Settings {

    private static final String SETTINGS_KEY = "markus.wieland.huelssegymnasiumapp.SETTINGS_KEY";
    private static final String GRADE_FORMAT_KEY = "markus.wieland.huelssegymnasiumapp.GRADE_FORMAT_KEY";
    private static final String COURSE_KEY = "markus.wieland.huelssegymnasiumapp.COURSE_KEY";
    private static final String LAST_DAY_KEY = "markus.wieland.huelssegymnasiumapp.LAST_SELECTED_DAY_KEY";

    private final SharedPreferences sharedPreferences;
    private final Gson gson;

    public Settings(Context context) {
        gson = new Gson();
        this.sharedPreferences = context.getSharedPreferences(SETTINGS_KEY, Context.MODE_PRIVATE);
    }

    public boolean doesExist() {
        return getGradeFormat() != null;
    }

    public GradeFormat getGradeFormat() {
        return gson.fromJson(sharedPreferences.getString(GRADE_FORMAT_KEY, null), GradeFormat.class);
    }

    public int getLastSelectedDay() {
        return sharedPreferences.getInt(LAST_DAY_KEY, 0);
    }

    public void saveLastSelectedDay(int selectedDay) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(LAST_DAY_KEY, selectedDay);
        editor.apply();
    }

    public String getCourse() {
        return sharedPreferences.getString(COURSE_KEY, null);
    }

    public void saveGradeFormat(GradeFormat gradeFormat) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(GRADE_FORMAT_KEY, gson.toJson(gradeFormat));
        editor.apply();
    }

    public void saveCourse(String course) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(COURSE_KEY, course);
        editor.apply();
    }


}
