import java.util.Arrays;
import java.util.Scanner;

public class anagram {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Input1: ");
        String s1 = scan.nextLine();
        s1 = s1.trim();
        System.out.print("Input2: ");
        String s2 = scan.nextLine();
        s2 = s2.trim();

        System.out.println(isAnagram(s1, s2));
    }

    private static boolean isAnagram(String s1, String s2) {
        s1 = sortString(s1.replaceAll(" ", "").toLowerCase());
        s2 = sortString(s2.replaceAll(" ", "").toLowerCase());
        return s1.equals(s2);
    }

    private static String sortString(String s) {
        char[] c = s.toCharArray();
        Arrays.sort(c);
        return new String(c);
    }
}
