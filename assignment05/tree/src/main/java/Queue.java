public class Queue<T> {
    protected class Node {
        public T value;
        public Node next;

        public Node(T element) {
            value = element;
            next = null;
        }
    }

    protected Node root;
    protected Node tail;

    public Queue() {
        root = null;
        tail = null;
    }

    public Queue(T[] arr) {
        this();
        tail = null;
        for (T t : arr)
            push(t);
    }

    public void push(T t) {
        if (root == null) {
            root = new Node(t);
            tail = root;
        } else {
            tail.next = new Node(t);
            tail = tail.next;
        }
    }

    public T pop() {
        final T c = root.value;
        root = root.next;
        if (root == null)
            tail = null;
        return c;
    }

    public T peek() {
        return root.value;
    }

    public boolean isEmpty() {
        return root == null;
    }
}
