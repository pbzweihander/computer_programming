import java.util.Vector;

public class Room {
    private Vector<Animal> animals;
    private Vector<Boolean> foods;

    public Room() {
        animals = new Vector<>();
        foods = new Vector<>();
    }

    public void addAnimal(Animal ani) {
        animals.add(ani);
    }

    public void addFood(boolean food) {
        foods.add(food);
    }

    public Animal getAnimal(int index) {
        return animals.elementAt(index);
    }

    public boolean getFood(int index) {
        return foods.elementAt(index);
    }

    public int getAnimalsSize() {
        return animals.size();
    }

    public int getFoodsSize() {
        return foods.size();
    }
}
