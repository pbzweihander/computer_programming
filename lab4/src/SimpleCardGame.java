import java.util.ArrayList;
import java.util.Random;

public class SimpleCardGame {
    class Card {
        public int shape = -1; // 1: D, 2: H, 3: C, 4: S
        public int number = -1; // 1: A, 2~10: 2~10, 11: J, 12: Q, 13: K

        public Card(int shape, int number) {
            this.shape = shape;
            this.number = number;
        }

        @Override
        public String toString() {
            String sshape;
            String snumber;
            switch (shape) {
                case 1:
                    sshape = "D";
                    break;
                case 2:
                    sshape = "H";
                    break;
                case 3:
                    sshape = "C";
                    break;
                case 4:
                    sshape = "S";
                    break;
                default:
                    sshape = "";
            }
            switch (number) {
                case 1:
                    snumber = "A";
                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                    snumber = "" + number;
                    break;
                case 11:
                    snumber = "J";
                    break;
                case 12:
                    snumber = "Q";
                    break;
                case 13:
                    snumber = "K";
                    break;
                default:
                    snumber = "";
                    break;
            }
            return snumber + sshape;
        }

        public String toExtendedString() {
            String sshape;
            String snumber;
            switch (shape) {
                case 1:
                    sshape = "Diamond";
                    break;
                case 2:
                    sshape = "Heart";
                    break;
                case 3:
                    sshape = "Club";
                    break;
                case 4:
                    sshape = "Spade";
                    break;
                default:
                    sshape = "";
            }
            switch (number) {
                case 1:
                    snumber = "Ace";
                    break;
                case 2:
                    snumber = "Two";
                    break;
                case 3:
                    snumber = "Three";
                    break;
                case 4:
                    snumber = "Four";
                    break;
                case 5:
                    snumber = "Five";
                    break;
                case 6:
                    snumber = "Six";
                    break;
                case 7:
                    snumber = "Seven";
                    break;
                case 8:
                    snumber = "Eight";
                    break;
                case 9:
                    snumber = "Nine";
                    break;
                case 10:
                    snumber = "Ten";
                    break;
                case 11:
                    snumber = "Jacks";
                    break;
                case 12:
                    snumber = "Queen";
                    break;
                case 13:
                    snumber = "King";
                    break;
                default:
                    snumber = "";
                    break;
            }
            return snumber + " of " + sshape;
        }

        public boolean equals(Card other) {
            return this.equalsNumber(other) && this.equalsShape(other);
        }

        public boolean equalsNumber(Card other) {
            return number == other.number;
        }

        public boolean equalsShape(Card other) {
            return shape == other.shape;
        }
    }

    public static void main(String[] args) {
        new SimpleCardGame().game(true);
    }

    private void game(boolean extended) {
        ArrayList<Card> p1 = new ArrayList<>();
        ArrayList<Card> p2 = new ArrayList<>();

        System.out.println("P1    P2");
        while (true) {
            Card c1 = drawCard();
            p1.add(c1);
            printCard(c1, extended);
            for (Card c: p2) {
                if (c.equalsShape(c1)) {
                    System.out.println();
                    System.out.println("P1 wins!");
                    return;
                }
            }

            System.out.print("    ");

            Card c2 = drawCard();
            p2.add(c2);
            printCard(c2, extended);
            for (Card c: p1) {
                if (c.equalsShape(c2)) {
                    System.out.println();
                    System.out.println("P2 wins!");
                    return;
                }
            }
            System.out.println();
        }
    }

    private Card drawCard() {
        Random rand = new Random();
        int n = 1 + rand.nextInt(13);
        int shape = 1 + rand.nextInt(4);
        return new Card(shape, n);
    }

    private void printCard(Card c, boolean extended) {
        if (extended)
            System.out.print(c.toExtendedString());
        else
            System.out.print(c.toString());
    }
}

