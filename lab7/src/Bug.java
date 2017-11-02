public class Bug extends Life {

    public Bug(int age) {
        super(age);
    }

    public void buzz() {
        System.out.println("buzzzzz");
    }

    @Override
    public void breathe() {
        System.out.println("A bug is breatheing");
    }
}
