import java.util.Random;

public class DealCard {
    private boolean[] dealedCards = new boolean[52];

    public static void main(String[] args) {
        DealCard dealCard = new DealCard();

        for (int i = 0; i < 4; i++) {
            int[] hand = new int[13];
            for (int j = 0; j < 13; j++)
                hand[j] = dealCard.draw();
            dealCard.printLine(i, hand);
        }
    }

    private DealCard() {
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

    private void printLine(int p, int[] cards) {
        System.out.print("Player" + p + ": ");
        for (int c: cards)
            System.out.print(intToCard(c) + " | ");
        System.out.println();
    }
}
