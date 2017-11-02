import java.util.Vector;

/**
 * Created by tcs정원일 on 2017-11-02.
 */
public class Test {
    public static void main(String[] args) {
        String[] owners = {"Mike", "Billy"};
        Dog[] dogs = {new Dog(owners[0], 7), new Dog(owners[1], 1)};
        Bug aBug = new Bug(2);
        Fly aFly = new Fly(1);
        Life aLife = new Life(3);
        Life bLife = new Life(3);
        Vector<Life> life_vec = new Vector<>();
        life_vec.add(dogs[0]);
        life_vec.add(dogs[1]);
        life_vec.add(aBug);
        life_vec.add(aFly);
        life_vec.add(aLife);
        life_vec.add(bLife);

        for (int i = 0; i < life_vec.size(); i++) {
            System.out.println("Age: " + life_vec.elementAt(i).getAge());
            life_vec.elementAt(i).breathe();
            life_vec.elementAt(i).eat();
            if (life_vec.elementAt(i) instanceof Bug)
                ((Bug) life_vec.elementAt(i)).buzz();
            else if (life_vec.elementAt(i) instanceof Dog) {
                System.out.print("When " + ((Dog) life_vec.elementAt(i)).owner() + " calls it says ");
                ((Dog) life_vec.elementAt(i)).bark();
            }
        }

        if (aLife.equals(bLife)) {
            System.out.println("Save 3!" + " and we are friends");
        }
    }
}