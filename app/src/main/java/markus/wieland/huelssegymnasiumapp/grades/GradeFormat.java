package markus.wieland.huelssegymnasiumapp.grades;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;

import lombok.Getter;
import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.helper.Type;

@Getter
public enum GradeFormat implements Type {

    NORMAL(6, 1, 0, R.string.grade_format_normal),
    ABITUR(0, 15, 1, R.string.grade_format_abitur);

    private final int minimum;
    private final int maximum;
    private final int id;
    private final int displayName;

    GradeFormat(int minimum, int maximum, int id, int displayName) {
        this.minimum = minimum;
        this.maximum = maximum;
        this.id = id;
        this.displayName = displayName;
    }

    @Override
    public int getDisplayName() {
        return displayName;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getDisplayNameAsString(Context context) {
        return context.getString(displayName);
    }
}
