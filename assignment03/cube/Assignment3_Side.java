public class Assignment3_Side {
    private int[][] side;

    public Assignment3_Side(int size, int value) {
        side = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                side[i][j] = value;
    }

    public int[][] report() {
        return side;
    }
}
