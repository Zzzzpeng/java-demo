package generic;



import java.util.*;

public class GenericTest {
    public static <T extends Comparable<? super T>> void sort1(List<T> list) {

    }

    public static <T extends Comparable<T>> void sort2(List<T> list) {

    }

    public static <T extends Comparable<T>> void sort3(T[] list) {

    }

    public static void main(String[] args) {
        {
            List<Animal> animals = new ArrayList<>();
            animals.add(new Animal());
            animals.add(new Animal());

            List<Dog> dogs = new ArrayList<>();
            dogs.add(new Dog());
            dogs.add(new Dog());


            sort2(animals);
//            sort2(dogs); //无法通过编译
        }


        {
            Dog[] dogs = new Dog[100];
            Animal[] animals = new Dog[100];
            sort3(dogs);
            sort3(animals);
        }
        Comparator<Animal> comparator = new Comparator<Animal>() {
            @Override
            public int compare(Animal o1, Animal o2) {
                return 0;
            }
        };
        Comparator<Dog> dogComparator = new Comparator<Dog>() {
            @Override
            public int compare(Dog o1, Dog o2) {
                return 0;
            }
        };
        TreeMap<Dog, Object> objectObjectTreeMap = new TreeMap<>(Animal::compareTo);

    }
}


