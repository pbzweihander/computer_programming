import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Vector<Room> rooms = new Vector<>();

        Room r1 = new Room();
        r1.addAnimal(new Carnivore("Ani1", "ani1", 0));
        r1.addAnimal(new Herbivore("Ani2", "ani1", 1));
        r1.addAnimal(new Omnivore("Ani3", "ani3", 2));
        r1.addFood(true);
        r1.addFood(false);
        rooms.add(r1);

        Room r2 = new Room();
        r2.addAnimal(new Carnivore("Ani1", "ani1", 0));
        r2.addAnimal(new Herbivore("Ani2", "ani1", 1));
        r2.addFood(false);
        rooms.add(r2);

        Room r3 = new Room();
        r3.addAnimal(new Carnivore("Ani1", "ani1", 0));
        r3.addAnimal(new Herbivore("Ani2", "ani1", 0));
        r3.addFood(true);
        rooms.add(r3);

        Room r4 = new Room();
        r4.addAnimal(new Carnivore("Ani1", "ani1", 0));
        r4.addAnimal(new Herbivore("Ani2", "ani1", 0));
        r4.addFood(true);
        r4.addFood(false);
        rooms.add(r4);

        Room r5 = new Room();
        r5.addAnimal(new Carnivore("Ani1", "ani1", 0));
        r5.addAnimal(new Herbivore("Ani2", "ani1", 0));
        r5.addAnimal(new Omnivore("Ani3", "ani3", 1));
        r5.addFood(true);
        r5.addFood(false);
        rooms.add(r5);

        Room r6 = new Room();
        r6.addAnimal(new Omnivore("Ani1", "ani1", 0));
        r6.addFood(true);
        r6.addFood(false);
        rooms.add(r6);

        Room r7 = new Room();
        r7.addAnimal(new Carnivore("Ani1", "ani1", 2));
        r7.addFood(true);
        rooms.add(r7);

        Manage manage = new Manage(new ManageInfo(rooms));
        Report report = manage.report();
        report.printAll();
        System.out.println(r1.getAnimal(0).getSpeciesName());
    }
}
