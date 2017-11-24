import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.IllegalStateException;

public class LinkedListIterator<T> implements Iterator<T> {
    private LinkedList<T> list;
    private LinkedListNode<T> cursor;

    private class EmptyNode<T> extends LinkedListNode<T> {
        public EmptyNode(LinkedListNode<T> next) {
            super(null);
            this.next = next;
        }
    }

    public LinkedListIterator(LinkedList<T> list, LinkedListNode<T> root) {
        this.list = list;
        cursor = new EmptyNode<T>(root);
    }

    public boolean hasNext() {
        return cursor.next != null;
    }

    public T next() throws NoSuchElementException {
        if (cursor.next == null)
            throw new NoSuchElementException();
        else {
            cursor = cursor.next;
            return cursor.value;
        }
    }

    public void remove() throws IllegalStateException {
        if (cursor instanceof EmptyNode)
            throw new IllegalStateException();
        else
            list.remove(cursor);
    }
}
