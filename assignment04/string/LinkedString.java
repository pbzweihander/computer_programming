import java.lang.Iterable;
import java.lang.IndexOutOfBoundsException;
import java.lang.StringIndexOutOfBoundsException;

public class LinkedString extends LinkedList<Character> implements LinkedStringInterface, Iterable<Character> {
    public LinkedString() {
        super();
    }

    public LinkedString(String str) {
        super();
        for (char c : str.toCharArray()) {
            attach(c);
        }
    }

    public LinkedString(LinkedString str) {
        super();
        for (char c : str) {
            attach(c);
        }
    }

    public LinkedString(char[] str) {
        super();
        for (char c : str) {
            attach(c);
        }
    }

    public LinkedString(byte[] str) {
        super();
        for (byte b: str) {
            attach((char)b);
        }
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

    private String toStringRec(LinkedListNode<Character> node) {
        return "" + node.value + (node.next != null ? toStringRec(node.next) : "");
    }

    public char charAt(int index) {
        try {
            return nth(index);
        } catch (IndexOutOfBoundsException e) {
            throw new StringIndexOutOfBoundsException("String index out of range: " + index);
        }
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
