public class Assignment4_2 {
    public Node4_1 root;

    public Assignment4_2(String s1, String s2) {
        root = new Node4_1();
        makeNode(s1, root);
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

    public String report_bits_preorder() {

    }

    public String report_bits_levelorder() {

    }

    public String report_preorder() {

    }

    public String report_levelorder() {

    }
}
