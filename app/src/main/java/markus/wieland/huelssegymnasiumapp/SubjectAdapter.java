package markus.wieland.huelssegymnasiumapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import lombok.Setter;
import markus.wieland.defaultappelements.uielements.adapter.DefaultViewHolder;
import markus.wieland.defaultappelements.uielements.adapter.QueryableAdapter;
import markus.wieland.defaultappelements.uielements.adapter.iteractlistener.OnItemClickListener;
import markus.wieland.defaultappelements.uielements.adapter.iteractlistener.OnItemInteractListener;
import markus.wieland.huelssegymnasiumapp.grades.GradeFormat;
import markus.wieland.huelssegymnasiumapp.subjects.Subject;
import markus.wieland.huelssegymnasiumapp.subjects.SubjectWithGradesAndCalendar;

@Setter
public class SubjectAdapter extends QueryableAdapter<Long, SubjectWithGradesAndCalendar, SubjectAdapter.SubjectViewHolder> {

    private GradeFormat gradeFormat;

    public SubjectAdapter(OnItemInteractListener<SubjectWithGradesAndCalendar> onItemInteractListener) {
        super(onItemInteractListener);
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubjectViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject, parent, false));
    }

    @Override
    public OnItemClickListener<SubjectWithGradesAndCalendar> getOnItemInteractListener() {
        return (OnItemClickListener<SubjectWithGradesAndCalendar>)super.getOnItemInteractListener();
    }

    public class SubjectViewHolder extends DefaultViewHolder<SubjectWithGradesAndCalendar> {

        private TextView name;
        private TextView teacher;
        private TextView average;
        private LinearLayout color;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bindViews() {
            teacher = findViewById(R.id.item_subject_teacher);
            color = findViewById(R.id.item_subject_color);
            average = findViewById(R.id.item_subject_average);
            name = findViewById(R.id.item_subject_name);
        }

        @Override
        public void bindItemToViewHolder(SubjectWithGradesAndCalendar subjectWithGradesAndCalendar) {
            Subject subject = subjectWithGradesAndCalendar.getSubject();
            color.setBackgroundColor(subject.getColor());
            name.setText(subject.getName());
            teacher.setText(subject.getTeacher() == null ? "-" : subject.getTeacher());
            average.setText(subjectWithGradesAndCalendar.getAverageAsString(gradeFormat));
            itemView.setOnClickListener(view -> getOnItemInteractListener().onClick(subjectWithGradesAndCalendar));
        }
    }

}
