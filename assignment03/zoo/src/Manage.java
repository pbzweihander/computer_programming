import java.util.Vector;

public class Manage {
    private ManageInfo info;

    public Manage() {
        info = new ManageInfo();
    }

    public Manage(ManageInfo info) {
        this.info = info;
    }

    public void set(ManageInfo info) {
        this.info = info;
    }

    public Report report() {
        Vector<String> lines = new Vector<>();

        for (int i = 0; i < info.getLength(); i++) {
            Room room = info.getRoom(i);
            int max_agr = 0;
            boolean is_there_aggression_problem = false;

            boolean is_there_carnivore = false;
            boolean is_there_herbivore = false;
            boolean is_there_c_food = false;
            boolean is_there_h_food = false;
            boolean is_there_food_problem = false;

            for (int j = 0; j < room.getAnimalsSize(); j++) {
                Animal animal = room.getAnimal(j);
                if (max_agr < animal.agr)
                    max_agr = animal.agr;
                switch (animal.type) {
                case 'C':
                    is_there_carnivore = true;
                    break;
                case 'H':
                    is_there_herbivore = true;
                    break;
                }
            }
            is_there_aggression_problem = max_agr > 0;
            for (int j = 0; j < room.getFoodsSize(); j++) {
                if (room.getFood(j))
                    is_there_c_food = true;
                else
                    is_there_h_food = true;
            }
            is_there_food_problem = (is_there_carnivore && !is_there_c_food)
                    || (is_there_herbivore && !is_there_h_food);

            String line = "Room " + (i + 1) + " : ";
            if (max_agr == 1)
                line += "CareTreat";
            else if (max_agr == 2)
                line += "Dangerous";
            if (is_there_aggression_problem && is_there_food_problem)
                line += ", ";
            if (is_there_food_problem)
                line += "FoodType";

            if (is_there_aggression_problem || is_there_food_problem)
                lines.add(line);
        }

        return new Report(lines);
    }
}
