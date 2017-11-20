public class Omnivore extends Animal {
    public Omnivore(char type, String speciesName, String name, int agr) {
        super(type, speciesName, name, agr);
    }

    @Override
    public String getSpeciesName() {
        return speciesName + "(Omni)";
    }
}
