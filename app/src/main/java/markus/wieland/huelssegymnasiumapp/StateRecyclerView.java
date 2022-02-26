package markus.wieland.huelssegymnasiumapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import markus.wieland.defaultappelements.uielements.adapter.DefaultAdapter;
import markus.wieland.defaultappelements.uielements.adapter.DefaultViewHolder;

@Getter
@Setter
public class StateRecyclerView<T, VH extends DefaultViewHolder<T>, A extends DefaultAdapter<T, VH>> extends ConstraintLayout {

    private RecyclerView recyclerView;
    private LinearLayout emptyView;
    private TextView emptyMessage;
    private ProgressBar progressBar;
    private State state;
    private A adapter;

    public StateRecyclerView(Context context) {
        this(context, null);
    }

    public StateRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StateRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeView(attrs, defStyleAttr);
    }

    public void setAdapter(A adapter) {
        this.recyclerView.setAdapter(adapter);
        this.adapter = adapter;
    }

    protected void initializeView(AttributeSet attrs, int defStyle) {

        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.StateRecyclerView, defStyle, 0);

        LayoutInflater.from(getContext()).inflate(R.layout.layout_state_recycler_view, this);
        progressBar = findViewById(R.id.layout_state_recycler_view_progress_bar);
        emptyView = findViewById(R.id.layout_state_recycler_view_empty);
        recyclerView = findViewById(R.id.layout_state_recycler_view_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        emptyMessage = findViewById(R.id.layout_state_recycler_view_empty_message);
        emptyMessage.setText(array.getString(R.styleable.StateRecyclerView_empty_message));

        if (array.getBoolean(R.styleable.StateRecyclerView_nested, false)) {

            setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        setState(State.LOADING);
    }

    public void submitList(List<T> list) {
        adapter.submitList(list);
        setState(list.isEmpty() ? State.EMPTY : State.LIST);
    }

    public void setState(State state) {
        recyclerView.setVisibility(state == State.LIST ? VISIBLE : GONE);
        progressBar.setVisibility(state == State.LOADING ? VISIBLE : GONE);
        emptyView.setVisibility(state == State.EMPTY ? VISIBLE : GONE);
        this.state = state;
    }

    public enum State {
        LOADING,
        EMPTY,
        LIST;
    }
}
