import java.util.ArrayList;
import java.util.Scanner;

public class ex2 {
    public static void main(String[] args) {
        ArrayList<Vector> nvectors = new ArrayList<>();

        Vector[] vlist = new Vector[5];
        for (int i = 0; i < 5; i++)
            vlist[i] = getVector();

        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 5; j++) {
                Vector n = Vector.cross(vlist[i], vlist[j]);
                System.out.println(n);

                boolean b = false;
                for(Vector v: nvectors)
                    if (v.equals(n))
                        b = true;

                if (!b)
                    nvectors.add(n);
            }
        }

        System.out.println(nvectors.size());
    }

    private static Vector getVector() {
        Scanner scan = new Scanner(System.in);
        String line = scan.nextLine();

        String[] factor = line.split(",");
        return new Vector(Integer.parseInt(factor[0]), Integer.parseInt(factor[1]), Integer.parseInt(factor[2]));
    }

    private static int comination(int n, int r) {
        return factorial(n) / (factorial(r) * factorial(n - r));
    }

    private static int factorial(int a) {
        int sum = 1;
        for (int i = 1; i <= a; i++)
            sum *= i;
        return sum;
    }
}
