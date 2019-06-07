package SortableLists.lists;

import SortableLists.sort.Comparator;
import SortableLists.sort.HeapSort;

/**
 * @author joschaseelig
 */
public class SinglyLinkedList<T> implements Listable<T> {

    private Node head;
    private int n = 0;

    private class Node {
        private T data;
        private Node next;
    }

    @Override
    public void add(T data) throws NullPointerException {
        if (data == null) {
            throw new NullPointerException();
        }
        Node newNode = new Node();
        newNode.data = data;
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        n++;
    }

    @Override
    public T insertAt(int index, T data) throws IndexOutOfBoundsException, NullPointerException {
        if (data == null) {
            throw new NullPointerException();
        }
        checkPositionIndex(index);
        Node newNode = new Node();
        newNode.data = data;
        if (index == 0) {
            newNode.next = head;
            head = newNode;
            n++;
        } else {
            Node temp = head;
            for (int i = 0; temp != null; i++) {
                if (i == index - 1) {
                    newNode.next = temp.next;
                    temp.next = newNode;
                    n++;
                    return null;
                }
                temp = temp.next;
            }
        }
        return null;
    }

    @Override
    public void swap(int firstIndex, int secondIndex) throws IndexOutOfBoundsException {
        checkElementIndex(firstIndex);
        checkPositionIndex(secondIndex);
        if (firstIndex == secondIndex)
            return;
        if (firstIndex > secondIndex) {
            int temp = firstIndex;
            firstIndex = secondIndex;
            secondIndex = temp;
        }
        this.insertAt(secondIndex, this.get(firstIndex));
        this.insertAt(firstIndex, this.get(secondIndex + 1));
        this.remove(firstIndex + 1);
        this.remove(secondIndex + 1);
    }

    @Override
    public T get(int index) throws IndexOutOfBoundsException {
        checkElementIndex(index);
        Node temp = head;
        for (int i = 0; temp != null; i++) {
            if (i == index) {
                return temp.data;
            }
            temp = temp.next;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public void remove(int index) {
        checkElementIndex(index);
        if (index == 0) {
            head = head.next;
            n--;
        } else {
            Node temp = head;
            for (int i = 0; temp != null; i++) {
                if (i == index - 1) {
                    temp.next = temp.next.next;
                    n--;
                    return;
                }
                temp = temp.next;
            }
        }
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public void clear() {
        head = null;
        n = 0;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void heapsort(Comparator<T> comparator) throws NullPointerException {
        new HeapSort<T>().sort(this, comparator);
    }

    @Override
    public Listable<T> search(T term, Comparator<T> comparator) {
        Listable<T> findings = new SinglyLinkedList<>();
        Node temp = head;
        while (temp != null) {
            if (comparator.compare(term, temp.data) == 0)
                findings.add(temp.data);
            temp = temp.next;
        }
        return findings;
    }

    public boolean isElementIndex(int index) {
        return index >= 0 && index < n;
    }

    public boolean isPositionIndex(int index) {
        return index >= 0 && index <= n;
    }

    public void checkElementIndex(int index) {
        if (!isElementIndex(index)) throw new IndexOutOfBoundsException();
    }

    public void checkPositionIndex(int index) {
        if (!isPositionIndex(index)) throw new IndexOutOfBoundsException();
    }

    @Override
    public void print(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= n) {
            throw new IndexOutOfBoundsException();
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        System.out.println(temp.data);
    }

    @Override
    public void printAll() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
}

