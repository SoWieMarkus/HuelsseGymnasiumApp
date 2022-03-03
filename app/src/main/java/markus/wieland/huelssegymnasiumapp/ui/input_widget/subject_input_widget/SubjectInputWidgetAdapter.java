package markus.wieland.huelssegymnasiumapp.ui.input_widget.subject_input_widget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import markus.wieland.defaultappelements.uielements.adapter.DefaultAdapter;
import markus.wieland.defaultappelements.uielements.adapter.DefaultViewHolder;
import markus.wieland.defaultappelements.uielements.adapter.iteractlistener.OnItemClickListener;
import markus.wieland.huelssegymnasiumapp.R;
import markus.wieland.huelssegymnasiumapp.modules.subjects.models.Subject;

public class SubjectInputWidgetAdapter extends DefaultAdapter<Subject, SubjectInputWidgetAdapter.SubjectInputWidgetViewHolder> {

    private Subject selectedSubject;

    public SubjectInputWidgetAdapter(OnItemClickListener<Subject> onItemClickListener) {
        super(onItemClickListener);

    }

    @NonNull
    @Override
    public SubjectInputWidgetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubjectInputWidgetViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject_input_widget, parent, false));
    }

    @Override
    public OnItemClickListener<Subject> getOnItemInteractListener() {
        return (OnItemClickListener<Subject>)super.getOnItemInteractListener();
    }

    public void select(Subject subject) {
        selectedSubject = subject == selectedSubject ? null : subject;
        notifyDataSetChanged();
        getOnItemInteractListener().onClick(selectedSubject);
    }

    public Subject getSelectedSubject() {
        return selectedSubject;
    }


    public class SubjectInputWidgetViewHolder extends DefaultViewHolder<Subject> {

        private CardView background;
        private CardView selected;
        private TextView abbreviation;

        public SubjectInputWidgetViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bindViews() {
            background = findViewById(R.id.item_subject_input_widget_background);
            selected = findViewById(R.id.item_subject_input_widget_selected);
            abbreviation = findViewById(R.id.item_subject_input_widget_abbreviation);
        }

        @Override
        public void bindItemToViewHolder(Subject subject) {
            background.setCardBackgroundColor(subject.getColor());
            selected.setVisibility(selectedSubject == null || subject.getSubjectId() != selectedSubject.getSubjectId()
                    ? View.INVISIBLE
                    : View.VISIBLE);
            selected.setCardBackgroundColor(subject.getColor());
            abbreviation.setText(subject.getAbbreviation());
            background.setOnClickListener(view -> select(subject));
        }

    }
}
