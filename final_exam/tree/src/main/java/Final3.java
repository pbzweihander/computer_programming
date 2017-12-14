public class Final3 {
    public Node root;

    public Final3(String s) {
        if (s.isEmpty()) {
            root = null;
            return;
        }
        root = decode(s.charAt(0), s.substring(1, s.length() - 2));
    }

    private Node decode(char label, String dna) {
        Node node = new Node();
        node.label = label;
        if (!dna.isEmpty()) {
            char left_label = dna.charAt(0);
            int left_start = dna.indexOf(left_label) + 1;
            int left_end = dna.lastIndexOf(left_label);
            String left_substr;
            if (left_start < left_end)
                left_substr = dna.substring(left_start, left_end);
            else
                left_substr = "";

            char right_label = dna.charAt(dna.length() - 1);
            int right_start = dna.indexOf(right_label) + 1;
            int right_end = dna.lastIndexOf(right_label);
            String right_substr;
            if (right_start < right_end)
                right_substr = dna.substring(right_start, right_end);
            else
                right_substr = "";

            node.left = decode(left_label, left_substr);
            node.right = decode(right_label, right_substr);
        }
        return node;
    }

    public Node getRoot() {
        return root;
    }

    public String encode(Node node) {
        if (node == null)
            return "";
        StringBuilder builder = new StringBuilder();
        builder.append(node.label);
        if (node.left != null && node.right != null) {
            builder.append(encode(node.left));
            builder.append(encode(node.right));
        }
        builder.append(node.label);
        return builder.toString();
    }
}
