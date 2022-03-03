package markus.wieland.huelssegymnasiumapp.modules.substitutions;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.defaultappelements.uielements.adapter.DefaultAdapter;
import markus.wieland.defaultappelements.uielements.adapter.DefaultViewHolder;
import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.api.models.Substitution;

@Getter
@Setter
public class SubstitutionAdapter extends DefaultAdapter<Substitution, SubstitutionAdapter.SubstitutionViewHolder> {

    private String course;

    public SubstitutionAdapter() {
        super(null);
    }

    @NonNull
    @Override
    public SubstitutionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubstitutionViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_substitutions, parent, false));
    }


    public class SubstitutionViewHolder extends DefaultViewHolder<Substitution> {

        private TextView lesson;
        private TextView teacher;
        private TextView subject;
        private TextView info;
        private TextView room;
        private TextView course;

        private LinearLayout background;
        private LinearLayout roomContainer;

        public SubstitutionViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bindViews() {
            lesson = findViewById(R.id.item_subsitutions_lesson);
            teacher = findViewById(R.id.item_subsitutions_teacher);
            subject = findViewById(R.id.item_subsitutions_subject);
            room = findViewById(R.id.item_subsitutions_room);
            info = findViewById(R.id.item_subsitutions_info);
            course = findViewById(R.id.item_subsitutions_course);
            background = findViewById(R.id.item_subsitution_background);
            roomContainer = findViewById(R.id.item_substitution_room_container);
        }

        @Override
        public void bindItemToViewHolder(Substitution substitution) {
            lesson.setText(substitution.getLesson());
            teacher.setText(substitution.getTeacher());
            teacher.setVisibility(View.GONE);
            roomContainer.setVisibility(substitution.getRoom().isEmpty() ? View.GONE : View.VISIBLE);
            subject.setText(substitution.getSubject());
            room.setText(substitution.getRoom());
            info.setText(substitution.getInfo());
            course.setText(substitution.getCourse());
            background.setSelected(substitution.getCourse().toLowerCase().contains(getCourse().toLowerCase()));
        }
    }


}
