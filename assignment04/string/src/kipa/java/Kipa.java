public class Kipa implements LinkedStringInterface {
    private String s;

    public Kipa(String v) {
        s = v;
    }

    public void remove(String substr) {
        Unimplemented();
    }

    public void remove(LinkedStringInterface substr) {
        Unimplemented();
    }

    public String toString() {
        return s;
    }

    public char charAt(int index) {
        return s.charAt(index);
    }

    public int length() {
        return s.length();
    }

    public LinkedStringInterface substring(int startIndex, int endIndex) {
        return new Kipa(s.substring(startIndex, endIndex));
    }

    public boolean contains(LinkedStringInterface substr) {
        return (Boolean) Unimplemented();
    }

    public boolean contains(String substr) {
        return s.contains(substr);
    }

    public int compareTo(LinkedStringInterface str) {
        return (Integer) Unimplemented();
    }

    public int compareTo(String str) {
        return s.compareTo(str);
    }

    public int compareToIgnoreCase(LinkedStringInterface str) {
        return (Integer) Unimplemented();
    }

    public int compareToIgnoreCase(String str) {
        return s.compareToIgnoreCase(str);
    }

    public LinkedStringInterface concat(LinkedStringInterface str) {
        return (LinkedStringInterface) Unimplemented();
    }

    public LinkedStringInterface concat(String str) {
        return new Kipa(s + str);
    }

    public LinkedStringInterface replace(char old, char n) {
        return new Kipa(s.replace(old, n));
    }

    public int indexOf(char c) {
        return s.indexOf(c);
    }

    public int indexOf(String v) {
        return s.indexOf(v);
    }

    private Object Unimplemented() {
        return (Integer) (1 / 0);
    }

}
