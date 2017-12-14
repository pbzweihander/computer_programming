public class Final3Test {
    public static void main(String[] args) {
        Final3 tree = new Final3("ABDFFGGDEEBCCA");
        if (inorder(tree.getRoot()).compareTo("FDGBEAC") == 0)
            System.out.println("match1");
        else
            System.out.println("nope1");

        Final3 tree2 = new Final3("");
        if (tree2.encode(tree.getRoot()).compareTo("ABDFFGGDEEBCCA") == 0)
            System.out.println("match2");
        else
            System.out.println("nope2");

        if (tree2.encode(tree2.getRoot()).compareTo("") == 0)
            System.out.println("match3");
        else
            System.out.println("nope3");
    }

    public static String inorder(Node n) {
        String s = "";
        if (n.left != null)
            s += inorder(n.left);
        s += n.label;
        if (n.right != null)
            s += inorder(n.right);
        return s;
    }
}
