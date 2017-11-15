public class Animal {
    protected char type;
    protected String speciesName;
    protected String name;
    protected int agr;

    public Animal(char type, String speciesName, String name, int agr) {
        this.type = type;
        this.speciesName = speciesName;
        this.name = name;
        this.agr = agr;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public String getName() {
        return name;
    }

    public char getType() {
        return type;
    }

    public int getAggresionLevel() {
        return agr;
    }
}
