public class Tester4_2 {
    public static void main(String[] args) {
        Assignment4_2 a = new Assignment4_2("11010011001000", "ABCDEFG");
        System.out.println(a.report_bits_preorder());
        System.out.println(a.report_bits_levelorder());
        System.out.println(a.report_preorder());
        System.out.println(a.report_levelorder());
    }
}
