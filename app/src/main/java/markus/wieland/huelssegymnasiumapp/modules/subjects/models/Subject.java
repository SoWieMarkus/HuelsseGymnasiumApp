package markus.wieland.huelssegymnasiumapp.modules.subjects.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.databases.DatabaseEntity;
import markus.wieland.defaultappelements.uielements.adapter.QueryableEntity;
import markus.wieland.huelssegymnasiumapp.ui.input_widget.color_input_widget.Color;

@Getter
@Setter
@Entity
public class Subject implements DatabaseEntity, QueryableEntity<Long>, Serializable {

    @Ignore
    public static final long NO_SUBJECT_ID = -1;

    @PrimaryKey(autoGenerate = true)
    private long subjectId;

    private String name;
    private String teacher;
    private String abbreviation;

    private int colorR;
    private int colorG;
    private int colorB;

    private int examWeight;

    @Ignore
    @Override
    public long getUniqueId() {
        return getSubjectId();
    }

    @Ignore
    public int getColor() {
        return new Color(getColorR(),getColorG(), getColorB()).getColorValue();
    }

    @Ignore
    public void setColor(Color color) {
        setColorR(color.getR());
        setColorG(color.getG());
        setColorB(color.getB());
    }

    @Override
    public Long getId() {
        return getSubjectId();
    }

    @Override
    public String getStringToApplyQuery() {
        return getName() + getAbbreviation();
    }

    @Ignore
    public String teacherToString() {
        return getTeacher() == null ? "-" : getTeacher();
    }

}
