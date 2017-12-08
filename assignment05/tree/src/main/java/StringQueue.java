public class StringQueue extends Queue<Character> {
    public StringQueue() {
        super();
    }

    public StringQueue(String str) {
        this();
        for (char c : str.toCharArray())
            push(c);
    }

    public String toString() {
        StringBuilder b = new StringBuilder();
        for (Node n = root; n != null; n = n.next)
            b.append(n.value);
        return b.toString();
    }
}
