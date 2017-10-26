import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Midterm3 {
    public static void main(String[] args) {
        Scanner input;
        PrintStream output;
        try {
            input = new Scanner(new File(args[0]));
            output = new PrintStream(new File(args[1]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        String content = input.nextLine();
        String[] words = content.split("[,. ]+", 0);

        double average_length = 0;
        for (String w : words)
            average_length += (double) w.length() / (double) words.length;
        int above_average = 0;
        for (String w : words)
            if (w.length() > average_length)
                above_average++;

        output.printf("Number of words = %d\nAverage length of a word = %.2f\nNumber of words above the average length = %d\n", words.length, average_length, above_average);
    }
}
