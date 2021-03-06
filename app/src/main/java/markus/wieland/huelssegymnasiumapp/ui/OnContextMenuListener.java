package markus.wieland.huelssegymnasiumapp.ui;

import markus.wieland.defaultappelements.uielements.adapter.iteractlistener.OnItemClickListener;

public interface OnContextMenuListener<T> extends OnItemClickListener<T> {

    void onEdit(T t);

    void onDelete(T t);

}
