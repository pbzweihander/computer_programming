import java.util.Iterator;
import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

public class LinkedListIterator<T> implements Iterator<T> {
    private LinkedList<T> list;
    private LinkedListNode<T> cursor;

    public LinkedListIterator(LinkedList<T> list, LinkedListNode<T> root) {
        this.list = list;
        cursor = root;
    }

    public boolean hasNext() {
        return cursor != null;
    }

    public T next() throws NoSuchElementException {
        if (!hasNext())
            throw new NoSuchElementException();
        else {
            LinkedListNode<T> node = cursor;
            cursor = cursor.next;
            return node.value;
        }
    }

    public void remove() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
