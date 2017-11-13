import java.io.*;

public class Assignment3_Cube {
    private Assignment3_Side[] side;
    private int size;

    public Assignment3_Cube(String filename) {
        side = new Assignment3_Side[6];
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            size = Integer.parseInt(br.readLine());
            for (int i = 0; i < 6; i++)
                side[i] = new Assignment3_Side(size, i + 1);

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int[][] report(int s) {
        return side[s - 1].report();
    }
}
