import java.util.Vector;

public class Room {
    private Vector<Animal> animals;
    private Vector<Boolean> foods;

    public Animal getAnimal(int index) {
        return animals.elementAt(index);
    }

    public boolean getFood(int index) {
        return foods.elementAt(index);
    }
}
