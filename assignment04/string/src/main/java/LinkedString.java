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
        for (int i = 0; i < str.length(); i++)
            push(str.charAt(i));
    }

    public LinkedString(LinkedStringInterface str) {
        this(str.toString());
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

    public void append(String str) {
        for (int i = 0; i < str.length(); i++)
            push(str.charAt(i));
    }

    public boolean isEmpty() {
        return root == null;
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

    protected static int[] getPi(String pattern) {
        int length = pattern.length();
        int[] pi = new int[length];
        int i = 1, j = 0;
        pi[0] = 0;
        while (i < length) {
            if (pattern.charAt(i) == pattern.charAt(j)) {
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

    public void remove(String pattern) {
        if (pattern.isEmpty() || isEmpty())
            return;
        int s_length = length();
        int p_length = pattern.length();
        if (s_length < p_length)
            return;
        int[] pi = getPi(pattern);
        CharacterNode node = root;
        CharacterNode start_of_pattern_node = new CharacterNode('\0');
        start_of_pattern_node.next = root;
        int i = 0;
        while (node != null) {
            if (node.value == pattern.charAt(i)) {
                node = node.next;
                int j = i + 1;
                while (j < p_length && node != null && node.value == pattern.charAt(j)) {
                    node = node.next;
                    j++;
                }
                if (j == p_length) {
                    if (start_of_pattern_node.next == root)
                        root = node;
                    else
                        start_of_pattern_node.next = node;
                    s_length -= p_length;
                    if (s_length < p_length)
                        return;
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

    public boolean contains(String pattern) {
        if (pattern.isEmpty())
            return true;
        if (isEmpty())
            return false;
        int s_length = length();
        int p_length = pattern.length();
        if (s_length < p_length)
            return false;
        int[] pi = getPi(pattern);
        CharacterNode node = root;
        int i = 0;
        while (node != null) {
            if (node.value == pattern.charAt(i)) {
                CharacterNode matching_node = node.next;
                i++;
                while (i < p_length && matching_node != null && matching_node.value == pattern.charAt(i)) {
                    matching_node = matching_node.next;
                    i++;
                }
                if (i == p_length)
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
        return compareTo(str.toString());
    }

    public int compareTo(String str) {
        int i = 0;
        int my_length = 0;
        int str_length = str.length();
        CharacterNode node = root;
        while (i < str_length && node != null) {
            if (node.value != str.charAt(i))
                return node.value - str.charAt(i);
            i++;
            node = node.next;
            my_length++;
        }
        if (node != null)
            while (node != null) {
                node = node.next;
                my_length++;
            }
        return my_length - str_length;
    }

    public int compareToIgnoreCase(LinkedStringInterface str) {
        return compareTo(str.toString());
    }

    public int compareToIgnoreCase(String str) {
        int i = 0;
        int my_length = 0;
        int str_length = str.length();
        CharacterNode node = root;
        while (i < str_length && node != null) {
            if (Character.toLowerCase(node.value) != Character.toLowerCase(str.charAt(i)))
                return Character.toLowerCase(node.value) - Character.toLowerCase(str.charAt(i));
            i++;
            node = node.next;
            my_length++;
        }
        if (node != null)
            while (node != null) {
                node = node.next;
                my_length++;
            }
        return my_length - str_length;
    }

    public LinkedStringInterface concat(LinkedStringInterface str) {
        return concat(str.toString());
    }

    public LinkedStringInterface concat(String str) {
        LinkedString new_str = new LinkedString(this);
        for (int i = 0; i < str.length(); i++)
            new_str.push(str.charAt(i));
        return new_str;
    }

    public void clear() {
        root = null;
        tail = null;
    }
}
