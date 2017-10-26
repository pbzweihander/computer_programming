public class Midterm2 {
    public static void main(String[] args) {
        char[] arr1 = args[0].toCharArray();
        char[] arr2 = args[1].toCharArray();

        String max = "";
        for (int start = 0; start < arr2.length; start++) {
            String word = "";
            for (int i = 0; i < min2(arr1.length, arr2.length - start); i++) {
                if (arr1[i] == arr2[start + i])
                    word += arr1[i];
                else {
                    if (!word.isEmpty()) {
                        if (max.length() < word.length())
                            max = word;
                        word = "";
                    }
                }
            }
            if (!word.isEmpty()) {
                if (max.length() < word.length())
                    max = word;
                word = "";
            }
        }
        for (int start = 0; start < arr1.length; start++) {
            String word = "";
            for (int i = 0; i < min2(arr2.length, arr1.length - start); i++) {
                if (arr2[i] == arr1[start + i])
                    word += arr2[i];
                else {
                    if (!word.isEmpty()) {
                        if (max.length() < word.length())
                            max = word;
                        word = "";
                    }
                }
            }
            if (!word.isEmpty()) {
                if (max.length() < word.length())
                    max = word;
                word = "";
            }
        }

        System.out.println(max);
    }

    private static int min2(int a, int b) {
        return a > b ? b : a;
    }
}
