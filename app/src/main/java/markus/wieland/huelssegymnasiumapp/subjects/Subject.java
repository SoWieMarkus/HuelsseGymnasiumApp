package markus.wieland.huelssegymnasiumapp.subjects;

import android.graphics.Color;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.databases.DatabaseEntity;

@Getter
@Setter
@Entity
public class Subject implements DatabaseEntity {

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
    public int getColor(){
        return Color.rgb(colorR, colorB, colorG);
    }
}
