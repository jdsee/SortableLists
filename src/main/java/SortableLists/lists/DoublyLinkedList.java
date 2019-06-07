package SortableLists.lists;

import SortableLists.sort.Comparator;
import SortableLists.sort.HeapSort;

/**
 * @author joschaseelig
 */
public class DoublyLinkedList<T> implements Listable<T> {

    private Node head;
    private Node tail;
    private int n = 0;

    private class Node {
        private T data;
        private Node prev;
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
        } else if (tail == null) {
            tail = newNode;
            tail.prev = head;
            head.next = tail;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        n++;
    }

    @Override
    public T insertAt(int index, T data) throws IndexOutOfBoundsException, NullPointerException {
        if (data == null) {
            throw new NullPointerException();
        } else if (index < 0 || index > n) {
            throw new IndexOutOfBoundsException();
        }
        Node newNode = new Node();
        newNode.data = data;
        Node temp = head;
        if (index == 0) {
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
        } else if (index == n) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else if (index <= n / 2) {
            for (int i = 0; i <= n / 2; i++) {
                if (i == index) {
                    newNode.prev = temp.prev;
                    temp.prev.next = newNode;
                    temp.prev = newNode;
                    newNode.next = temp;

                }
                temp = temp.next;
            }
        } else {
            temp = tail;
            for (int i = n - 1; i > n / 2; i--) {
                if (i == index) {
                    newNode.prev = temp.prev;
                    temp.prev.next = newNode;
                    temp.prev = newNode;
                    newNode.next = temp;

                }
                temp = temp.prev;
            }
        }
        n++;
        return null;
    }

    @Override
    public void swap(int firstIndex, int secondIndex) throws IndexOutOfBoundsException {
        if (firstIndex < 0 || firstIndex > n
                && secondIndex < 0 || secondIndex > n)
            throw new IndexOutOfBoundsException();
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
        if (index >= 0 && index < n) {
            if (index <= n / 2) {
                Node temp = head;
                for (int i = 0; i <= n / 2; i++) {
                    if (i == index) {
                        return temp.data;
                    }
                    temp = temp.next;
                }
            } else {
                Node temp = tail;
                for (int i = n - 1; i > n / 2; i--) {
                    if (i == index) {
                        return temp.data;
                    }
                    temp = temp.prev;
                }
            }
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public void remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= n) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            head.next.prev = null;
            head = head.next;
        } else if (index == n - 1) {
            tail = tail.prev;
            tail.next = null;
        } else if (index <= n / 2) {
            Node temp = head;
            for (int i = 0; i <= n / 2; i++) {
                if (i == index) {
                    temp.next.prev = temp.prev;
                    temp.prev.next = temp.next;
                }
                temp = temp.next;
            }
        } else {
            Node temp = tail;
            for (int i = n - 1; i > n / 2; i--) {
                if (i == index) {
                    temp.next.prev = temp.prev;
                    temp.prev.next = temp.next;
                }
                temp = temp.prev;
            }
        }
        n--;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public void clear() {
        if (head == null) {
            return;
        } else if (head.next == null) {
            head = null;
        } else {
            head.next.prev = null;
            head = null;
        }
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
        Listable<T> findings = new DoublyLinkedList<>();
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

    @Override
    public void printAll() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    @Override
    public void print(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= n) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            head.next.prev = null;
            head = head.next;
        } else if (index <= n / 2) {
            Node temp = head;
            for (int i = 0; i < n / 2; i++) {
                if (i == index) {
                    System.out.println(temp.data);
                    return;
                }
            }
        } else {
            Node temp = tail;
            for (int i = n - 1; i > n / 2; i--) {
                if (i == index) {
                    System.out.println(temp.data);
                    return;
                }
                temp = temp.prev;
            }
        }
    }
}
