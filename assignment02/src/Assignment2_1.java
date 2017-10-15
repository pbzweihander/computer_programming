import java.util.Scanner;

public class Assignment2_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter string : ");
        String string = scanner.nextLine();
        System.out.print("Enter operation : ");
        String operation = scanner.next();
        System.out.print("Enter key value : ");
        int key = scanner.nextInt();

        StringBuffer out = new StringBuffer();
        for (char c: string.toCharArray()) {
            char e = '\0';
            if (operation.equals("e"))
                e = encrypt(c, key);
            else if (operation.equals("d"))
                e = encrypt(c, -key);
            else {
                System.out.printf("Error, input operation %s is invalid\n", operation);
                return;
            }
            if (e == '\0') {
                System.out.printf("Error, input value %c is out of range\n", c);
                return;
            }
            out.append(e);
        }
        System.out.println(out);
    }

    private static char encrypt(char c, int key) {
        char[] ca = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789".toCharArray();
        int e = 0;
        boolean b = false;
        for (int i = 0; i < ca.length; i++)
            if (ca[i] == c) {
                e = i;
                b = true;
                break;
            }
        if (!b)
            return '\0';
        e = clamp(e + key, ca.length);
        return ca[e];
    }

    private static int clamp(int i, int range) {
        while (i < 0)
            i += range;
        return i % range;
    }
}
