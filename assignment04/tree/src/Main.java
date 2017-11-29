
/* Created and written by kipa00.
 * You can freely modify or distribute this code:
 * - without redistributing the modified code
 * - without removing this comment
 * A code judged AC by this code do not guarantee
 * that your code is absolutely correct.
 * USE AT YOUR OWN RISK.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

public class Main {

    public static Random rand = new Random();
    public static final boolean is_windows = System.getProperty("os.name").startsWith("Windows");

    public Object o;

    public static void main(String[] args) {
        if (args.length == 0) {
            printlnColored("Warning!", "\033[4m\033[1;31m%s\033[0m");
            System.out.print("This usage of program is ");
            printColored("highly unrecommended", "\033[1;31m%s\033[0m");
            System.out.print(".\nIf you are to continue programming, you should get used to ");
            printColored("CLI programming", "\033[1;32m%s\033[0m");
            System.out.println(".\nGoogle nano editor for more information.");
            try {
                int exec_time;
                printlnColored("Assignment 4-1", "\033[4m\033[1;35m%s\033[0m");
                exec_time = check_first();
                printColored("AC!", "\033[1;32m%s\033[0m");
                System.out.println(" (" + exec_time + "ms)");
                printlnColored("Assignment 4-2", "\033[4m\033[1;35m%s\033[0m");
                exec_time = check_second();
                printColored("AC!", "\033[1;32m%s\033[0m");
                System.out.println(" (" + exec_time + "ms)");
            } catch (Exception err) {
                printlnColored(err.getMessage(), " \033[1;31m%s\033[0m");
            }
        } else if (args[0].equals("check")) {
            int exec_time;
            if (args.length == 1) {
                try {
                    printlnColored("Assignment 4-1", "\033[4m\033[1;35m%s\033[0m");
                    exec_time = check_first();
                    printColored("AC!", "\033[1;32m%s\033[0m");
                    System.out.println(" (" + exec_time + "ms)");
                    printlnColored("Assignment 4-2", "\033[4m\033[1;35m%s\033[0m");
                    exec_time = check_second();
                    printColored("AC!", "\033[1;32m%s\033[0m");
                    System.out.println(" (" + exec_time + "ms)");
                } catch (Exception err) {
                    printlnColored(err.getMessage(), " \033[1;31m%s\033[0m");
                }
            } else if (args[1].equals("1")) {
                try {
                    exec_time = check_first();
                    printColored("AC!", "\033[1;32m%s\033[0m");
                    System.out.println(" (" + exec_time + "ms)");
                } catch (Exception err) {
                    printlnColored(err.getMessage(), " \033[1;31m%s\033[0m");
                }
            } else if (args[1].equals("2")) {
                try {
                    exec_time = check_second();
                    printColored("AC!", "\033[1;32m%s\033[0m");
                    System.out.println(" (" + exec_time + "ms)");
                } catch (Exception err) {
                    printlnColored(err.getMessage(), " \033[1;31m%s\033[0m");
                }
            }
        } else if (args[0].equals("make")) {
            int cnt = 0;
            try {
                cnt = Integer.parseInt(args[1]);
            } catch (Exception err) {
                cnt = 300;
            }
            PrintStream out = null;
            if (cnt >= 0) {
                try {
                    out = new PrintStream(new File("tests/1.txt"));
                    out.println(cnt);
                    for (int tc_num = 1; tc_num <= cnt; ++tc_num) {
                        System.out.print("\rMaking first test cases... (" + tc_num + "/" + cnt + ")");
                        int sz = (int) (rand.nextInt(100001 / cnt) + (100001L * tc_num) / cnt);
                        if (tc_num <= 3) {
                            sz = tc_num * 5;
                        }
                        String bit = makeBitString_1(sz), arb = makeString(sz);
                        Node4_1 now = new Assignment4_1(bit, arb).getRoot();
                        out.println(String.join("-", bit, arb, traversal(now)));
                    }
                    System.out.println(" done.");
                } catch (Exception err) {
                    System.err.println("A critical error occurred while making test cases");
                    err.printStackTrace();
                } finally {
                    if (out != null) {
                        out.close();
                    }
                }
            }
            try {
                cnt = Integer.parseInt(args[2]);
            } catch (Exception err) {
                cnt = 150;
            }
            out = null;
            if (cnt >= 0) {
                try {
                    out = new PrintStream(new File("tests/2.txt"));
                    out.println(cnt);
                    for (int tc_num = 1; tc_num <= cnt; ++tc_num) {
                        System.out.print("\rMaking second test cases... (" + tc_num + "/" + cnt + ")");
                        int sz = (int) (rand.nextInt(100001 / cnt) + (100001L * tc_num) / cnt);
                        if (tc_num <= 3) {
                            sz = tc_num * 5;
                        }
                        String bit = makeBitString_2(sz), arb = makeString(sz);
                        Assignment4_2 now = new Assignment4_2(bit, arb);
                        out.println(String.join("-", traversal(now.root), now.report_bits_preorder(),
                                now.report_preorder(), now.report_bits_levelorder(), now.report_levelorder()));
                    }
                    System.out.println(" done.");
                } catch (Exception err) {
                    System.err.println("A critical error occurred while making test cases");
                    err.printStackTrace();
                } finally {
                    if (out != null) {
                        out.close();
                    }
                }
            }
        }
    }

    public static int check_first() throws Exception {
        BufferedReader br = null;
        int res = -1;
        try {
            br = new BufferedReader(new FileReader("tests/1.txt"));
            int n = Integer.parseInt(br.readLine());
            int i;
            for (i = 0; i < n; ++i) {
                System.out.printf("\rChecking... (%.2f%%)", 100. * i / n);
                String[] exp = br.readLine().split("-");
                Main m = new Main();
                Thread th = new Thread(new Runnable() {
                    private Main mm = m;

                    @Override
                    public void run() {
                        mm.o = new Assignment4_1(exp[0], exp[1]).getRoot();
                    }
                });
                th.start();
                {
                    long stime = System.nanoTime();
                    while (th.isAlive()) {
                        if (System.nanoTime() - stime > 1000000000L) {
                            break;
                        }
                    }
                    int temp = (int) ((System.nanoTime() - stime) / 1000000L);
                    res = res < temp ? temp : res;
                    if (th.isAlive()) {
                        th.interrupt();
                    }
                }
                if (m.o == null) {
                    br.close();
                    throw new Exception("Time Limit Exceeded on test " + (i + 1) + ", ^C to exit");
                } else if (!traversal((Node4_1) m.o).equals(exp[2])) {
                    System.out.println();
                    br.close();
                    throw new Exception("Wrong Answer on test " + (i + 1));
                }
            }
            System.out.print("\rChecking... (100.00%) ");
        } catch (FileNotFoundException err) {
            throw new Exception("No test cases provided; could not test the code");
        }
        if (br != null) {
            br.close();
        }
        return res;
    }

    public static int check_second() throws Exception {
        BufferedReader br = null;
        int res = -1;
        try {
            br = new BufferedReader(new FileReader("tests/2.txt"));
            int n = Integer.parseInt(br.readLine());
            int i;
            for (i = 0; i < n; ++i) {
                System.out.printf("\rChecking... (%.2f%%)", 100. * i / n);
                String[] exp = br.readLine().split("-", 5);
                Main m = new Main();
                Thread th = new Thread(new Runnable() {
                    private Main mm = m;

                    @Override
                    public void run() {
                        Assignment4_2 now = new Assignment4_2(exp[1], exp[4]);
                        mm.o = new boolean[] { traversal(now.root).equals(exp[0]),
                                now.report_bits_preorder().equals(exp[1]), now.report_preorder().equals(exp[2]),
                                now.report_bits_levelorder().equals(exp[3]), now.report_levelorder().equals(exp[4]) };
                    }
                });
                th.start();
                {
                    long stime = System.nanoTime();
                    while (th.isAlive()) {
                        if (System.nanoTime() - stime > 1000000000L) {
                            break;
                        }
                    }
                    int temp = (int) ((System.nanoTime() - stime) / 1000000L);
                    res = res < temp ? temp : res;
                    if (th.isAlive()) {
                        th.interrupt();
                    }
                }
                String[] errMsg = new String[] { "tree differs", "from report_bits_preorder()",
                        "from report_preorder()", "from report_bits_levelorder()", "from report_levelorder()" };
                int k;
                boolean[] checks = (boolean[]) m.o;
                if (m.o == null) {
                    br.close();
                    throw new Exception("Time Limit Exceeded on test " + (i + 1) + ", ^C to exit");
                } else
                    for (k = 0; k < 5; ++k) {
                        if (!checks[k]) {
                            System.out.println();
                            br.close();
                            throw new Exception("Wrong answer on test " + (i + 1) + ": " + errMsg[k]);
                        }
                    }
            }
            System.out.print("\rChecking... (100.00%) ");
        } catch (FileNotFoundException err) {
            throw new Exception("No test cases provided; could not test the code");
        }
        if (br != null) {
            br.close();
        }
        return res;
    }

    public static String traversal(Node4_1 n) {
        Stack<Node4_1> stack = new Stack<Node4_1>();
        Stack<Byte> stack2 = new Stack<Byte>();
        StringBuilder sb = new StringBuilder();
        stack.add(n);
        stack2.add((byte) 0);
        while (!stack.empty()) {
            Node4_1 now = stack.peek();
            if (now == null) {
                stack.pop();
            } else if (now.label == '0') {
                byte nowb = stack2.pop();
                if (nowb == 0) {
                    sb.append(now.character);
                    stack.add(now.left);
                    stack2.add((byte) 1);
                    stack2.add((byte) 0);
                } else if (nowb == 1) {
                    stack.add(now.right);
                    stack2.add((byte) 2);
                    stack2.add((byte) 0);
                } else {
                    stack.pop();
                }
            } else {
                sb.append('_');
                stack.pop();
                stack2.pop();
            }
        }
        return sb.toString();
    }

    public static String makeString(int size) {
        StringBuilder sb = new StringBuilder();
        while (size-- > 0) {
            sb.append("023456789QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm".charAt(rand.nextInt(61)));
        }
        return sb.toString();
    }

    public static String makeBitString_1(int size) {
        LinkedList<Integer> sizes = new LinkedList<Integer>();
        StringBuilder sb = new StringBuilder();
        sizes.add(size);
        while (sizes.peek() != null) {
            int now = sizes.poll();
            if (now == 0) {
                sb.append("1");
            } else {
                int leftsz = rand.nextInt(now);
                sb.append("0");
                sizes.add(leftsz);
                sizes.add(now - 1 - leftsz);
            }
        }
        return sb.toString();
    }

    public static String makeBitString_2(int size) {
        Stack<Integer> sizes = new Stack<Integer>(), flags = new Stack<Integer>();
        StringBuilder sb = new StringBuilder();
        sizes.add(size);
        flags.add(-1);
        while (!sizes.empty()) {
            int now = sizes.peek();
            int b = flags.pop();
            if (now == 0 || b == -2) {
                sizes.pop();
            } else if (b == -1) {
                int leftsz = rand.nextInt(now);
                sb.append(leftsz == 0 ? "0" : "1");
                sb.append(leftsz == now - 1 ? "0" : "1");
                sizes.add(leftsz);
                flags.add(now - leftsz - 1);
                flags.add(-1);
            } else {
                sizes.add(b);
                flags.add(-2);
                flags.add(-1);
            }
        }
        return sb.toString();
    }

    public static void printlnColored(String v, String format) {
        if (is_windows) {
            System.out.println(v);
        } else {
            System.out.printf(format + "\n", v);
        }
    }

    public static void printColored(String v, String format) {
        if (is_windows) {
            System.out.print(v);
        } else {
            System.out.printf(format, v);
        }
    }

}
