package markus.wieland.huelssegymnasiumapp.ui.filter;

import java.util.ArrayList;
import java.util.List;

public class Filter<F extends Filterable> {

    private final List<F> items;

    public Filter() {
        this.items = new ArrayList<>();
    }

    public void updateList(List<F> items) {
        this.items.clear();
        this.items.addAll(items);
    }

    public List<F> filterList(List<Type> types) {
        if (types.isEmpty()) return items;
        List<F> filteredList = new ArrayList<>();
        for (F f : items) {
            for (Type type : types) {
                if (type.getId() == f.getType().getId()) {
                    filteredList.add(f);
                    break;
                }
            }
        }
        return filteredList;
    }
}
