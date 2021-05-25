package ru.geekbrains.sklyarov.lesson2;

import java.util.Arrays;

public class ArrayImpl<E extends Comparable<? super E>> implements Array<E> {

    private static final int DEFAULT_CAPACITY = 8;

    protected E[] data;
    protected int size;


    @SuppressWarnings("unchecked")
    public ArrayImpl(int initialCapacity) {
        this.data = (E[]) new Comparable[initialCapacity];
    }

    public ArrayImpl() {
        this(DEFAULT_CAPACITY);
    }

    // O(1) --> O(n)
    @Override
    public void add(E value) {
        checkAndGrow();
        data[size++] = value;
    }

    protected void checkAndGrow() {
        if (data.length == size) {
            data = Arrays.copyOf(data, calculateNewLength());
        }
    }

    private int calculateNewLength() {
        return size == 0 ? 1 : size * 2;
    }

    // O(1)
    @Override
    public E get(int index) {
        checkIndex(index);
        return data[index];
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            String errorMsg = String.format("Incorrect 'index': %d; max value is: %d", index, size - 1);
            throw new IndexOutOfBoundsException(errorMsg);
        }
    }

    @Override
    public boolean remove(E value) {
        int index = indexOf(value);
        return index != -1 && remove(index) != null;
    }

    // O(n)
    @Override
    public E remove(int index) {
        checkIndex(index);
        E removedValue = data[index];
        if (index < size - 1) {
            System.arraycopy(data, index + 1, data, index, size - index - 1);
        }
        data[--size] = null;
        return removedValue;
    }

    // O(n)
    @Override
    public int indexOf(E value) {
        for (int i = 0; i < size; i++) {
            if (value.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(E value) {
        return indexOf(value) != -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(data[i]).append(",");
        }
        if (size > 0) {
            sb.append(data[size - 1]);
        }
        sb.append("]");
        return sb.toString();
    }

    // O(n^2) - compare
    // O(n^2) - exchange
    @Override
    public void sortBubble() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (data[j].compareTo(data[j + 1]) > 0) {
                    swap(j, j + 1);
                }
            }
        }
    }

    private void swap(int index1, int index2) {
        E temp = data[index1];
        data[index1] = data[index2];
        data[index2] = temp;
    }

    // O(n^2) - compare
    // O(n) - exchange
    @Override
    public void sortSelect() {
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (data[j].compareTo(data[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(i, minIndex);
            }
        }
    }

    // O(n^2) --> O(n) - compare
    // O(n) --> O(0) - exchange
    //  5,7,2,4
    //  5,7,2,4
    //  5,7,7,4 (2) 7,5,7,4 (2) 2,5,7,4
    //  2,5,7,7 (4) 2,5,5,7 (4) 2,4,5,7
    @Override
    public void sortInsert() {
        for (int i = 1; i < size; i++) {
            E temp = data[i];
            int ind = i;
            while (ind > 0 && data[ind - 1].compareTo(temp) >= 0) {
                data[ind] = data[ind - 1];
                ind--;
            }
            data[ind] = temp;
        }
    }
}
