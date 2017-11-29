
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Analyzer {

    public Pool[] pool;

    public Analyzer(String filename) throws FileNotFoundException, IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream(filename));
        dis.readInt();
        dis.readInt();
        int l = (int) dis.readShort();
        pool = new Pool[l];
        int i;
        for (i = 1; i < l; ++i) {
            byte u;
            Pool p = new Pool[] { null, new UTFPool(), null, new IntPool(), new FloatPool(), new LongPool(),
                    new DoublePool(), new ClassPool(), new StringPool(), new FieldPool(), new MethodPool(),
                    new InterfaceMethodPool(), new NameAndTypePool(), null, null, new MethodHandlePool(),
                    new MethodTypePool(), null, new InvokeDynamicPool() }[u = dis.readByte()];
            p.read(dis);
            pool[i] = p;
            if (u == 5 || u == 6) {
                ++i;
            }
        }
    }

    public interface Pool {
        public void read(DataInputStream fis) throws IOException;

        public String toString(Analyzer a);
    }

    public class ClassPool implements Pool {
        short v;

        public void read(DataInputStream fis) throws IOException {
            v = fis.readShort();
        }

        public String toString(Analyzer a) {
            return "Class(" + a.pool[v].toString(a) + ")";
        }
    }

    public class FieldPool implements Pool {
        short v, w;

        public void read(DataInputStream fis) throws IOException {
            v = fis.readShort();
            w = fis.readShort();
        }

        public String toString(Analyzer a) {
            return "Field(" + a.pool[v].toString(a) + "," + a.pool[w].toString(a) + ")";
        }
    }

    public class MethodPool implements Pool {
        short v, w;

        public void read(DataInputStream fis) throws IOException {
            v = fis.readShort();
            w = fis.readShort();
        }

        public String toString(Analyzer a) {
            return "Method(" + a.pool[v].toString(a) + "," + a.pool[w].toString(a) + ")";
        }
    }

    public class InterfaceMethodPool implements Pool {
        short v, w;

        public void read(DataInputStream fis) throws IOException {
            v = fis.readShort();
            w = fis.readShort();
        }

        public String toString(Analyzer a) {
            return "InterfaceMethod(" + a.pool[v].toString(a) + "," + a.pool[w].toString(a) + ")";
        }
    }

    public class StringPool implements Pool {
        short v;

        public void read(DataInputStream fis) throws IOException {
            v = fis.readShort();
        }

        public String toString(Analyzer a) {
            return "String(" + a.pool[v].toString(a) + ")";
        }
    }

    public class IntPool implements Pool {
        int v;

        public void read(DataInputStream fis) throws IOException {
            v = fis.readInt();
        }

        public String toString(Analyzer a) {
            return "Int(" + v + ")";
        }
    }

    public class FloatPool implements Pool {
        float v;

        public void read(DataInputStream fis) throws IOException {
            v = fis.readFloat();
        }

        public String toString(Analyzer a) {
            return "Float(" + v + ")";
        }
    }

    public class LongPool implements Pool {
        long v;

        public void read(DataInputStream fis) throws IOException {
            v = fis.readLong();
        }

        public String toString(Analyzer a) {
            return "Long(" + v + ")";
        }
    }

    public class DoublePool implements Pool {
        double v;

        public void read(DataInputStream fis) throws IOException {
            v = fis.readDouble();
        }

        public String toString(Analyzer a) {
            return "Double(" + v + ")";
        }
    }

    public class NameAndTypePool implements Pool {
        short v, w;

        public void read(DataInputStream fis) throws IOException {
            v = fis.readShort();
            w = fis.readShort();
        }

        public String toString(Analyzer a) {
            return "NameAndType(" + a.pool[v].toString(a) + ":" + a.pool[w].toString(a) + ")";
        }
    }

    public class UTFPool implements Pool {
        String v;

        public void read(DataInputStream fis) throws IOException {
            v = fis.readUTF();
        }

        public String toString(Analyzer a) {
            return v;
        }
    }

    public class MethodHandlePool implements Pool {
        byte kind;
        short index;

        public void read(DataInputStream fis) throws IOException {
            kind = fis.readByte();
            index = fis.readShort();
        }

        public String toString(Analyzer a) {
            return "MethodHandle";
        }
    }

    public class MethodTypePool implements Pool {
        short v;

        public void read(DataInputStream fis) throws IOException {
            v = fis.readShort();
        }

        public String toString(Analyzer a) {
            return "MethodType(" + a.pool[v].toString(a) + ")";
        }
    }

    public class InvokeDynamicPool implements Pool {
        public void read(DataInputStream fis) throws IOException {
            fis.readInt();
        }

        public String toString(Analyzer a) {
            return "InvokeDynamic";
        }
    }

}
