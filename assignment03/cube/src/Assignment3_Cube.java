import java.io.*;
import java.util.Arrays;

public class Assignment3_Cube {
    private Assignment3_Side[] sides;
    private int size;

    public Assignment3_Cube(String filename) {
        sides = new Assignment3_Side[6];
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));

            size = Integer.parseInt(br.readLine());
            for (int i = 0; i < 6; i++)
                sides[i] = new Assignment3_Side(size, i + 1);

            int number_of_operation = Integer.parseInt(br.readLine());

            for (int i = 0; i < number_of_operation; i++) {
                String line = br.readLine().trim();
                String[] token = line.split(" ");
                int side = Integer.parseInt(token[0]) - 1;
                boolean row = token[1].equals("r");
                int direction;
                if (row)
                    direction = token[2].equals("r") ? 1 : -1;
                else
                    direction = token[2].equals("d") ? 1 : -1;
                int location = Integer.parseInt(token[3]) - 1;

                if (row)
                    rotateRow(side, direction, location);
                else
                    rotateCol(side, direction, location);
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int[][] report(int s) {
        return sides[s - 1].report();
    }

    public static void pushArrForward(int[] arr) {
        int size = arr.length;
        int tmp = arr[size - 1];
        for (int i = size - 1; i >= 1; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = tmp;
    }

    public static void pullArrBackward(int[] arr) {
        int size = arr.length;
        int tmp = arr[0];
        for (int i = 1; i < size; i++) {
            arr[i - 1] = arr[i];
        }
        arr[size - 1] = tmp;
    }

    public static void shiftArr(int[] arr, int direction) {
        if (direction >= 0) {
            pushArrForward(arr);
        } else {
            pullArrBackward(arr);
        }
    }

    public static void shiftArr(int[] arr, int direction, int count) {
        for (int i = 0; i < count; i++)
            shiftArr(arr, direction);
    }

    public static int[] concatArrs(int[]... arrs) {
        int size = 0;
        for (int[] arr : arrs)
            size += arr.length;
        int i = 0;
        int[] new_arr = new int[size];
        for (int[] arr : arrs)
            for (int j : arr)
                new_arr[i++] = j;
        return new_arr;
    }

    public static int[] reversed(int[] arr) {
        int size = arr.length;
        int[] new_arr = new int[size];
        for (int i = 0; i < size; i++)
            new_arr[i] = arr[size - i - 1];
        return new_arr;
    }

    public static void divideArrIntoSides(int[] arr, int[]... arrs) {
        int size = arrs[0].length;
        int i = 0;
        for (int[] a : arrs) {
            for (int j = 0; j < size; j++) {
                a[j] = arr[i++];
            }
        }
    }

    public void rotateRow(int side, int direction, int location) {
        if (side == 2)
            rotateRow(0, -direction, size - location - 1);
        else if (side == 3)
            rotateRow(1, -direction, size - location - 1);
        else if (side != 1) {
            // +d: 0+Rl -> 5+Rl -> 2-Rn-l-1 -> 4+Rl -> 0+Rl
            int[][] arrs = { sides[0].getRow(location), sides[5].getRow(location),
                    reversed(sides[2].getRow(size - location - 1)), sides[4].getRow(location) };
            int[] concated = concatArrs(arrs);
            shiftArr(concated, direction, size);
            divideArrIntoSides(concated, arrs);
            sides[0].setRow(location, arrs[0]);
            sides[5].setRow(location, arrs[1]);
            sides[2].setRow(size - location - 1, reversed(arrs[2]));
            sides[4].setRow(location, arrs[3]);
        } else {
            // +d: 1+Rl -> 5+Cn-l-1 -> 3-Rn-l-1 -> 4-Cl -> 0+Rl
            int[][] arrs = { sides[1].getRow(location), sides[5].getCol(size - location - 1),
                    reversed(sides[3].getRow(size - location - 1)), reversed(sides[4].getCol(location)) };
            int[] concated = concatArrs(arrs);
            shiftArr(concated, direction, size);
            divideArrIntoSides(concated, arrs);
            sides[1].setRow(location, arrs[0]);
            sides[5].setCol(size - location - 1, arrs[1]);
            sides[3].setRow(size - location - 1, reversed(arrs[2]));
            sides[4].setCol(location, reversed(arrs[3]));
        }
    }

    public void rotateCol(int side, int direction, int location) {
        if (side == 4)
            rotateRow(1, direction, location);
        else if (side == 5)
            rotateRow(1, -direction, size - location - 1);
        else {
            // +d: _+Cl -> _+Cl
            int[][] arrs = { sides[0].getCol(location), sides[3].getCol(location), sides[2].getCol(location),
                    sides[1].getCol(location) };
            int[] concated = concatArrs(arrs);
            shiftArr(concated, direction, size);
            divideArrIntoSides(concated, arrs);
            sides[0].setCol(location, arrs[0]);
            sides[3].setCol(location, arrs[1]);
            sides[2].setCol(location, arrs[2]);
            sides[1].setCol(location, arrs[3]);
        }
    }
}
