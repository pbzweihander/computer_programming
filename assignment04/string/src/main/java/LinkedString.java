import java.lang.StringIndexOutOfBoundsException;

public class LinkedString implements LinkedStringInterface {
    private class CharacterNode {
        public char value;
        public CharacterNode next;

        public CharacterNode(char value) {
            this.value = value;
            next = null;
        }
    }

    private CharacterNode root;

    public LinkedString() {
        root = null;
    }

    public LinkedString(String str) {
        root = null;
        for (char c : str.toCharArray()) {
            push(c);
        }
    }

    public LinkedString(LinkedString str) {
        root = null;
        for (char c : str.toCharArray()) {
            push(c);
        }
    }

    public LinkedString(char[] str) {
        root = null;
        for (char c : str) {
            push(c);
        }
    }

    public LinkedString(byte[] str) {
        root = null;
        for (byte b : str) {
            push((char) b);
        }
    }

    public void push(char element) {
        if (root == null)
            root = new CharacterNode(element);
        else {
            CharacterNode node = root;
            while (node.next != null)
                node = node.next;
            node.next = new CharacterNode(element);
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public char[] toCharArray() {
        char[] arr = new char[length()];
        int i = 0;
        CharacterNode node = root;
        while (node != null) {
            arr[i++] = node.value;
            node = node.next;
        }
        return arr;
    }

    public int length() {
        CharacterNode node = root;
        int i = 0;
        while (node != null) {
            node = node.next;
            i++;
        }
        return i;
    }

    public void remove(String substr) {
        // TODO: remove of String
    }

    public void remove(LinkedStringInterface substr) {
        // TODO: remove of LinkedString
    }

    public String toString() {
        return toStringRec(root);
    }

    private String toStringRec(CharacterNode node) {
        return "" + node.value + (node.next != null ? toStringRec(node.next) : "");
    }

    public char charAt(int index) {
        CharacterNode node = root;
        while (index-- > 0) {
            node = node.next;
            if (node == null)
                throw new StringIndexOutOfBoundsException("String index out of range: " + index);
        }
        return node.value;
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

    public LinkedString toLowerCase() {
        LinkedString new_string = new LinkedString();
        for (char c : this.toCharArray())
            new_string.push(Character.toLowerCase(c));
        return new_string;
    }

    public LinkedString toUpperCase() {
        LinkedString new_string = new LinkedString();
        for (char c : this.toCharArray())
            new_string.push(Character.toUpperCase(c));
        return new_string;
    }
}
