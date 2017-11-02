public class Dog extends Life {
    private String ownerName;

    public Dog(String owner, int age) {
        super(age);
        ownerName = owner;
    }

    public void bark() {
        System.out.println("BowWow");
    }

    @Override
    public void eat() {
        System.out.println("A dog is eating a food voraciously");
    }

    public String owner() {
        return ownerName;
    }
}
