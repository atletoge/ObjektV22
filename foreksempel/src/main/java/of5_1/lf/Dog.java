package of5_1.lf;

public class Dog implements Animal {

    private String name;
    private int age;

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String makeSound() {
        return name + " sier bjeff!";
    }

    @Override
    public int compareTo(Animal o) {
        return new AnimalAgeComparator().compare(this, o);
    }

}
