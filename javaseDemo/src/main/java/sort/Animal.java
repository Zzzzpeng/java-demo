package sort;

import java.util.ArrayList;
import java.util.List;

public class Animal implements Comparable<Animal> {
    public static <T extends Comparable<T>> void cm(T t){
        System.out.println(t);
    }
    public static void main(String[] args) {
        List<Dog> lis = new ArrayList<>();
        List<Animal> lis3 = new ArrayList<>();
        List<? extends Animal> lis1 = lis;
        List<? super Dog> lis2  = lis3;
        lis2.add(new Dog());
        Demo<Dog> demo = null;
        Dog dog = new Dog();
        cm(dog);



    }


    @Override
    public int compareTo(Animal o) {
        return 0;
    }

}

class Dog extends Animal {

}

class Demo<T extends Comparable<? super T>>{}