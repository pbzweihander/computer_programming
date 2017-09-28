import java.util.Scanner;

public class palindrome {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("input: ");
        String line = scan.nextLine();
        line = line.trim();

        System.out.println(isPalindrome(line));
    }

    private static boolean isPalindrome(String s) {
        int i = 0;
        while (i < s.length() / 2) {
            if (s.charAt(i) != s.charAt(s.length() - i - 1))
                return false;
            i++;
        }
        return true;
    }
}
