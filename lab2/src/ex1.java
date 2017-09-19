public class ex1 {
    public static void main(String[] args) {
        draw_art(10);
    }

    private static void draw_art(int size) {
        System.out.print('#');
        for (int i = 0; i < size * 4; i++)
            System.out.print('=');
        System.out.println('#');

        for (int i = size; i > 0; i--) {
            System.out.print('|');
            for (int j = 0; j < i - 1; j++)
                System.out.print("  ");
            System.out.print("<>");
            for (int j = i; j < size; j ++)
                System.out.print("..");

            for (int j = i; j < size; j ++)
                System.out.print("..");
            System.out.print("<>");
            for (int j = 0; j < i - 1; j++)
                System.out.print("  ");
            System.out.println('|');
        }

        for (int i = 1; i <= size; i++) {
            System.out.print('|');
            for (int j = 0; j < i - 1; j++)
                System.out.print("  ");
            System.out.print("<>");
            for (int j = i; j < size; j ++)
                System.out.print("..");

            for (int j = i; j < size; j ++)
                System.out.print("..");
            System.out.print("<>");
            for (int j = 0; j < i - 1; j++)
                System.out.print("  ");
            System.out.println('|');
        }

        System.out.print('#');
        for (int i = 0; i < size * 4; i++)
            System.out.print('=');
        System.out.println('#');
    }
}
