import java.lang.StringIndexOutOfBoundsException;
import java.lang.IndexOutOfBoundsException;

public class LinkedString implements LinkedStringInterface {
    protected class CharacterNode {
        public char value;
        public CharacterNode next;

        public CharacterNode(char value) {
            this.value = value;
            next = null;
        }

        public LinkedString take(int length) {
            int i = 0;
            CharacterNode node = this;
            LinkedString arr = new LinkedString();
            while (i++ < length) {
                if (node == null)
                    throw new IndexOutOfBoundsException();
                arr.push(node.value);
                node = node.next;
            }
            return arr;
        }

        public CharacterNode after(int index) {
            int i = 0;
            CharacterNode node = this;
            while (i++ < index && node != null)
                node = node.next;
            if (node == null)
                throw new IndexOutOfBoundsException();
            return node;
        }
    }

    protected CharacterNode root;
    protected CharacterNode tail;

    public LinkedString() {
        root = null;
        tail = null;
    }

    public LinkedString(String str) {
        root = null;
        tail = null;
        for (char c : str.toCharArray()) {
            push(c);
        }
    }

    public LinkedString(LinkedString str) {
        root = null;
        tail = null;
        for (char c : str.toCharArray()) {
            push(c);
        }
    }

    public LinkedString(LinkedStringInterface str) {
        this(str.toString());
    }

    public LinkedString(char[] str) {
        root = null;
        tail = null;
        for (char c : str) {
            push(c);
        }
    }

    public LinkedString(byte[] str) {
        root = null;
        tail = null;
        for (byte b : str) {
            push((char) b);
        }
    }

    public void push(char element) {
        if (root == null) {
            root = new CharacterNode(element);
            tail = root;
        } else {
            tail.next = new CharacterNode(element);
            tail = tail.next;
        }
    }

    public void append(LinkedString str) {
        if (root == null) {
            root = str.root;
            tail = str.tail;
        } else {
            tail.next = str.root;
            tail = str.tail;
        }
    }

    public void append(String str) {
        for (char c : str.toCharArray())
            push(c);
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

    protected static int[] getPi(char[] pattern) {
        int[] pi = new int[pattern.length];
        int i = 1, j = 0;
        pi[0] = 0;
        while (i < pattern.length) {
            if (pattern[i] == pattern[j]) {
                pi[i] = j + 1;
                i++;
                j++;
            } else {
                if (j > 0)
                    j = pi[j - 1];
                else
                    i++;
            }
        }
        return pi;
    }

    public void remove(String substr) {
        System.out.println("doh!");
        int s_length = length();
        if (substr.isEmpty() || isEmpty() || s_length < substr.length())
            return;
        char[] pattern = substr.toCharArray();
        int[] pi = getPi(pattern);
        CharacterNode node = root;
        CharacterNode start_of_pattern_node = new CharacterNode('\0');
        start_of_pattern_node.next = root;
        int i = 0;
        while (node != null) {
            if (node.value == pattern[i]) {
                node = node.next;
                int j = i + 1;
                while (j < pattern.length && node != null && node.value == pattern[j]) {
                    node = node.next;
                    j++;
                }
                if (j == pattern.length) {
                    if (start_of_pattern_node.next == root)
                        root = node;
                    else
                        start_of_pattern_node.next = node;
                    i = 0;
                    node = root;
                    start_of_pattern_node = new CharacterNode('\0');
                    start_of_pattern_node.next = root;
                } else if (node == null)
                    return;
                else {
                    start_of_pattern_node = start_of_pattern_node.after(j - pi[j - 1]);
                    i = pi[j - 1];
                }
            } else {
                node = node.next;
                start_of_pattern_node = start_of_pattern_node.next;
            }
        }
    }

    public void remove(LinkedStringInterface substr) {
        remove(substr.toString());
    }

    public String toString() {
        if (isEmpty())
            return "";
        StringBuilder builder = new StringBuilder();
        CharacterNode node = root;
        while (node != null) {
            builder.append(node.value);
            node = node.next;
        }
        return builder.toString();
    }

    protected CharacterNode nodeAt(int index) {
        CharacterNode node = root;
        while (index-- > 0) {
            node = node.next;
            if (node == null) {
                StringBuilder builder = new StringBuilder();
                builder.append("Index out of range: ");
                builder.append(index);
                throw new IndexOutOfBoundsException(builder.toString());
            }
        }
        return node;
    }

    public char charAt(int index) {
        try {
            return nodeAt(index).value;
        } catch (IndexOutOfBoundsException e) {
            StringBuilder builder = new StringBuilder();
            builder.append("String index out of range: ");
            builder.append(index);
            throw new StringIndexOutOfBoundsException(builder.toString());
        }
    }

    protected String makeSubstringExceptionMessage(int startIndex, int endIndex) {
        StringBuilder builder = new StringBuilder();
        builder.append("begin ");
        builder.append(startIndex);
        builder.append(", end ");
        builder.append(endIndex);
        builder.append(", length ");
        builder.append(length());
        return builder.toString();
    }

    public LinkedStringInterface substring(int startIndex, int endIndex) {
        if (isEmpty())
            return new LinkedString();
        CharacterNode node = root;
        int i = 0;
        while (i++ < startIndex) {
            if (node == null)
                throw new StringIndexOutOfBoundsException(makeSubstringExceptionMessage(startIndex, endIndex));
            node = node.next;
        }
        try {
            return node.take(endIndex - startIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new StringIndexOutOfBoundsException(makeSubstringExceptionMessage(startIndex, endIndex));
        }
    }

    public boolean contains(LinkedStringInterface substr) {
        return contains(substr.toString());
    }

    public boolean contains(String substr) {
        if (substr.isEmpty())
            return true;
        if (isEmpty())
            return false;
        int s_length = length();
        if (s_length < substr.length())
            return false;
        char[] pattern = substr.toCharArray();
        int[] pi = getPi(pattern);
        CharacterNode node = root;
        int i = 0;
        while (node != null) {
            if (node.value == pattern[i]) {
                CharacterNode matching_node = node.next;
                i++;
                while (i < pattern.length && matching_node != null && matching_node.value == pattern[i]) {
                    matching_node = matching_node.next;
                    i++;
                }
                if (i == pattern.length)
                    return true;
                if (matching_node == null)
                    return false;
                i = pi[i - 1];
                node = matching_node;
            } else
                node = node.next;
        }
        return false;
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
        LinkedString new_str = new LinkedString(this);
        new_str.append(new LinkedString(str));
        return new_str;
    }

    public LinkedStringInterface concat(String str) {
        LinkedString new_str = new LinkedString(this);
        for (char c : str.toCharArray()) {
            new_str.push(c);
        }
        return new_str;
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

    public void clear() {
        root = null;
        tail = null;
    }
}
