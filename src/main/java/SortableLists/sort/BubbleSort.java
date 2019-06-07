package SortableLists.sort;

import SortableLists.lists.Listable;

/**
 * @author joschaseelig
 */
public class BubbleSort<T> implements Sortable<T> {
    @Override
    public void sort(Listable<T> list, Comparator<T> comparator) {
        for (int i = 1; i < list.size(); i++) {
            for (int e = 0; e < list.size() - i; e++) {
                if (comparator.compare(list.get(e), list.get(e + 1)) > 0)
                    list.swap(e, e + 1);
            }
        }
    }
}
