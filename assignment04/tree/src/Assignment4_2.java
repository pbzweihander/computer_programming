public class Assignment4_2 {
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
            if (root == null)
                return null;
            final T t = root.value;
            root = root.next;
            return t;
        }

        public boolean isEmpty() {
            return root == null;
        }
    }

    public Assignment4_2(String s1, String s2) {
        root = new Node4_1();
        makeNode(s1, root);
        labelNode(s2, root);
    }

    private String makeNode(String dna, Node4_1 parent) {
        if (dna.charAt(0) == '1')
            parent.left = new Node4_1();
        if (dna.charAt(1) == '1')
            parent.right = new Node4_1();
        dna = dna.substring(2);
        if (parent.left != null && !dna.isEmpty())
            dna = makeNode(dna, parent.left);
        if (parent.right != null && !dna.isEmpty())
            dna = makeNode(dna, parent.right);
        return dna;
    }

    private void labelNode(String dna, Node4_1 root) {
        Queue<Node4_1> labelling_queue = new Queue<>();
        labelling_queue.push(root);
        while (!labelling_queue.isEmpty()) {
            Node4_1 node = labelling_queue.pop();
            node.label = dna.charAt(0);
            if (dna.length() > 1)
                dna = dna.substring(1);
            if (node.left != null)
                labelling_queue.push(node.left);
            if (node.right != null)
                labelling_queue.push(node.right);
        }
    }

    public String report_bits_preorder() {
        return reportBitsPreorder(root);
    }

    private String reportBitsPreorder(Node4_1 node) {
        return reportBits(node) + (node.left != null ? reportBitsPreorder(node.left) : "")
                + (node.right != null ? reportBitsPreorder(node.right) : "");
    }

    public String report_bits_levelorder() {
        Queue<Node4_1> level_queue = new Queue<>();
        String out = "";
        level_queue.push(root);
        while (!level_queue.isEmpty()) {
            Node4_1 node = level_queue.pop();
            out += reportBits(node);
            if (node.left != null)
                level_queue.push(node.left);
            if (node.right != null)
                level_queue.push(node.right);
        }
        return out;
    }

    public String report_preorder() {
        return reportPreorder(root);
    }

    private String reportPreorder(Node4_1 node) {
        return node.label + (node.left != null ? reportPreorder(node.left) : "")
                + (node.right != null ? reportPreorder(node.right) : "");
    }

    public String report_levelorder() {
        Queue<Node4_1> level_queue = new Queue<>();
        String out = "";
        level_queue.push(root);
        while (!level_queue.isEmpty()) {
            Node4_1 node = level_queue.pop();
            out += node.label;
            if (node.left != null)
                level_queue.push(node.left);
            if (node.right != null)
                level_queue.push(node.right);
        }
        return out;
    }

    private String reportBits(Node4_1 node) {
        return (node.left != null ? "1" : "0") + (node.right != null ? "1" : "0");
    }
}
