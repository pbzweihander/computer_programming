public class Omnivore extends Animal {
    public Omnivore(String speciesName, String name, int agr) {
        super('O', speciesName, name, agr);
    }

    @Override
    public String getSpeciesName() {
        return speciesName + "(Omni)";
    }
}
