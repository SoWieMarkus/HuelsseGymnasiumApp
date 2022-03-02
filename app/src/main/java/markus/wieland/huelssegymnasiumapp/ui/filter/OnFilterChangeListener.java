package markus.wieland.huelssegymnasiumapp.ui.filter;

import java.util.List;

import markus.wieland.defaultappelements.uielements.adapter.iteractlistener.OnItemInteractListener;
import markus.wieland.huelssegymnasiumapp.helper.Type;

public interface OnFilterChangeListener extends OnItemInteractListener<Type> {

    void onChange(List<Type> filter);
}
