
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;
import java.util.Vector;

public class Main {

    public static final long time_limit = 1 * 1000000000L; // 1 sec
    public static final boolean is_windows = System.getProperty("os.name").startsWith("Windows");

    public Random rand;
    public Object o;

    public Main() {
        rand = new Random();
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("make")) {
                new Main().makeTest();
            } else if (args[0].equalsIgnoreCase("test")) {
                new Main().test();
            }
        } else {
            new Main().start();
        }
    }

    public void test() {
        Scanner in = new Scanner(System.in);
        String pre = in.next();
        String inord = in.next();
        in.close();
        Assignment5_1 test = new Assignment5_1(pre, inord);
        System.out.println("traversal(): " + traversal(test.get()));
        System.out.println("report(): " + test.report());
    }

    public void start() {
        int treeTime = 0, rpTime = 0, tc_num;
        try {
            if (!integrityCheck()) {
                return;
            }
        } catch (Exception err) {
            err.printStackTrace();
        }
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream("tcs.bin"));
            for (tc_num = 1;; ++tc_num) {
                String preord = readString(dis);
                String inord = readString(dis);
                String tree = readString(dis);
                String rp = readString(dis);
                System.out.printf("\rChecking test #%d...", tc_num);
                Assignment5_1 a51 = null;
                int tmp = timeCheck(() -> {
                    o = new Assignment5_1(preord, inord);
                });
                if (tmp < 0) {
                    throw new Exception("Time Limit Exceeded: tree construction");
                }
                if (o instanceof Assignment5_1) {
                    a51 = (Assignment5_1) o;
                }
                treeTime = treeTime < tmp ? tmp : treeTime;
                if (a51 == null || !traversal(a51.get()).equals(tree)) {
                    throw new Exception("Wrong Answer: tree construction");
                }
                tmp = timeCheck(() -> {
                    o = ((Assignment5_1) o).report();
                });
                if (tmp < 0) {
                    throw new Exception("Time Limit Exceeded: report()");
                }
                if (o instanceof String) {
                    tree = (String) o;
                }
                rpTime = rpTime < tmp ? tmp : rpTime;
                if (!rp.equals(tree)) {
                    throw new Exception("Wrong Answer: report()");
                }
            }
        } catch (FileNotFoundException err) {
            printColored("\033[1;31mNo test cases provided; could not test the code\033[0m\n");
        } catch (IOException err) {
            printColored("\033[1;32m AC!\033[0m (construction \033[1;35m" + treeTime + "ms\033[0m, report() \033[1;35m"
                    + rpTime + "ms\033[0m)\n");
        } catch (Exception err) {
            printColored("\033[1;31m " + err.getMessage() + "\033[0m\n");
            System.exit(0);
        }
    }

    public int timeCheck(Runnable r) {
        Thread th = new Thread(r);
        th.start();
        long stime = System.nanoTime();
        while (th.isAlive()) {
            if (System.nanoTime() - stime > time_limit) {
                break;
            }
        }
        if (th.isAlive()) {
            return -1;
        }
        return (int) ((System.nanoTime() - stime) / 1000000L);
    }

    public void makeTest() throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream("hand.bin"));
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("tcs.bin"));
        for (int i = 600; i <= 60000; i += 600) {
            System.out.printf("\rMaking... (%.2f%%)", i / 600.);
            String preord = makeString(i);
            String inord = getString(preord);
            Assignment5_1 n = new Assignment5_1(preord, inord);
            String tree = traversal(n.get());
            String rp = n.report();
            writeString(dos, preord);
            writeString(dos, inord);
            writeString(dos, tree);
            writeString(dos, rp);
        }
        System.out.println();
        try {
            while (true) {
                String preord = readString(dis);
                String inord = readString(dis);
                Assignment5_1 n = new Assignment5_1(preord, inord);
                String tree = traversal(n.get());
                String rp = n.report();
                writeString(dos, preord);
                writeString(dos, inord);
                writeString(dos, tree);
                writeString(dos, rp);
            }
        } catch (IOException err) {
        }
    }

    public void writeString(DataOutputStream dos, String s) throws IOException {
        int i, len = s.length();
        dos.writeInt(len);
        for (i = 0; i < len; ++i) {
            dos.writeChar(s.charAt(i));
        }
    }

    public String readString(DataInputStream dis) throws IOException {
        int i, len = dis.readInt();
        StringBuilder sb = new StringBuilder();
        for (i = 0; i < len; ++i) {
            sb.append(dis.readChar());
        }
        return sb.toString();
    }

    public String makeString(int n) {
        char[] v = new char[65000];
        int i, j = 0;
        for (i = 1; i < 0xD800; ++i) {
            if (i == 10 || i == 13 || i == '_')
                continue;
            v[j++] = (char) i;
        }
        for (i = 0xE000; i < 0x10000; ++i) {
            v[j++] = (char) i;
        }
        for (i = 0; i < j; ++i) {
            int idx = rand.nextInt(j);
            char t = v[i];
            v[i] = v[idx];
            v[idx] = t;
        }
        return new String(Arrays.copyOfRange(v, 0, n));
    }

    public String traversal(Node5_1 n) {
        Stack<Node5_1> stack = new Stack<Node5_1>();
        Stack<Byte> stack2 = new Stack<Byte>();
        StringBuilder sb = new StringBuilder();
        stack.add(n);
        stack2.add((byte) 0);
        while (!stack.empty()) {
            Node5_1 now = stack.peek();
            if (now == null) {
                sb.append('_');
                stack.pop();
                stack2.pop();
            } else {
                byte b = stack2.pop();
                if (b == 0) {
                    sb.append(now.label);
                    stack.push(now.left);
                    stack2.push((byte) 1);
                    stack2.push((byte) 0);
                } else if (b == 1) {
                    stack.push(now.right);
                    stack2.push((byte) 2);
                    stack2.push((byte) 0);
                } else {
                    stack.pop();
                }
            }
        }
        return sb.toString();
    }

    private String getString(String v) {
        StringBuilder sb = new StringBuilder();
        getString(v.length(), v, 0, sb);
        return sb.toString();
    }

    private void getString(int size, String v, int idx, StringBuilder sb) {
        if (size == 0) {
            return;
        }
        int l = rand.nextInt(size);
        getString(l, v, idx + 1, sb);
        sb.append(v.charAt(idx));
        getString(size - l - 1, v, idx + l + 1, sb);
    }

    public void printColored(String v) {
        if (is_windows) {
            v = v.replaceAll("\\033\\[[0-9;]*m", "");
        }
        System.out.print(v);
    }

    public HashSet<String> integritySet;

    public boolean integrityCheck() throws IOException {
        Vector<String> vs = new Vector<String>();
        this.integritySet = new HashSet<String>();
        integrityCheck("Assignment5_1.class", vs);
        if (vs.size() == 0) {
            printColored("\033[1;32mNo potentally hazardous classes found.\033[0m\n");
            return true;
        }
        String[] arr = new String[vs.size()];
        vs.toArray(arr);
        Arrays.sort(arr);
        for (String s : arr) {
            printColored("\033[1;35mHazardous and monstrous class \033[1;31m" + s + "\033[1;35m found.\033[0m\n");
        }
        printColored("\033[1;31mCannot continue.\033[0m\n");
        return false;
    }

    public void integrityCheck(String filename, Vector<String> vs) throws IOException {
        Analyzer az = null;
        integritySet.add(filename);
        try {
            az = new Analyzer(filename);
        } catch (FileNotFoundException err) {
            return;
        }
        for (Analyzer.Pool ap : az.pool) {
            if (ap instanceof Analyzer.ClassPool) {
                String className = ((Analyzer.UTFPool) az.pool[((Analyzer.ClassPool) ap).v]).v;
                int i;
                for (i = 0; className.charAt(i) == '['; i++)
                    ;
                className = className.substring(i);
                if (className.matches("L.*;")) {
                    className = className.substring(1, className.length() - 1);
                }
                if (className.contains("/") && !className.startsWith("java/lang/")) {
                    vs.add(className.replace('/', '.'));
                }
                if (!className.contains("/") && !integritySet.contains(className + ".class")) {
                    integrityCheck(className + ".class", vs);
                }
            }
        }
    }

}
