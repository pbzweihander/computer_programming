public class Life {
    private int age;

    public Life(int age) {
        this.age = age;
    }

    public void breathe() {
        System.out.println("A life is breatheing");
    }

    public void eat() {
        System.out.println("A life is eatting");
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Life)
            return this.age == ((Life)other).age;
        return false;
    }
}
