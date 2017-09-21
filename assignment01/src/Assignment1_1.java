import java.util.Scanner;

public class Assignment1_1 {
    public static void main(String[] args) {
        int n = GetInt();
        int last = 0;
        int[] list = new int[n * 2];
        int count = 0;

        System.out.println("(i)");
        for (int i = 2 - (n % 2); i < n; i += 4) {
            printLine(n, i);
            list[count++] = i;
            last = i;
        }
        printLine(n, n);
        list[count++] = n;

        int middle = last;
        do {
            if (middle < 1)
                middle = 2 - (n % 2);
            printLine(n, middle);
            list[count++] = middle;
            middle -= 4;
        } while (middle >= (float)n / 2f);
        for (int i = middle + 8; i < n; i += 4) {
            printLine(n, i);
            list[count++] = i;
        }
        printLine(n, n);
        list[count++] = n;

        for (int i = last; i >= 1; i -= 4) {
            printLine(n, i);
            list[count++] = i;
        }

        System.out.println("(ii)");
        for (int i = 0; i < count; i++) {
            printBlankLine(n, list[i]);
        }
    }

    private static int GetInt() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Input:");
        return scan.nextInt();
    }

    private static void printLine(int n, int i) {
        for (int j = 0; j < (n - i) / 2; j++)
            System.out.print(" ");
        for (int j = 0; j < i; j++)
            System.out.print("*");
        System.out.println("");
    }

    private static void printBlankLine(int n, int i) {
        for (int j = 0; j < (n - i) / 2; j++)
            System.out.print(" ");
        System.out.print("*");
        for (int j = 0; j < i - 2; j++)
            System.out.print(" ");
        if (i > 1)
            System.out.print("*");
        System.out.println("");
    }
}
