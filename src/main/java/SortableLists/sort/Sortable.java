package SortableLists.sort;

import SortableLists.lists.Listable;

/**
 * Implementing this interface results in a type to sort objects stored in a {@code Listable} by a {@code Comparator}.
 *
 * @param <T>
 * @author joschaseelig
 * @see Listable
 * @see Comparator
 */
public interface Sortable<T> {
    void sort(Listable<T> list, Comparator<T> comparator);
}
