public class Carnivore extends Animal {
    public Carnivore(String speciesName, String name, int agr) {
        super('C', speciesName, name, agr);
    }

    @Override
    public String getSpeciesName() {
        return speciesName + "(Carni)";
    }
}
