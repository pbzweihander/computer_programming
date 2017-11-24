import java.lang.Iterable;

public class LinkedList<T> implements Iterable<T> {
    private LinkedListNode<T> root;

    public LinkedList() {
        root = null;
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
}
