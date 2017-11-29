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

    private class EmptyHeadCharacterNode extends CharacterNode {
        public EmptyHeadCharacterNode(CharacterNode next) {
            super('\0');
            this.next = next;
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
        int[] pi = new int[pattern.length];
        int pos = 1, cnd = 0;
        pi[0] = -1;

        while (pos < pattern.length) {
            if (pattern[pos] == pattern[cnd])
                pi[pos++] = pi[cnd++];
            else {
                pi[pos] = cnd;
                cnd = pi[cnd];

                while (cnd >= 0 && pattern[pos] != pattern[cnd])
                    cnd = pi[cnd];

                pos++;
                cnd++;
            }
        }
        return pi;
    }

    public void remove(String substr) {
        char[] pattern = substr.toCharArray();
        if (pattern.length == 0 || isEmpty())
            return;
        int[] pi = getPi(pattern);
        CharacterNode m_node = new EmptyHeadCharacterNode(root);
        CharacterNode m_plus_i_node = root;
        int i = 0;

        while (m_plus_i_node != null) {
            if (pattern[i] == m_plus_i_node.value) {
                m_plus_i_node = m_plus_i_node.next;
                i++;
                if (i == pattern.length) {
                    if (m_node instanceof EmptyHeadCharacterNode)
                        root = m_plus_i_node;
                    else
                        m_node.next = m_plus_i_node;
                    m_node = new EmptyHeadCharacterNode(root);
                    m_plus_i_node = root;
                    i = 0;
                }
            } else {
                try {
                    m_node = m_node.after(i - pi[i]);
                    if (pi[i] > -1) {
                        m_plus_i_node = m_node.after(pi[i] + 1);
                        i = pi[i];
                    } else {
                        m_plus_i_node = m_node.next;
                        i = 0;
                    }
                } catch (IndexOutOfBoundsException e) {
                    return;
                }
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
        return indexOf(substr) > -1;
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
        char[] pattern = substr.toCharArray();
        if (pattern.length == 0)
            return 0;
        if (isEmpty())
            return -1;
        int[] pi = getPi(pattern);
        CharacterNode m_node = root, m_plus_i_node = root;
        int m = 0, i = 0;

        while (m_plus_i_node != null) {
            if (pattern[i] == m_plus_i_node.value) {
                m_plus_i_node = m_plus_i_node.next;
                i++;
                if (i == pattern.length)
                    return m;
            } else {
                try {
                    m_node = m_node.after(i - pi[i]);
                    m += i - pi[i];
                    if (pi[i] > -1) {
                        m_plus_i_node = m_node.after(pi[i]);
                        i = pi[i];
                    } else {
                        m_plus_i_node = m_node;
                        i = 0;
                    }
                } catch (IndexOutOfBoundsException e) {
                    return -1;
                }
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
        return compareToIgnoreCase(str.toString());
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
