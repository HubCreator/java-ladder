package techcourse.jcf.mission;

public class SimpleLinkedList implements SimpleList {

    Node next;
    private int size;

    @Override
    public boolean add(String value) {
        Node newNode = new Node(value, null);
        if (next == null) {
            next = newNode;
            size++;
            return true;
        }
        Node tmp = next;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = newNode;
        size++;
        return true;
    }

    @Override
    public void add(int index, String value) {
        Node newNode = new Node(value, null);
        Node tmp = next;
        for (int i = 0; i < index - 1; i++) {
            tmp = tmp.next;
        }
        newNode.next = tmp.next;
        tmp.next = newNode;
        size++;
    }

    @Override
    public String set(int index, String value) {
        Node tmp = next;
        String result;
        for (int i = 0; i < index - 1; i++) {
            tmp = tmp.next;
        }
        result = tmp.data;
        tmp.data = value;
        return result;
    }

    @Override
    public String get(int index) {
        Node result = next;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }

    @Override
    public boolean contains(String value) {
        Node result = next;
        while (result.next != null) {
            if (result.data.equals(value)) {
                return true;
            }
            result = result.next;
        }
        return false;
    }

    @Override
    public int indexOf(String value) {
        int index = 0;
        Node result = next;
        while (result.next != null) {
            if (result.data.equals(value)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean remove(String value) {
        return false;
    }

    @Override
    public String remove(int index) {
        String result;
        if (index == 0) {
            Node tmp = next;
            next = next.next;
            result = tmp.data;
            tmp = null;
        } else if (index == size - 1) {
            Node tmp = next;
            while (tmp.next != null) {
                tmp = tmp.next;
            }
            result = tmp.data;
            tmp = null;
        } else {
            Node tmp = next;
            for (int i = 0; i < index - 1; i++) {
                tmp = tmp.next;
            }
            result = tmp.next.data;
            tmp.next = tmp.next.next;
        }
        size--;
        return result;
    }

    @Override
    public void clear() {
        Node result = next;
        while (result.next != null) {
            Node tmp = result;
            result = tmp.next;
            tmp = null;
        }
        next = null;
        size = 0;
    }
}
