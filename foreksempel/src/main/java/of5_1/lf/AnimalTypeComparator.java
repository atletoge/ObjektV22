package of5_1.lf;

import java.util.Comparator;

public class AnimalTypeComparator implements Comparator<Animal> {

    @Override
    public int compare(Animal o1, Animal o2) {
        if (o1 instanceof Dog) {
            if (o2 instanceof Dog) {
                return 0;
            } else {
                return 1;
            }
        }
        return -1;
    }

}
