package markus.wieland.huelssegymnasiumapp.subjects;

import androidx.room.Embedded;
import androidx.room.Ignore;
import androidx.room.Relation;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.defaultappelements.uielements.adapter.QueryableEntity;
import markus.wieland.defaultappelements.uielements.fragments.DefaultFragment;
import markus.wieland.huelssegymnasiumapp.calendar.CalendarEntry;
import markus.wieland.huelssegymnasiumapp.grades.Grade;
import markus.wieland.huelssegymnasiumapp.grades.GradeFormat;
import markus.wieland.huelssegymnasiumapp.grades.transformer.DefaultGrade;
import markus.wieland.huelssegymnasiumapp.grades.transformer.SecondaryOneGrade;
import markus.wieland.huelssegymnasiumapp.grades.transformer.SecondaryTwoGrade;

@Getter
@Setter
public class SubjectWithGradesAndCalendar implements QueryableEntity<Long> {

    @Embedded
    private Subject subject;

    @Relation(parentColumn = "subjectId", entityColumn = "subjectId")
    private List<Grade> grades;

    @Relation(parentColumn = "subjectId", entityColumn = "subjectId")
    private List<CalendarEntry> calendarEntries;

    @Ignore
    public Float calculateAverage(GradeFormat format) {
        if (grades.isEmpty()) return null;

        float sumOthers = 0;
        float amountOthers = 0;
        float sumExams = 0;
        float amountExams = 0;

        List<DefaultGrade> translatedGrades = new ArrayList<>();
        for (Grade grade : grades) {
            boolean isExam = grade.getGradeType().isExam();
            translatedGrades.add(format == GradeFormat.ABITUR
                    ? new SecondaryTwoGrade(isExam).setUpFromDatabaseValue(grade.getValue())
                    : new SecondaryOneGrade(isExam).setUpFromDatabaseValue(grade.getValue()));
        }

        for (DefaultGrade grade : translatedGrades) {
            if (grade.isExam()) {
                sumExams += grade.getValueToCalculateAverage();
                amountExams++;
                continue;
            }

            sumOthers+= grade.getValueToCalculateAverage();
            amountOthers++;
        }

        float weightExam = subject.getExamWeight() / 100f;
        float weightOthers = 1 - weightExam;

        if (amountExams == 0 && amountOthers == 0) throw new IllegalStateException();
        if (amountOthers == 0) return sumExams / amountExams;
        if (amountExams == 0) return sumOthers / amountOthers;
        return weightExam * (sumExams / amountExams) + weightOthers * (sumOthers / amountOthers);
    }

    @Ignore
    public String getAverageAsString(GradeFormat gradeFormat){
        Float average = calculateAverage(gradeFormat);
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        return "Ø " + (average == null ? "-" : decimalFormat.format(average));
    }

    @Override
    public Long getId() {
        return subject.getSubjectId();
    }

    @Override
    public String getStringToApplyQuery() {
        return subject.getName();
    }
}
