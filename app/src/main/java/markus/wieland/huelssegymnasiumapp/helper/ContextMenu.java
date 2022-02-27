package markus.wieland.huelssegymnasiumapp.helper;

import android.view.View;

import markus.wieland.huelssegymnasiumapp.OnContextMenuListener;
import markus.wieland.huelssegymnasiumapp.R;

public class ContextMenu<T> implements View.OnCreateContextMenuListener {

    private final OnContextMenuListener<T> contextMenuListener;
    private final T t;

    public ContextMenu(OnContextMenuListener<T> contextMenuListener, T t) {
        this.contextMenuListener = contextMenuListener;
        this.t = t;
    }

    @Override
    public void onCreateContextMenu(android.view.ContextMenu contextMenu, View view, android.view.ContextMenu.ContextMenuInfo contextMenuInfo) {
        contextMenu.add(0, 1, 0, R.string.context_menu_edit).setOnMenuItemClickListener(menuItem -> {
            contextMenuListener.onEdit(t);
            return true;
        });
        contextMenu.add(0, 1, 0, R.string.context_menu_delete).setOnMenuItemClickListener(menuItem -> {
            contextMenuListener.onDelete(t);
            return true;
        });
    }
}
