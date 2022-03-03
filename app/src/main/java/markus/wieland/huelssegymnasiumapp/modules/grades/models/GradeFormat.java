package markus.wieland.huelssegymnasiumapp.modules.grades.models;

import android.content.Context;

import lombok.Getter;
import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.ui.filter.Type;

@Getter
public enum GradeFormat implements Type {

    NORMAL(0, 500, 0, R.string.grade_format_normal),
    ABITUR(0, 1500, 1, R.string.grade_format_abitur);

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
