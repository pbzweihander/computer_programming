import java.util.Vector;

public class Room {
    private Vector<Animal> animals;
    private Vector<Boolean> foods;

    private boolean[] agrs;
    private boolean hasCarnivore;
    private boolean hasHerbivore;
    private boolean hasCFood;
    private boolean hasHFood;

    public Room() {
        animals = new Vector<>();
        foods = new Vector<>();
        agrs = new boolean[3];
        agrs[0] = false;
        agrs[1] = false;
        agrs[2] = false;
        hasCarnivore = false;
        hasHerbivore = false;
        hasCFood = false;
        hasHFood = false;
    }

    public void addAnimal(Animal ani) {
        animals.add(ani);
        switch (ani.getType()) {
            case 'C':
                hasCarnivore = true;
                break;
            case 'H':
                hasHerbivore = true;
                break;
        }
        agrs[ani.getAggresionLevel()] = true;
    }

    public void addFood(boolean food) {
        foods.add(food);
        if (food)
            hasCFood = true;
        else
            hasHFood = true;
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

    public boolean isCareTreat() {
        return agrs[1];
    }

    public boolean isDangerous() {
        return agrs[2];
    }

    public boolean isFoodType() {
        return (hasCarnivore && !hasCFood) || (hasHerbivore && !hasHFood);
    }
}
