package markus.wieland.huelssegymnasiumapp.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.text.DecimalFormat;
import java.util.List;

import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.modules.grades.models.GradeFormat;
import markus.wieland.huelssegymnasiumapp.modules.settings.Settings;
import markus.wieland.huelssegymnasiumapp.modules.subjects.models.SubjectWithGradesAndCalendar;

public class AverageView extends ConstraintLayout {

    private static final String AVERAGE_SYMBOL = "Ø";

    private ProgressBar progressBar;
    private TextView average;

    private final Settings settings;
    private DecimalFormat decimalFormat;

    public AverageView(@NonNull Context context) {
        this(context, null);
    }

    public AverageView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AverageView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        settings = new Settings(getContext());
        initialize();
    }

    protected void initialize() {
        decimalFormat = new DecimalFormat("#.00");
        LayoutInflater.from(getContext()).inflate(R.layout.layout_average_view, this);
        GradeFormat gradeFormat = settings.getGradeFormat();
        progressBar = findViewById(R.id.average_view_progress_bar);
        average = findViewById(R.id.average_view_text_view);

        progressBar.setMax(gradeFormat.getMaximum());
        progressBar.setMin(gradeFormat.getMinimum());
    }

    public void calculateAverage(List<SubjectWithGradesAndCalendar> subjectWithGradesAndCalendars) {

        float total = 0;
        float amount = 0;

        GradeFormat gradeFormat = settings.getGradeFormat();
        for (SubjectWithGradesAndCalendar subjectWithGradesAndCalendar : subjectWithGradesAndCalendars) {
            Float averageOfSubject = subjectWithGradesAndCalendar.calculateAverage(gradeFormat);
            if (averageOfSubject == null) continue;
            amount++;
            total += averageOfSubject;
        }

        update(amount == 0 ? null : total / amount);
    }

    public void update(Float averageToDisplay) {
        average.setText(getAverageAsString(averageToDisplay));
        if (averageToDisplay == null) {
            progressBar.setProgress(progressBar.getMax());
            return;
        }

        progressBar.setProgress((int) (settings.getGradeFormat() == GradeFormat.ABITUR ? averageToDisplay * 100 : 600 - averageToDisplay * 100));
    }

    protected String getAverageAsString(Float averageToDisplay) {
        return AVERAGE_SYMBOL + " " + (averageToDisplay == null ? "-" : decimalFormat.format(averageToDisplay));
    }
}
