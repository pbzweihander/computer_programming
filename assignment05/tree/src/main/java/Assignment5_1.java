public class Assignment5_1 {
    private class StringList {
        private class CharacterNode {
            public char value;
            public CharacterNode next;

            public CharacterNode(char c) {
                value = c;
                next = null;
            }
        }

        private CharacterNode root;
        private CharacterNode tail;

    }

    public Node5_1 root;

    public Assignment5_1(String preorder, String inorder) {
        if (preorder.isEmpty()) {
            root = null;
        } else {
        }
    }

    private Node5_1 construct(String preorder, String inorder) {
        Node5_1 node = new Node5_1();
        node.label = preorder.charAt(0);
        node.left = construct(preorder.substring(1), inorder.split("" + node.label)[0]);
    }

    public String report() {
        return "";
    }

    public Node5_1 get() {
        return root;
    }
}
