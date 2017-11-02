public class Fly extends Bug {
    public Fly(int age) {
        super(age);
    }

    @Override
    public void buzz() {
        System.out.println("Aeeeeeeeng");
    }
}
