public class Assignment5_1 {
    public Node5_1 root;

    public Assignment5_1(String preorder, String inorder) {
        if (preorder.isEmpty()) {
            root = null;
        } else {
            root = construct(new StringQueue(preorder), new StringQueue(inorder));
        }
    }

    private Node5_1 construct(StringQueue preorder, StringQueue inorder) {
        Node5_1 node = new Node5_1();
        node.label = preorder.pop();
        if (inorder.peek() == node.label)
            return node;
        StringQueue[] lists = inorder.split(node.label);
        node.left = construct(preorder, lists[0]);
        node.right = construct(preorder, lists[1]);
        return node;
    }

    public String report() {
        StringBuilder builder = new StringBuilder();
        StringBuilder buffer = new StringBuilder();
        Queue<Node5_1> queue = new Queue<>();
        Node5_1 delimiter = new Node5_1();
        boolean reversed = true;

        delimiter.label = '\0';
        queue.push(root);
        queue.push(delimiter);
        builder.append(root.label);

        while (!queue.isEmpty()) {
            Node5_1 node = queue.pop();
            if (node == delimiter) {
                if (!queue.isEmpty())
                    queue.push(delimiter);
                if (reversed)
                    builder.append(buffer.reverse());
                else
                    builder.append(buffer);
                reversed = !reversed;
                buffer.setLength(0);
            } else {
                buffer.append(node.left != null ? node.left.label : "");
                buffer.append(node.right != null ? node.right.label : "");
                if (node.left != null)
                    queue.push(node.left);
                if (node.right != null)
                    queue.push(node.right);
            }
        }
        return builder.toString();
    }

    public Node5_1 get() {
        return root;
    }
}
