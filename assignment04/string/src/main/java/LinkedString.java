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
        char[] arr = str.toCharArray();
        for (char c : arr)
            push(c);
    }

    public LinkedString(LinkedStringInterface str) {
        this(str.toString());
    }

    public LinkedString(char[] str) {
        root = null;
        tail = null;
        for (char c : str)
            push(c);
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

    public boolean isEmpty() {
        return root == null;
    }

    protected String makeSubstringExceptionMessage(int startIndex, int endIndex) {
        return "begin " + startIndex + ", end " + endIndex + ", length " + length();
    }

    protected String makeIndexOutExceptionMessage(int index) {
        return "String index out of range: " + index;
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
        int length = pattern.length;
        int[] pi = new int[length];
        int i = 1, j = 0;
        pi[0] = 0;
        while (i < length) {
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
        if (substr.isEmpty() || isEmpty())
            return;
        int s_length = length();
        int p_length = substr.length();
        if (s_length < p_length)
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
                while (j < p_length && node != null && node.value == pattern[j]) {
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
        char[] arr = new char[length()];
        CharacterNode node = root;
        int i = 0;
        while (node != null) {
            arr[i++] = node.value;
            node = node.next;
        }
        return new String(arr);
    }

    public char charAt(int index) {
        if (index < 0)
            throw new IndexOutOfBoundsException(makeIndexOutExceptionMessage(index));
        CharacterNode node = root;
        while (index-- > 0) {
            node = node.next;
            if (node == null)
                throw new IndexOutOfBoundsException(makeIndexOutExceptionMessage(index));
        }
        return node.value;
    }

    public LinkedStringInterface substring(int startIndex, int endIndex) {
        if (startIndex < 0 || endIndex < 0 || startIndex > endIndex)
            throw new StringIndexOutOfBoundsException(makeSubstringExceptionMessage(startIndex, endIndex));
        if (isEmpty()) {
            if (startIndex == 0 && endIndex == 0)
                return new LinkedString();
            else
                throw new StringIndexOutOfBoundsException(makeSubstringExceptionMessage(startIndex, endIndex));
        }
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
        int p_length = substr.length();
        if (s_length < p_length)
            return false;
        char[] pattern = substr.toCharArray();
        int[] pi = getPi(pattern);
        CharacterNode node = root;
        int i = 0;
        while (node != null) {
            if (node.value == pattern[i]) {
                CharacterNode matching_node = node.next;
                i++;
                while (i < p_length && matching_node != null && matching_node.value == pattern[i]) {
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

    public int indexOf(char c) {
        if (isEmpty())
            return -1;
        CharacterNode node = root;
        int cursor = 0;
        while (node != null && node.value != c) {
            node = node.next;
            cursor++;
        }
        if (node != null)
            return cursor;
        return -1;
    }

    public int indexOf(String substr) {
        if (substr.isEmpty())
            return 0;
        if (isEmpty())
            return -1;
        char[] pattern = substr.toCharArray();
        int[] pi = getPi(pattern);
        CharacterNode node = root;
        int start_of_pattern_cursor = 0;
        int where_is_node = 0; // TODO: debug
        int i = 0;
        while (node != null) {
            if (node.value == pattern[i]) {
                node = node.next;
                where_is_node++; // TODO: debug
                int j = i + 1;
                while (j < pattern.length && node != null && node.value == pattern[j]) {
                    node = node.next;
                    where_is_node++;
                    j++;
                }
                if (node == null && j < pattern.length)
                    return -1;
                else if (j == pattern.length) {
                    // System.out.println("" + where_is_node + " : " + start_of_pattern_cursor); // TODO: debug
                    return start_of_pattern_cursor;
                } else {
                    start_of_pattern_cursor += j - pi[j - 1];
                    i = pi[j - 1];
                }
            } else {
                node = node.next;
                where_is_node++; // TODO: debug
                start_of_pattern_cursor++;
            }
        }
        return -1;
    }

    public int compareTo(LinkedStringInterface str) {
        return compareTo(str.toString());
    }

    public int compareTo(String str) {
        int my_length = 0;
        char[] str_array = str.toCharArray();
        CharacterNode node = root;
        for (char c : str_array) {
            if (node == null)
                return my_length - str_array.length;
            int diff = node.value - c;
            if (diff != 0)
                return diff;
            node = node.next;
            my_length++;
        }
        while (node != null) {
            node = node.next;
            my_length++;
        }
        return my_length - str_array.length;
    }

    public int compareToIgnoreCase(LinkedStringInterface str) {
        return compareTo(str.toString());
    }

    public int compareToIgnoreCase(String str) {
        int my_length = 0;
        char[] str_array = str.toCharArray();
        CharacterNode node = root;
        for (char c : str_array) {
            if (node == null)
                return my_length - str_array.length;
            int diff = Character.toLowerCase(node.value) - Character.toLowerCase(c);
            if (diff != 0)
                return diff;
            node = node.next;
            my_length++;
        }
        while (node != null) {
            node = node.next;
            my_length++;
        }
        return my_length - str_array.length;
    }

    public LinkedStringInterface concat(LinkedStringInterface str) {
        return concat(str.toString());
    }

    public LinkedStringInterface concat(String str) {
        LinkedString new_str = new LinkedString(this);
        char[] arr = str.toCharArray();
        for (char c : arr)
            new_str.push(c);
        return new_str;
    }

    public LinkedStringInterface replace(char a, char b) {
        LinkedString new_str = new LinkedString(this);
        CharacterNode node = new_str.root;
        while (node != null) {
            if (node.value == a)
                node.value = b;
            node = node.next;
        }
        return new_str;
    }
}
