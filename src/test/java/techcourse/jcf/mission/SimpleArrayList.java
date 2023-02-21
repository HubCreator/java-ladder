package techcourse.jcf.mission;

import java.util.Arrays;

public class SimpleArrayList implements SimpleList {

    private static final int DEFAULT_CAPACITY = 10;

    private String[] elementData;
    private int modCount = 0;
    private int size = 0;

    public SimpleArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new String[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = new String[DEFAULT_CAPACITY];
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    public SimpleArrayList() {
        this.elementData = new String[DEFAULT_CAPACITY];
    }

    @Override
    public boolean add(String value) {
        modCount++;
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[size++] = value;
        return true;
    }

    private String[] grow() {
        return Arrays.copyOf(elementData, size * 2);
    }

    @Override
    public void add(int index, String value) {
        modCount++;
        if (size == elementData.length) {
            elementData = grow();
        }
        for (int i = size - 1; i >= index; i--) {
            set(i + 1, get(i));
        }
        size++;
        set(index, value);
    }

    @Override
    public String set(int index, String value) {
        elementData[index] = value;
        return value;
    }

    @Override
    public String get(int index) {
        return elementData[index];
    }

    @Override
    public boolean contains(String value) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String value) {
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(value)) {
                return i;
            }
        }
        return -1;
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
    public boolean remove(String value) {
        remove(indexOf(value));
        return true;
    }

    @Override
    public String remove(int index) {
        String value = elementData[index];
        for (int i = indexOf(value); i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size - 1] = null;
        size--;
        return value;
    }

    @Override
    public void clear() {

    }
}
