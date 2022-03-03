package markus.wieland.huelssegymnasiumapp.ui.filter;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import lombok.Getter;

@Getter
public class FilterView extends RecyclerView {

    private final FilterAdapter adapter;

    public FilterView(@NonNull Context context) {
        this(context, null);
    }

    public FilterView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FilterView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setHasFixedSize(true);
        setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new FilterAdapter(null);
        setAdapter(adapter);
    }

    public void setOnFilterChangeListener(OnFilterChangeListener onFilterChangeListener) {
        adapter.setOnItemInteractListener(onFilterChangeListener);
    }

    public void setFilterOptions(Type[] types){
        adapter.submitList(types);
    }

    public List<Type> getFilterOptions(){
        return adapter.getFilter();
    }

}
