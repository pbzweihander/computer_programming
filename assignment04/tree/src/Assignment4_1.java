public class Assignment4_1 {
    public Node4_1 root;

    private class Queue<T> {
        private class Node {
            public Node next;
            public T value;

            public Node(T element) {
                value = element;
            }

            public void attach(T element) {
                if (next == null)
                    next = new Node(element);
                else
                    next.attach(element);
            }
        }

        private Node root;

        public Queue() {
            root = null;
        }

        public void push(T element) {
            if (root == null)
                root = new Node(element);
            else
                root.attach(element);
        }

        public T pop() {
            final T t = root.value;
            root = root.next;
            return t;
        }

        public int getSize() {
            if (root == null)
                return 0;
            int n = 1;
            Node cursor = root;
            while (cursor.next != null) {
                n++;
                cursor = cursor.next;
            }
            return n;
        }
    }

    public Assignment4_1(String line1, String line2) {

    }

    public Node4_1 getRoot() {
        return root;
    }
}
