public class CircularArray {
    private int[] arr;

    public CircularArray(int[] arr) {
        this.arr = arr.clone();
    }

    public int length() {
        return arr.length;
    }

    public boolean equals(CircularArray other) {
        if (arr.length != other.length())
            return false;

        int start = 0;
        int length = arr.length;
        int i = 0;
        for (int j = start; j < length + start; j++) {
            if (arr[i++] != other.arr[j % length]) {
                start++;
                i = 0;
                j = start - 1;
                if (start >= length)
                    return false;
            }
        }
        return true;
    }
}
