public class LinkedString implements LinkedStringInterface {
    private class LinkedList<T> {
        private class Node<T> {
            public T value;
            public Node<T> next;

            public Node(T value) {
                this.value = value;
                next = null;
            }
        }

        Node<T> root;

        public LinkedList() {
            root = null;
        }

        public void attach(T element) {
            if (root == null)
                root = new Node<>(element);
            else {
                Node<T> node = root;
                while (node.next != null)
                    node = node.next;
                node.next = new Node<>(element);
            }
        }
    }

    private LinkedList<Character> char_array;

    public LinkedString() {
        char_array = new LinkedList<>();
    }

    public LinkedString(String str) {
        char_array = new LinkedList<>();
        for (char c : str.toCharArray()) {
            char_array.attach(c);
        }
    }

    public void remove(String substr) {
        // TODO: remove of String
    }

    public void remove(LinkedStringInterface substr) {
        // TODO: remove of LinkedString
    }

    public String toString() {
        return ""; // TODO: toString
    }

    public char charAt(int index) {
        return '\0'; // TODO: charAt
    }

    public int length() {
        return 0; // TODO: length
    }

    public LinkedStringInterface substring(int startIndex, int endIndex) {
        return null; // TODO: substring
    }

    public boolean contains(LinkedStringInterface substr) {
        return false; // TODO: contains of LinkedString
    }

    public boolean contains(String substr) {
        return false; // TODO: conatains of String
    }

    public int compareTo(LinkedStringInterface str) {
        return 0; // TODO: compareTo of LinkedString
    }

    public int compareTo(String str) {
        return 0; // TODO: compareTo of String
    }

    public int compareToIgnoreCase(LinkedStringInterface str) {
        return 0; // TODO: compareToIgnoreCase of LinkedString
    }

    public int compareToIgnoreCase(String str) {
        return 0; // TODO: compareToIgnoreCase of String
    }

    public LinkedStringInterface concat(LinkedStringInterface str) {
        return null; // TODO: concat of LinkedString
    }

    public LinkedStringInterface concat(String str) {
        return null; // TODO: concat of String
    }
}
