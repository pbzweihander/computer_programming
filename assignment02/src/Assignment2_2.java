import java.util.Random;

public class Assignment2_2 {
    private boolean[] dealedCards = new boolean[52];

    public static void main(String[] args) {
        Assignment2_2 a = new Assignment2_2();

        int[][] player = new int[4][10];

        for (int i = 0; i < player.length; i++) {
            for (int j = 0; j < player[i].length; j++) {
                player[i][j] = a.draw();
            }
            a.printHand(i + 1, player[i]);
        }
        System.out.println();

        int prev_card = -1;
        int[] scores = new int[4];
        for (int i = 0; i < player[0].length; i++) {
            for (int j = 0; j < player.length; j++) {
                int c = player[j][i];
                System.out.print(a.intToCard(c) + " | ");
                if (prev_card != -1) {
                    if (prev_card / 13 == c / 13)
                        scores[j] += 4;
                    else if (prev_card % 13 == c % 13)
                        scores[j] += 13;
                }
                prev_card = c;
            }
            System.out.println();
        }

        for (int i = 0; i < player.length; i++) {
            System.out.printf("Player %d score : %d\n", i + 1, scores[i]);
        }
    }

    private Assignment2_2() {
        for(int i = 0; i < dealedCards.length; i++)
            dealedCards[i] = false;
    }

    private int draw() {
        Random random = new Random();
        int n = -1;
        do {
            n = random.nextInt(52);
        } while (dealedCards[n]);
        dealedCards[n] = true;
        return n;
    }

    private String intToCard(int c) {
        String card = "";
        switch (c / 13) {
            case 0:
                card = "S";
                break;
            case 1:
                card = "D";
                break;
            case 2:
                card = "C";
                break;
            case 3:
                card = "H";
                break;
        }
        switch (c % 13) {
            case 0:
                card += " A";
                break;
            case 10:
                card += " J";
                break;
            case 11:
                card += " Q";
                break;
            case 12:
                card += " K";
                break;
            default:
                int n = c % 13;
                if (n < 9)
                    card += " " + (n + 1);
                else
                    card += n + 1;
        }
        return card;
    }

    private void printHand(int p, int[] cards) {
        System.out.print("Player " + p + " : ");
        for (int c: cards)
            System.out.print(intToCard(c) + " | ");
        System.out.println();
    }
}
