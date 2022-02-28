package markus.wieland.huelssegymnasiumapp;

public interface OnCalendarContextMenu<T> extends OnContextMenuListener<T> {

    void onDone(T t);

}
