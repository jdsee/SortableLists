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
     * Returns 0 if the specified objects are equal, less 0 if object 2 is greater than object 1 or greater 0 otherwise.
     *
     * @param o1 object 1
     * @param o2 object 2
     * @return value less 0, equal to 0 or greater than 0
     */
    int compare(T o1, T o2);
}
