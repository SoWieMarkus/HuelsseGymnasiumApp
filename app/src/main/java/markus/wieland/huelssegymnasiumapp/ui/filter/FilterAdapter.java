package markus.wieland.huelssegymnasiumapp.ui.filter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import markus.wieland.defaultappelements.uielements.adapter.DefaultAdapter;
import markus.wieland.defaultappelements.uielements.adapter.DefaultViewHolder;
import markus.wieland.huelssegymnasiumapp.R;

@Getter
public class FilterAdapter extends DefaultAdapter<Type, FilterAdapter.FilterViewHolder> {

    private final List<Type> filter;

    public FilterAdapter(OnFilterChangeListener onFilterChangeListener) {
        super(onFilterChangeListener);
        filter = new ArrayList<>();
    }

    @NonNull
    @Override
    public FilterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FilterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filter, parent, false));
    }

    @Override
    public OnFilterChangeListener getOnItemInteractListener() {
        return (OnFilterChangeListener) super.getOnItemInteractListener();
    }

    protected void select(Type type) {
        if (filter.contains(type)) {
            filter.remove(type);
        } else {
            filter.add(type);
        }
        notifyItemChanged(getList().indexOf(type));
        if (getOnItemInteractListener() == null) return;
        getOnItemInteractListener().onChange(filter);
    }

    public class FilterViewHolder extends DefaultViewHolder<Type> {

        private TextView displayName;

        public FilterViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void bindViews() {
            displayName = findViewById(R.id.item_filter_display_name);
        }

        @Override
        public void bindItemToViewHolder(Type type) {
            displayName.setSelected(getFilter().contains(type));
            displayName.setText(type.getDisplayNameAsString(itemView.getContext()));
            itemView.setOnClickListener(view -> select(type));
        }
    }

}
