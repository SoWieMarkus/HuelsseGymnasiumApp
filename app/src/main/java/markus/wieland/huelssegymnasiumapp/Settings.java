package markus.wieland.huelssegymnasiumapp;

import android.content.Context;
import android.content.SharedPreferences;

public class Settings {

    private static final String SETTINGS_KEY = "markus.wieland.huelssegymnasiumapp.SETTINGS_KEY";

    private final SharedPreferences sharedPreferences;

    public Settings(Context context) {
        this.sharedPreferences = context.getSharedPreferences(SETTINGS_KEY, Context.MODE_PRIVATE);
    }
}
