public class Assignment4_2 {
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

    private interface Reporter {
        public void report(Node4_1 node, StringBuilder builder);
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
        if (parent.left != null)
            dna = makeNode(dna, parent.left);
        if (parent.right != null)
            dna = makeNode(dna, parent.right);
        return dna;
    }

    private void labelNode(String dna, Node4_1 root) {
        Queue<Node4_1> labelling_queue = new Queue<>();
        labelling_queue.push(root);
        while (!labelling_queue.isEmpty()) {
            Node4_1 node = labelling_queue.pop();
            node.character = dna.charAt(0);
            dna = dna.substring(1);
            if (node.left != null)
                labelling_queue.push(node.left);
            if (node.right != null)
                labelling_queue.push(node.right);
        }
    }

    public String report_bits_preorder() {
        return reportPreorder(root, (Node4_1 node, StringBuilder builder) -> {
            reportBits(node, builder);
        });
    }

    public String report_preorder() {
        return reportPreorder(root, (Node4_1 node, StringBuilder builder) -> {
            reportCharacter(node, builder);
        });
    }

    public String report_bits_levelorder() {
        return reportLevelOrder(root, (Node4_1 node, StringBuilder builder) -> {
            reportBits(node, builder);
        });
    }

    public String report_levelorder() {
        return reportLevelOrder(root, (Node4_1 node, StringBuilder builder) -> {
            reportCharacter(node, builder);
        });
    }

    private String reportPreorder(Node4_1 node, Reporter reporter) {
        StringBuilder builder = new StringBuilder();
        reportPreorder_inner(root, builder, reporter);
        return builder.toString();
    }

    private void reportPreorder_inner(Node4_1 node, StringBuilder builder, Reporter reporter) {
        reporter.report(node, builder);
        if (node.left != null)
            reportPreorder_inner(node.left, builder, reporter);
        if (node.right != null)
            reportPreorder_inner(node.right, builder, reporter);
    }

    private String reportLevelOrder(Node4_1 root, Reporter reporter) {
        Queue<Node4_1> level_queue = new Queue<>();
        StringBuilder builder = new StringBuilder();
        level_queue.push(root);
        while (!level_queue.isEmpty()) {
            Node4_1 node = level_queue.pop();
            reporter.report(node, builder);
            if (node.left != null)
                level_queue.push(node.left);
            if (node.right != null)
                level_queue.push(node.right);
        }
        return builder.toString();
    }

    private void reportBits(Node4_1 node, StringBuilder builder) {
        builder.append(node.left != null ? "1" : "0");
        builder.append(node.right != null ? "1" : "0");
    }

    private void reportCharacter(Node4_1 node, StringBuilder builder) {
        builder.append(node.character);
    }
}
