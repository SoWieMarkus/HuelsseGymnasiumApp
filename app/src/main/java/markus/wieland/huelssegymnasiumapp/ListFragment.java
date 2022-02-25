package markus.wieland.huelssegymnasiumapp;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.defaultappelements.uielements.adapter.DefaultAdapter;
import markus.wieland.defaultappelements.uielements.adapter.DefaultViewHolder;
import markus.wieland.defaultappelements.uielements.fragments.DefaultFragment;

@Getter
@Setter
public abstract class ListFragment<T, V extends DefaultViewHolder<T>, A extends DefaultAdapter<T, V>> extends DefaultFragment {

    private RecyclerView recyclerView;
    private A adapter;

    public ListFragment(int layout) {
        super(layout);
    }

    public abstract int getRecyclerViewId();

    public abstract A createAdapter();

    @Override
    public void bindViews() {
        recyclerView = findViewById(getRecyclerViewId());
        adapter = createAdapter();
    }

    @Override
    public void initializeViews() {
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}
