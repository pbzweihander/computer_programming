public class Assignment3_Side {
    private int[][] side;

    public Assignment3_Side(int size, int value) {
        side = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                side[i][j] = value;
    }

    public int[] getRow(int location) {
        return side[location];
    }

    public int[] getCol(int location) {
        int size = side.length;
        int[] col = new int[size];
        for (int i = 0; i < size; i++)
            col[i] = side[i][location];
        return col;
    }

    public int[][] report() {
        return side;
    }

    public void setRow(int location, int[] row) {
        side[location] = row;
    }

    public void setCol(int location, int[] col) {
        for (int i = 0; i < side.length; i++)
            side[i][location] = col[i];
    }
}
