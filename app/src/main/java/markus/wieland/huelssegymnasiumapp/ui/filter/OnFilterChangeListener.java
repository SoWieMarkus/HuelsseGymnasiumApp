package markus.wieland.huelssegymnasiumapp.ui.filter;

import java.util.List;

import markus.wieland.defaultappelements.uielements.adapter.iteractlistener.OnItemInteractListener;

public interface OnFilterChangeListener extends OnItemInteractListener<Type> {

    void onChange(List<Type> filter);
}
