package markus.wieland.huelssegymnasiumapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import markus.wieland.defaultappelements.uielements.adapter.DefaultViewHolder;
import markus.wieland.defaultappelements.uielements.adapter.QueryableAdapter;
import markus.wieland.defaultappelements.uielements.adapter.iteractlistener.OnItemInteractListener;
import markus.wieland.huelssegymnasiumapp.subjects.Subject;

public class SubjectAdapter extends QueryableAdapter<Long, Subject, SubjectAdapter.SubjectViewHolder> {

    public SubjectAdapter(OnItemInteractListener<Subject> onItemInteractListener) {
        super(onItemInteractListener);
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubjectViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_subject, parent, false));
    }

    public class SubjectViewHolder extends DefaultViewHolder<Subject> {

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bindViews() {

        }

        @Override
        public void bindItemToViewHolder(Subject subject) {

        }
    }

}
