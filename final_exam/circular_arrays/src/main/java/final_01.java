import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class final_01 {
    public static void main(String[] args) {
        final_01 main = new final_01();
        main.inner_main(args);
    }

    private void inner_main(String[] args) {
        Scanner scanner;
        try {
            scanner = getInputFileScanner(args[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        int n_of_arrays = scanner.nextInt();
        int[] size_of_arrays = new int[n_of_arrays];
        CircularArray[] arrays = new CircularArray[n_of_arrays];
        for (int i = 0; i < n_of_arrays; i++)
            size_of_arrays[i] = scanner.nextInt();
        for (int i = 0; i < n_of_arrays; i++) {
            int[] arr = new int[size_of_arrays[i]];
            for (int j = 0; j < size_of_arrays[i]; j++)
                arr[j] = scanner.nextInt();
            arrays[i] = new CircularArray(arr);
        }

        int n_of_array_pairs = 0;
        for (int i = 0; i < n_of_arrays - 1; i++)
            for (int j = i + 1; j < n_of_arrays; j++)
                if (arrays[i].equals(arrays[j]))
                    n_of_array_pairs++;

        PrintStream outstream;
        try {
            outstream = getOutputFileStream(args[1]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        outstream.println(n_of_array_pairs);
    }

    private Scanner getInputFileScanner(String filename) throws FileNotFoundException {
        return new Scanner(new File(filename));
    }

    private PrintStream getOutputFileStream(String filename) throws FileNotFoundException {
        return new PrintStream(new File(filename));
    }
}
