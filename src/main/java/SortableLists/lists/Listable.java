package SortableLists.lists;

import SortableLists.sort.Comparator;

/**
 * Implementing this interface results in a listable data type. Items of any type can be stored in there.
 *
 * @param <T>
 * @author joschaseelig
 */
public interface Listable<T> {

    /**
     * Appends the specified item of this list.
     *
     * @param data element to be appended in this list.
     * @throws NullPointerException if the specified item is null.
     */
    void add(T data) throws NullPointerException;

    /**
     * Adding the specified item at the specified index in this list.
     *
     * @param index
     * @param data
     * @return
     * @throws IndexOutOfBoundsException if the index is out of range.
     * @throws NullPointerException      if the specified item is null.
     */
    T insertAt(int index, T data) throws IndexOutOfBoundsException, NullPointerException;

    public void swap(int firstIndex, int secondIndex) throws IndexOutOfBoundsException;

    /**
     * Returns the item at the specified index in this list.
     *
     * @param index
     * @return
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    T get(int index) throws IndexOutOfBoundsException;

    /**
     * Removes the item at the specified index in this list.
     *
     * @param index
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    void remove(int index) throws IndexOutOfBoundsException;

    /**
     * Returns the amount of items in this list
     *
     * @return amount of items in this list.
     */
    int size();

    /**
     * Removes all items in this list.
     */
    void clear();

    /**
     * Returns true if there are no items in this list.
     *
     * @return true if this list is empty.
     */
    boolean isEmpty();

    /**
     * Sorts the elements in this list by applying heapsort.
     * The sorted list will have the element with the lowest value at the first, and the one with the highest value
     * at the last index.
     *
     * @param comparator
     * @throws NullPointerException if one or more elements in this list is null.
     */
    void heapsort(Comparator<T> comparator) throws NullPointerException;

    /**
     * Searches one or more Students by comparing with the specified comparator.
     *
     * @param term       search term
     * @param comparator search criterium
     * @return Listable with all findings of this search. Return value is null, if no matching item exists.
     */
    Listable<T> search(T term, Comparator<T> comparator);

    boolean isElementIndex(int index);

    boolean isPositionIndex(int index);

    /**
     * Prints all items of this list to standardOutput.
     */
    void printAll();

    /**
     * Prints the item at the specified index in this list to standardOutput.
     *
     * @param index
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    void print(int index) throws IndexOutOfBoundsException;

}
