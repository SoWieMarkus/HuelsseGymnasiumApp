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
import markus.wieland.defaultappelements.uielements.adapter.iteractlistener.OnItemInteractListener;
import markus.wieland.huelssegymnasiumapp.calendar.LocalDateConverter;
import markus.wieland.huelssegymnasiumapp.grades.Grade;
import markus.wieland.huelssegymnasiumapp.grades.GradeFormat;
import markus.wieland.huelssegymnasiumapp.grades.transformer.DefaultGrade;
import markus.wieland.huelssegymnasiumapp.helper.ContextMenu;

@Setter
public class GradeAdapter extends QueryableAdapter<Long, Grade, GradeAdapter.GradeViewHolder> {

    private GradeFormat gradeFormat;

    public GradeAdapter(OnContextMenuListener<Grade> onContextMenuListener) {
        super(onContextMenuListener);
    }

    @NonNull
    @Override
    public GradeAdapter.GradeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GradeViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_grade, parent, false));
    }

    @Override
    public OnContextMenuListener<Grade> getOnItemInteractListener() {
        return (OnContextMenuListener<Grade>) super.getOnItemInteractListener();
    }

    public class GradeViewHolder extends DefaultViewHolder<Grade> {

        private TextView value;
        private TextView type;
        private TextView date;
        private LinearLayout color;

        public GradeViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bindViews() {
            value = findViewById(R.id.item_grade_value);
            type = findViewById(R.id.item_grade_type);
            date = findViewById(R.id.item_grade_date);
            color = findViewById(R.id.item_grade_color);
        }

        @Override
        public void bindItemToViewHolder(Grade grade) {
            value.setText(grade.getGrade(gradeFormat).toString());
            date.setText(LocalDateConverter.toDisplayString(grade.getLocalDate(), itemView.getContext()));
            type.setText(grade.getGradeType().getDisplayNameAsString(itemView.getContext()));
            color.setBackgroundColor(grade.getGrade(gradeFormat).getRawGradeValue().getColor());
            itemView.setOnCreateContextMenuListener(new ContextMenu<>(getOnItemInteractListener(), grade));
            itemView.setOnClickListener(view -> getOnItemInteractListener().onClick(grade));
        }
    }
}
