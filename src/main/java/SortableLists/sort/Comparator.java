package SortableLists.sort;

/**
 * Implementing this interface results in a Comparator-Type.
 * Objects of these implementations are used to compare 2 objects of the same type.
 *
 * @param <T>
 * @author joschaseelig
 */
public interface Comparator<T> {
    /**
     * Returns 0 if the specified objects are equal, <0 if object 2 is less than object one or >0 otherwise.
     *
     * @param o1 object 1
     * @param o2 object 2
     * @return <0 || 0 || >0
     */
    int compare(T o1, T o2);
}
