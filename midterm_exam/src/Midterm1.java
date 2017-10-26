import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Midterm1 {
    public static void main(String[] args) {
        Midterm1 instance = new Midterm1();
        instance.inner_main(args);
    }

    private void inner_main(String[] args) {
        Scanner input;
        PrintStream output;
        try {
            input = new Scanner(new File(args[0]));
            output = new PrintStream(new File(args[1]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        int number_of_points = input.nextInt();
        input.nextLine();
        Point[] points = new Point[number_of_points];
        for (int i = 0; i < number_of_points; i++)
            points[i] = nextPoint(input);

        int number_of_pairs = c(number_of_points, 2);

        double max = 0;
        PointPair[] max_pairs = new PointPair[number_of_pairs];
        int max_count = 0;

        double min = -1;
        PointPair[] min_pairs = new PointPair[number_of_pairs];
        int min_count = 0;

        for (int i = 0; i < number_of_points; i++) {
            for (int j = i + 1; j < number_of_points; j++) {
                PointPair pair = new PointPair(points[i], points[j]);
                double d = pair.distance();
                if (d == max) {
                    max_pairs[max_count++] = pair;
                } else if (d > max) {
                    max = d;
                    max_pairs[0] = pair;
                    max_count = 1;
                }
                if (d == min) {
                    min_pairs[min_count++] = pair;
                } else if (min == -1 || d < min) {
                    min = d;
                    min_pairs[0] = pair;
                    min_count = 1;
                }
            }
        }

        output.print("The farthest pair");
        if (max_count == 1) {
            output.println(" is:");
            output.println(max_pairs[0].toString());
        } else {
            output.println("s are:");
            for (int i = 0; i < max_count; i++)
                output.println(max_pairs[i].toString());
        }

        output.print("The closest pair");
        if (min_count == 1) {
            output.println(" is:");
            output.println(min_pairs[0].toString());
        } else {
            output.println("s are:");
            for (int i = 0; i < min_count; i++)
                output.println(min_pairs[i].toString());
        }
    }

    private Point nextPoint(Scanner scanner) {
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        return new Point(x, y);
    }

    private int c(int n, int r) {
        return factorial(n) / (factorial(n - r) * factorial(r));
    }

    private int factorial(int n) {
        return n == 0 ? 1 : n * factorial(n - 1);
    }

    private class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public double distanceTo(Point b) {
            int dx = x - b.getX();
            int dy = y - b.getY();
            return Math.sqrt(dx * dx + dy * dy);
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", x, y);
        }
    }

    private class PointPair {
        private Point a;
        private Point b;

        public PointPair(Point a, Point b) {
            this.a = a;
            this.b = b;
        }

        public double distance() {
            return a.distanceTo(b);
        }

        @Override
        public String toString() {
            return a.toString() + ", " + b.toString();
        }
    }
}
