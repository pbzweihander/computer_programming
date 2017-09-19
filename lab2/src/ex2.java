public class ex2 {
    public static void main(String[] args) {
        final int N = 500;

        int[] perfects = GetPerfects(N);
        int[] fiboprimes = GetFiboprimes(N);
        int[] mersennes = GetMersennes(N);

        System.out.println("[" + perfects.length + ", " + fiboprimes.length + ", " + mersennes.length + "]");

        System.out.print('[');
        for (int i:perfects) {
            System.out.print(i);
            System.out.print(", ");
        }
        System.out.println(']');

        System.out.print('[');
        for (int i:fiboprimes) {
            System.out.print(i);
            System.out.print(", ");
        }
        System.out.println(']');

        System.out.print('[');
        for (int i:mersennes) {
            System.out.print(i);
            System.out.print(", ");
        }
        System.out.println(']');
    }

    private static boolean IsPerfect(int n)
    {
        int sum = 0;
        for (int i = 1; i <= n / 2; i++)
            if (n % i == 0)
                sum += i;

        return sum == n;
    }

    private static boolean IsPrime(int n)
    {
        if (n == 2)
            return true;
        if (n % 2 == 0)
            return false;
        for (int i = 3; i <= Math.sqrt(n); i++)
            if (n % i ==0)
                return false;
        return true;
    }

    private static int[] GetPerfects(int max)
    {
        int[] perfects = new int[max / 2];
        int count = 0;

        for (int i = 1; i <= max; i++)
            if (IsPerfect(i))
                perfects[count++] = i;

        int[] out = new int[count];
        System.arraycopy(perfects, 0, out, 0, count);

        return out;
    }

    private static int[] GetFiboprimes(int max)
    {
        int i = 2;
        int j = 1;
        int[] primes = new int[max / 2];
        int count = 0;

        while (i <= max) {
            if (IsPrime(i))
                primes[count++] = i;

            int tmp = i;
            i += j;
            j = tmp;
        }

        int[] out = new int[count];
        System.arraycopy(primes, 0, out, 0, count);

        return out;
    }

    private static int[] GetMersennes(int max)
    {
        int i = 2;
        int[] primes = new int[max / 2];
        int count = 0;

        while ((int)Math.pow(2, i) - 1 <= max)
        {
            if (IsPrime((int)Math.pow(2, i) - 1))
                primes[count++] = (int)Math.pow(2, i) - 1;

            i++;
        }

        int[] out = new int[count];
        System.arraycopy(primes, 0, out, 0, count);

        return out;
    }
}
