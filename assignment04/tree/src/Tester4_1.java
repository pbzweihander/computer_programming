public class Tester4_1 {
    public static void main(String[] args) {
        Assignment4_1 a = new Assignment4_1("00001101111", "ABCDE");
        Node4_1 r = a.getRoot();
        //reports in bits
        System.out.println(preorder(r));
        System.out.println(inorder(r));
        System.out.println(postorder(r));
        //reports in character
        System.out.println(preorderLetter(r));
        System.out.println(inorderLetter(r));
        System.out.println(postorderLetter(r));

    }

    public static String preorder(Node4_1 n) {
        String s = "" + n.label;
        if (n.left != null)
            s += preorder(n.left);
        if (n.right != null)
            s += preorder(n.right);
        return s;
    }

    public static String inorder(Node4_1 n) {
        String s = "";
        if (n.left != null)
            s += inorder(n.left);
        s += n.label;
        if (n.right != null)
            s += inorder(n.right);
        return s;
    }

    public static String postorder(Node4_1 n) {
        String s = "";
        if (n.left != null)
            s += preorder(n.left);
        if (n.right != null)
            s += preorder(n.right);
        s += n.label;
        return s;
    }

    public static String preorderLetter(Node4_1 n) {
        String s = "";
        s += n.character;
        if (n.left != null)
            s += preorderLetter(n.left);
        if (n.right != null)
            s += preorderLetter(n.right);
        return s;
    }

    public static String inorderLetter(Node4_1 n) {
        String s = "";
        if (n.left != null)
            s += inorderLetter(n.left);
        s += n.character;
        if (n.right != null)
            s += inorderLetter(n.right);
        return s;
    }

    public static String postorderLetter(Node4_1 n) {
        String s = "";
        if (n.left != null)
            s += postorderLetter(n.left);
        if (n.right != null)
            s += postorderLetter(n.right);
        s += n.character;
        return s;
    }

}
