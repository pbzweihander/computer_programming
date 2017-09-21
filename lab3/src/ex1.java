import java.util.Scanner;

public class ex1 {
    public static void main(String[] args) {
        int n = getInt();

        if (isPowerOfTwo(n))
            System.out.println("2의 배수");
        else
            System.out.println("2의 배수 아님");
    }

    private static void test() {
        int n = getInt();

        for (int i = 1; i <= n; i++) {
            assert isPowerOfTwo(i) == isPowerOfTwoTest(i);
            if (isPowerOfTwo(i))
                System.out.println(i);
        }
    }

    private static int getInt() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Input:");
        return scan.nextInt();
    }

    private static boolean isPowerOfTwo(int n) {
        return (n & (n - 1)) == 0;
    }

    private static boolean isPowerOfTwoTest(int n) {
        for (int i = 0; Math.pow(2, i) <= n; i++)
            if (Math.pow(2, i) == n)
                return true;
        return false;
    }
}
