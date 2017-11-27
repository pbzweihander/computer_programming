import java.lang.StringIndexOutOfBoundsException;
import java.lang.IndexOutOfBoundsException;

public class LinkedString implements LinkedStringInterface {
    private class CharacterNode {
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
    }

    private CharacterNode root;
    private CharacterNode tail;

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
        tail.next = str.root;
        tail = str.tail;
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

    public static int[] getPi(char[] pattern) {
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
        char[] pattern = substr.toCharArray();
        int pattern_len = pattern.length;
        if (substr.isEmpty() || isEmpty() || pattern_len > length())
            return;
        CharacterNode str_cursor = new CharacterNode('\0');
        str_cursor.next = root;
        int pattern_cursor = 0;
        while (str_cursor.next != null) {
            if (str_cursor.next.value == pattern[pattern_cursor]) {
                CharacterNode str_matching_node = str_cursor.next.next;
                pattern_cursor++;
                int count = 1;
                while (str_matching_node != null) {
                    if (str_matching_node.value == pattern[pattern_cursor]) {
                        count++;
                        if (count == pattern_len) {
                            str_cursor.next = str_matching_node.next;
                            remove(substr);
                            return;
                        }
                        str_matching_node = str_matching_node.next;
                        pattern_cursor++;
                    } else
                        break;
                }
                str_cursor = str_cursor.next;
                pattern_cursor = 0;
            } else
                str_cursor = str_cursor.next;
        }
    }

    public void remove(LinkedStringInterface substr) {
        LinkedString pattern = new LinkedString(substr);
        int pattern_len = pattern.length();
        if (pattern.isEmpty() || isEmpty() || pattern_len > length())
            return;
        CharacterNode str_cursor = new CharacterNode('\0');
        str_cursor.next = root;
        CharacterNode pattern_cursor = pattern.root;
        while (str_cursor.next != null) {
            if (str_cursor.next.value == pattern_cursor.value) {
                CharacterNode str_matching_node = str_cursor.next.next;
                pattern_cursor = pattern_cursor.next;
                int count = 1;
                while (str_matching_node != null) {
                    if (str_matching_node.value == pattern_cursor.value) {
                        count++;
                        if (count == pattern_len) {
                            str_cursor.next = str_matching_node.next;
                            remove(substr);
                            return;
                        }
                        str_matching_node = str_matching_node.next;
                        pattern_cursor = pattern_cursor.next;
                    } else
                        break;
                }
                str_cursor = str_cursor.next;
                pattern_cursor = pattern.root;
            } else
                str_cursor = str_cursor.next;
        }
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

    private CharacterNode nodeAt(int index) {
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

    private String makeSubstringExceptionMessage(int startIndex, int endIndex) {
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
                int j = i + 1;
                while (matching_node.value == pattern[j]) {
                    matching_node = matching_node.next;
                    j++;
                    if (j == pattern.length)
                        return true;
                    if (matching_node == null)
                        return false;
                }
                i = pi[j - 1];
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
}
