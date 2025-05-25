import java.util.Collection;

public class List<T> {
    private Node<T> root;
    private int size;

    public List() {
        this.root = null;
        this.size = 0;
    }

    public List(Collection<? extends T> c) {
        this();
        addAll(c);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    public boolean add(T e) {
        if (root == null) {
            root = new Node<>(e);
        } else {
            Node<T> current = root;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node<>(e);
        }
        size++;
        return true;
    }

    public boolean remove(Object o) {
        if (root == null) return false;

        if (root.data.equals(o)) {
            root = root.next;
            size--;
            return true;
        }

        Node<T> current = root;
        while (current.next != null) {
            if (current.next.data.equals(o)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public T get(int index) {
        checkIndex(index);
        Node<T> current = root;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public T set(int index, T element) {
        checkIndex(index);
        Node<T> current = root;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        T oldValue = current.data;
        current.data = element;
        return oldValue;
    }

    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == 0) {
            root = new Node<>(element, root);
        } else {
            Node<T> current = root;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = new Node<>(element, current.next);
        }
        size++;
    }

    public T remove(int index) {
        checkIndex(index);
        T removed;
        if (index == 0) {
            removed = root.data;
            root = root.next;
        } else {
            Node<T> current = root;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            removed = current.next.data;
            current.next = current.next.next;
        }
        size--;
        return removed;
    }

    public int indexOf(Object o) {
        Node<T> current = root;
        int index = 0;
        while (current != null) {
            if (o == null ? current.data == null : o.equals(current.data)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    public int lastIndexOf(Object o) {
        Node<T> current = root;
        int index = 0;
        int lastIndex = -1;
        while (current != null) {
            if (o == null ? current.data == null : o.equals(current.data)) {
                lastIndex = index;
            }
            current = current.next;
            index++;
        }
        return lastIndex;
    }

    public boolean addAll(Collection<? extends T> c) {
        if (c.isEmpty()) return false;
        for (T element : c) {
            add(element);
        }
        return true;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (c.isEmpty()) return false;

        int i = index;
        for (T element : c) {
            add(i++, element);
        }
        return true;
    }

    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object o : c) {
            while (remove(o)) {
                modified = true;
            }
        }
        return modified;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<T> current = root;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}