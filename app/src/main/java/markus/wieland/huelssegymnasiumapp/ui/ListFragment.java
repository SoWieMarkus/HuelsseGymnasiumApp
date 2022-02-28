package markus.wieland.huelssegymnasiumapp.ui;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.defaultappelements.uielements.adapter.DefaultAdapter;
import markus.wieland.defaultappelements.uielements.adapter.DefaultViewHolder;
import markus.wieland.defaultappelements.uielements.fragments.DefaultFragment;

@Getter
@Setter
public abstract class ListFragment<T, V extends DefaultViewHolder<T>, A extends DefaultAdapter<T, V>> extends DefaultFragment {

    private StateRecyclerView<T, V, A> recyclerView;
    private A adapter;

    protected ListFragment(int layout) {
        super(layout);
    }

    public abstract int getRecyclerViewId();

    public abstract A createAdapter();

    @Override
    public void bindViews() {
        recyclerView = findViewById(getRecyclerViewId());
        adapter = createAdapter();
    }

    public void submitList(List<T> list) {
        recyclerView.submitList(list);
    }

    @Override
    public void initializeViews() {
        recyclerView.setAdapter(adapter);
    }

}
