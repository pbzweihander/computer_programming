public class Herbivore extends Animal {
    public Herbivore(char type, String speciesName, String name, int agr) {
        super(type, speciesName, name, agr);
    }

    @Override
    public String getSpeciesName() {
        return speciesName + "(Herbi)";
    }
}
