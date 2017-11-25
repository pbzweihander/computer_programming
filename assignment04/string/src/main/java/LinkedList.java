import java.lang.Iterable;
import java.lang.IndexOutOfBoundsException;

public class LinkedList<T> implements Iterable<T> {
    protected LinkedListNode<T> root;

    public LinkedList() {
        root = null;
    }

    protected LinkedList(LinkedListNode<T> root) {
        this.root = root;
    }

    public void attach(T element) {
        if (root == null)
            root = new LinkedListNode<>(element);
        else {
            LinkedListNode<T> node = root;
            while (node.next != null)
                node = node.next;
            node.next = new LinkedListNode<>(element);
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public LinkedListIterator<T> iterator() {
        return new LinkedListIterator<T>(this, root);
    }

    public T nth(int index) {
        LinkedListNode<T> node = root;
        while (index-- > 0) {
            node = node.next;
            if (node == null)
                throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        return node.value;
    }

    public int length() {
        LinkedListNode<T> node = root;
        int i = 0;
        while (node != null) {
            node = node.next;
            i++;
        }
        return i;
    }

    public LinkedList<T> subSequence(int startIndex, int endIndex) throws IndexOutOfBoundsException {
        LinkedList<T> new_list = new LinkedList<>();
        for (int i = startIndex; i <= endIndex; i++) {
            new_list.attach(nth(i));
        }
        return new_list;
    }
}
