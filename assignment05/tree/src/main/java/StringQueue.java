public class StringQueue extends Queue<Character> {
    private class EmptyHead extends Node {
        public EmptyHead(Node n) {
            super(null);
            next = n;
        }
    }

    public StringQueue() {
        super();
    }

    public StringQueue(String str) {
        this();
        for (char c : str.toCharArray())
            push(c);
    }

    private StringQueue(Node root, Node tail) {
        this.root = root;
        this.tail = tail;
    }

    public String toString() {
        StringBuilder b = new StringBuilder();
        for (Node n = root; n != null; n = n.next)
            b.append(n.value);
        return b.toString();
    }

    public StringQueue[] split(char c) {
        for (Node n = new EmptyHead(root); n.next != null; n = n.next) {
            if (n.next.value == c) {
                StringQueue[] q = new StringQueue[2];
                q[0] = new StringQueue(root, n);
                q[1] = new StringQueue(n.next.next, tail);
                n.next.next = null;
                n.next = null;
                return q;
            }
        }
        return null;
    }
}
