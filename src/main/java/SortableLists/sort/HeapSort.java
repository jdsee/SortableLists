package SortableLists.sort;

import SortableLists.lists.Listable;

/**
 * @author joschaseelig
 */
public class HeapSort<T> implements Sortable<T> {
    private int n;

    @Override
    public void sort(Listable<T> list, Comparator<T> comparator) {
        n = list.size();
        buildMaxHeap(list, comparator);
        for (int i = n; i > 0; i--) {
            list.swap(0, i - 1);
            n--;
            heapify(1, list, comparator);
        }
    }

    private void buildMaxHeap(Listable<T> list, Comparator<T> comparator) {
        for (int i = Math.floorDiv(n, 2); i > 0; i--)
            heapify(i, list, comparator);
    }

    private void heapify(int i, Listable<T> list, Comparator<T> comparator) {
        int left = 2 * i;
        int right = left + 1;
        int max = i;

        if (left <= n)
            max = comparator.compare(list.get(i - 1), list.get(left - 1)) >= 0 ? i : left;
        if (right <= n)
            max = comparator.compare(list.get(max - 1), list.get(right - 1)) >= 0 ? max : right;

        if (max != i) {
            list.swap(i - 1, max - 1);
            heapify(max, list, comparator);
        }
    }
}
