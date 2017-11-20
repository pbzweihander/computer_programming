public interface LinkedStringInterface {
    void remove(String substr);
    void remove(LinkedStringInterface substr);
    String toString();
    char charAt(int index);
    int length();
    LinkedStringInterface substring(int startIndex, int endIndex);
    boolean contains(LinkedStringInterface substr);
    boolean contains(String substr);
    int compareTo(LinkedStringInterface str);  int compareTo(String str);
    int compareToIgnoreCase(LinkedStringInterface str);  int compareToIgnoreCase(String str);
    LinkedStringInterface concat(LinkedStringInterface str);
    LinkedStringInterface concat(String str);
}
