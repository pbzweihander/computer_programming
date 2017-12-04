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
        if (isEmpty())
            return "";
        else
            return toStringRec(root);
    }

    private String toStringRec(CharacterNode node) {
        return "" + node.value + (node.next != null ? toStringRec(node.next) : "");
    }

    private CharacterNode nodeAt(int index) {
        CharacterNode node = root;
        while (index-- > 0) {
            node = node.next;
            if (node == null)
                throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        return node;
    }

    public char charAt(int index) {
        try {
            return nodeAt(index).value;
        } catch (IndexOutOfBoundsException e) {
            throw new StringIndexOutOfBoundsException("String index out of range: " + index);
        }
    }

    public LinkedStringInterface substring(int startIndex, int endIndex) {
        if (isEmpty())
            return new LinkedString();
        CharacterNode node = root;
        int i = 0;
        while (i++ < startIndex) {
            if (node == null)
                throw new StringIndexOutOfBoundsException(
                        "begin " + startIndex + ", end " + endIndex + ", length " + length());
            node = node.next;
        }
        try {
            return node.take(endIndex - startIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new StringIndexOutOfBoundsException(
                    "begin " + startIndex + ", end " + endIndex + ", length " + length());
        }
    }

    public boolean contains(LinkedStringInterface substr) {
        LinkedString pattern = (LinkedString) substr;
        if (pattern.isEmpty())
            return true;
        if (isEmpty())
            return false;
        int pattern_len = pattern.length();
        if (pattern_len > length())
            return false;
        CharacterNode str_cursor = root;
        CharacterNode pattern_cursor = pattern.root;
        while (str_cursor != null) {
            if (str_cursor.value == pattern_cursor.value) {
                CharacterNode str_matching_node = str_cursor.next;
                pattern_cursor = pattern_cursor.next;
                int count = 1;
                while (str_matching_node != null) {
                    if (str_matching_node.value == pattern_cursor.value) {
                        count++;
                        if (count == pattern_len)
                            return true;
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
        return false;
    }

    public boolean contains(String substr) {
        if (substr.isEmpty())
            return true;
        if (isEmpty())
            return false;
        char[] pattern = substr.toCharArray();
        int pattern_len = pattern.length;
        if (pattern_len > length())
            return false;
        CharacterNode str_cursor = root;
        int pattern_cursor = 0;
        while (str_cursor != null) {
            if (str_cursor.value == pattern[pattern_cursor]) {
                CharacterNode str_matching_node = str_cursor.next;
                pattern_cursor++;
                int count = 1;
                while (str_matching_node != null) {
                    if (str_matching_node.value == pattern[pattern_cursor]) {
                        count++;
                        if (count == pattern_len)
                            return true;
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
