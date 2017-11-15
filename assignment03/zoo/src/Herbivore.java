public class Herbivore extends Animal {
    public Herbivore(String speciesName, String name, int agr) {
        super('H', speciesName, name, agr);
    }

    @Override
    public String getSpeciesName() {
        return speciesName + "(Herbi)";
    }
}
