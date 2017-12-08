public class Assignment5_1 {
    public Node5_1 root;

    public Assignment5_1(String preorder, String inorder) {
        if (preorder.isEmpty())
            root = null;
        else
            root = construct(new StringQueue(preorder), inorder);
    }

    private Node5_1 construct(StringQueue preorder, String inorder) {
        Node5_1 node = new Node5_1();
        node.label = preorder.pop();
        if (inorder.length() <= 1)
            return node;
        int index = inorder.indexOf(node.label);
        if (index > 0)
            node.left = construct(preorder, inorder.substring(0, index));
        if (index < inorder.length() - 1)
            node.right = construct(preorder, inorder.substring(index + 1));
        return node;
    }

    public String report() {
        if (root == null)
            return "";
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
                builder.append(reversed ? buffer.reverse() : buffer);
                reversed = !reversed;
                buffer.setLength(0);
            } else {
                if (node.left != null) {
                    buffer.append(node.left.label);
                    queue.push(node.left);
                }
                if (node.right != null) {
                    buffer.append(node.right.label);
                    queue.push(node.right);
                }
            }
        }
        return builder.toString();
    }

    public Node5_1 get() {
        return root;
    }
}
