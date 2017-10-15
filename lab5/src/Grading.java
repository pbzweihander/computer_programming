import java.util.Scanner;

public class Grading {
    public static void main(String[] args) {
        Grading grading = new Grading();
        System.out.println(grading.getGrade(grading.getScore()));
    }

    private int getScore() {
        Scanner scanner = new Scanner(System.in);
        int assignment = scanner.nextInt();
        int challenge_bonus = scanner.nextInt();
        int number_of_absence = scanner.nextInt();
        int midterm_score = scanner.nextInt();
        int final_score = scanner.nextInt();

        int sum = 0;
        sum += clamp(assignment + challenge_bonus, 100, 20);
        sum += clamp(20 - (int) Math.pow(2, number_of_absence - 1), 20, 10);
        sum += clamp(midterm_score, 120, 35);
        sum += clamp(final_score, 100, 35);

        return sum;
    }

    private int clamp(int value, int max, int percentage) {
        if (value > max)
            return percentage;
        if (value < 0)
            return 0;
        return (int)(((double)value / (double)max) * (double)percentage);
    }

    private String getGrade(int score) {
        return new String[] {
                "A+", "A0", "A-",
                "B+", "B0", "B-",
                "C+", "C+", "C0", "C0", "C-", "C-",
                "D", "D", "D",
                "F", "F", "F", "F", "F", "F",
        }[(100 - score) / 5];
    }
}
