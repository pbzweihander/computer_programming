import java.text.DecimalFormat;
import java.util.Scanner;

public class Assignment1_2 {
    public static void main(String[] args) {
        String hhmmss = getHHMMSS();

        float hour = getHour(hhmmss);
        float minute = getMinute(hhmmss);
        int second = getSecond(hhmmss);

        float hour_degree = convert12ToDegrees(hour);
        float minute_degree = convert60ToDegrees(minute);
        float second_degree = convert60ToDegrees(second);

        float hour_minute = angleBetween(hour_degree, minute_degree);
        float minute_second = angleBetween(minute_degree, second_degree);
        float second_hour = angleBetween(second_degree, hour_degree);

        System.out.println("<Result>");

        for (int i = 0; i < 3; i++) {
            if (hour_minute == max(hour_minute, minute_second, second_hour)) {
                System.out.println("Hour-minute: " + fmt(hour_minute) + " degrees");
                hour_minute = -1f;
            } else if (minute_second == max(hour_minute, minute_second, second_hour)) {
                System.out.println("Minute-second: " + fmt(minute_second) + " degrees");
                minute_second = -1f;
            } else {
                System.out.println("Second-hour: " + fmt(second_hour) + " degrees");
                second_hour = -1f;
            }
        }
    }

    private static String fmt(float f) {
        return new DecimalFormat("#.##").format(f);
    }

    private static String getHHMMSS() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Input time: ");
        String line = scan.nextLine();
        if (line.length() > 6) {
            line = line.substring(line.length() - 6);
        } else {
            while (line.length() < 6)
                line = "0" + line;
        }
        return line;
    }

    private static float getHour(String hhmmss) {
        int hh = Integer.parseInt(hhmmss.substring(0, 2)) % 12;
        float fmm = getMinute(hhmmss);
        return (float)hh + (fmm / 60f);
    }

    private static float getMinute(String hhmmss) {
        int mm = Integer.parseInt(hhmmss.substring(2, 4));
        int ss = getSecond(hhmmss);
        return (float)mm + ((float)ss / 60f);
    }

    private static int getSecond(String hhmmss) {
        return Integer.parseInt(hhmmss.substring(4, 6));
    }

    private static float convert12ToDegrees(float h) {
        return h / 12f * 360f;
    }

    private static float convert60ToDegrees(float ms) {
        return ms / 60f * 360f;
    }

    private static float angleBetween(float a, float b) {
        float diff = Math.abs(a - b);
        if (diff > 180f)
            diff = 360f - diff;
        return diff;
    }

    private static float max(float a, float b, float c) {
        if (a >= b && a >= c) return a;
        if (b >= a && b >= c) return b;
        if (c >= a && c >= b) return c;
        return 0f;
    }
}
