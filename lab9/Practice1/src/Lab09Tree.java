import java.util.LinkedList;

public class Lab09Tree {
    private LinkedNode root;

    private class LinkedNode {
        public LinkedNode parent;
        private LinkedNode left;
        private LinkedNode right;
        private Lab09Node self;

        public LinkedNode(Lab09Node me) {
            self = me;
            if (me != null) {
                if (me.left != null)
                    left = new LinkedNode(me.left, this);
                else
                    left = null;
                if (me.right != null)
                    right = new LinkedNode(me.right, this);
                else
                    right = null;
            }
            parent = null;
        }

        public LinkedNode(int v) {
            self = new Lab09Node(v);
            left = null;
            right = null;
            parent = null;
        }

        public LinkedNode(Lab09Node me, LinkedNode p) {
            self = me;
            if (me != null) {
                if (me.left != null)
                    left = new LinkedNode(me.left, this);
                else
                    left = null;
                if (me.right != null)
                    right = new LinkedNode(me.right, this);
                else
                    right = null;
            }
            parent = p;
        }

        public LinkedNode(int v, LinkedNode p) {
            self = new Lab09Node(v);
            left = null;
            right = null;
            parent = p;
        }

        public LinkedNode getLeft() {
            return left;
        }

        public LinkedNode getRight() {
            return right;
        }

        public int getVal() {
            return self.val;
        }

        public void setChild(Lab09Node node) {
            if (node.val <= self.val)
                left = new LinkedNode(node, this);
            else
                right = new LinkedNode(node, this);
        }

        public void setChild(LinkedNode node) {
            if (node.getVal() <= self.val)
                left = node;
            else
                right = node;
            node.parent = this;
        }

        public void setLeft(LinkedNode node) {
            left = node;
            if (node != null)
                node.parent = this;
        }

        public void setRight(LinkedNode node) {
            right = node;
            if (node != null)
                node.parent = this;
        }

        public void setChild(int v) {
            if (v <= self.val)
                left = new LinkedNode(v, this);
            else
                right = new LinkedNode(v, this);
        }

        public void setLeft(int v) {
            left = new LinkedNode(v, this);
        }

        public void setRight(int v) {
            right = new LinkedNode(v, this);
        }
    }

    public Lab09Tree() {
        root = null;
    }

    public void insert(int v) {
        if (root == null)
            root = new LinkedNode(v);
        else
            insert(v, root);
    }

    private void insert(int v, LinkedNode n) {
        if (v <= n.getVal()) {
            if (n.getLeft() == null)
                n.setLeft(v);
            else
                insert(v, n.getLeft());
        } else {
            if (n.getRight() == null)
                n.setRight(v);
            else
                insert(v, n.getRight());
        }
    }

    public void remove(int v) {
        remove(v, root);
    }

    private void remove(int v, LinkedNode n) {
        if (v == n.getVal()) {
            LinkedNode s = smallest(n.getRight());
            s.setLeft(n.left);
            s.setRight(n.right);
            s.parent.setLeft(null);
            s.parent = n.parent;
            n = null;
            s.parent.setChild(s);
        } else if (v < n.getVal())
            remove(v, n.getLeft());
        else
            remove(v, n.getRight());
    }

    private LinkedNode smallest(LinkedNode n) {
        if (n.getLeft() == null)
            return n;
        else
            return smallest(n.getLeft());
    }

    public String preorder() {
        return preorder(root);
    }

    public String inorder() {
        return inorder(root);
    }

    public String postorder() {
        return postorder(root);
    }

    public String levelorder() {
        return "";
    }

    public String printSideways() {
        return printSideways(root, "");
    }

    private String preorder(LinkedNode n) {
        String s = "";
        s += n.getVal();
        if (n.getLeft() != null)
            s += " " + preorder(n.getLeft());
        if (n.getRight() != null)
            s += " " + preorder(n.getRight());
        return s;
    }

    private String inorder(LinkedNode n) {
        String s = "";
        if (n.getLeft() != null)
            s += inorder(n.getLeft()) + " ";
        s += n.getVal();
        if (n.getRight() != null)
            s += " " + inorder(n.getRight());
        return s;
    }

    private String postorder(LinkedNode n) {
        String s = "";
        if (n.getLeft() != null)
            s += postorder(n.getLeft()) + " ";
        if (n.getRight() != null)
            s += postorder(n.getRight()) + " ";
        s += n.getVal();
        return s;
    }

    private String levelorderR() {
        return "";
    }

    private String printSideways(LinkedNode n, String indent) {
        return "";
    }
}
