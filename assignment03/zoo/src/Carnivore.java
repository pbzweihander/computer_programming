public class Carnivore extends Animal {
    public Carnivore(char type, String speciesName, String name, int agr) {
        super(type, speciesName, name, agr);
    }

    @Override
    public String getSpeciesName() {
        return speciesName + "(Carni)";
    }
}
