public class Assignment4_1 {
    public Node4_1 root;

    private class Queue<T> {
        private class Node {
            public Node next;
            public T value;

            public Node(T element) {
                value = element;
            }
        }

        private Node root;
        private Node tail;

        public Queue() {
            root = null;
            tail = null;
        }

        public void push(T element) {
            if (root == null) {
                root = new Node(element);
                tail = root;
            } else {
                tail.next = new Node(element);
                tail = tail.next;
            }
        }

        public T pop() {
            if (root == null)
                return null;
            final T t = root.value;
            root = root.next;
            if (root == null)
                tail = null;
            return t;
        }

        public boolean isEmpty() {
            return root == null;
        }
    }

    public Assignment4_1(String line1, String line2) {
        Queue<Node4_1> node_queue = new Queue<>();
        Queue<Node4_1> child_needed_queue = new Queue<>();
        int i = 0;
        for (char c : line1.toCharArray()) {
            Node4_1 node = new Node4_1();
            boolean is_leaf = c == '1';
            node.label = c;
            node.character = is_leaf ? '1' : line2.charAt(i++);
            node_queue.push(node);
        }

        root = node_queue.pop();
        child_needed_queue.push(root);

        while (!child_needed_queue.isEmpty()) {
            Node4_1 n = child_needed_queue.pop();
            n.left = node_queue.pop();
            n.right = node_queue.pop();
            if (n.left != null && n.left.label == '0')
                child_needed_queue.push(n.left);
            if (n.right != null && n.right.label == '0')
                child_needed_queue.push(n.right);
        }
    }

    public Node4_1 getRoot() {
        return root;
    }
}
