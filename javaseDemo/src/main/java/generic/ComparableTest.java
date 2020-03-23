package generic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class ComparableTest {
    static List<Dog> dogList = new ArrayList<>();
    static List<Animal> animalList = new ArrayList<>();

    public static <T extends Comparable<T>> void cmp(T comparable) {

    }

    public static void cmp1(Comparable<Animal> comparable) {

    }

    static void CmpTest() {
        Comparable<Animal> animalComparable = new Animal();
//        Comparable<Dog> dogComparable = new Dog();//无法通过编译,Dog不是 Comparable<Dog>,只是Comparable<Animal>
        cmp1(animalComparable);
        ;
    }


    public static <T extends Comparable<T>> void cmpCollection(List<T> collection) {

    }

    public static <T extends Comparable<? super T>> void cmpCollection1(List<T> collection) {

    }

    public static void cmpCollection() {

        cmpCollection(animalList);

        //无法通过编译,因为cmpCollection方法只接收List<T extends Comparable<T>>,
        //而List<Dog>是List<Comparable<Animal>>,不是List<Comparable<Dog>>
        /* cmpCollection(dogList);*/


        cmpCollection1(dogList);
    }


    /*测试java数组是否协变*/
    public static void testCmpArrays() {
        Dog[] dogs = new Dog[100];
        Animal[] animals = new Dog[100];

//        cmpArrays(dogs);
//        cmpArrays(animals);

        animals[0] = new Animal(); //编译通过,但运行会产生ArrayStoreException异常
    }


    public static void cmpCollect(List<Animal> animals) {

    }

    /*测试java泛型集合是否协变*/
    static void testCmpCollect() {
        List<Dog> dogList = null;
        List<Animal> animalList = null;
//        cmpCollect(dogList);  无法通过编译
        cmpCollect(animalList);
    }


    static void te() {
        Dog dog = new Dog();
        cmp(dog); //这里
    }

    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.setAge(22);
        Function<Dog, Integer> getAge = Dog::getAge;
        Integer apply = getAge.apply(dog);
        System.out.println(apply);


    }

}

class Animal implements Comparable<Animal> {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Animal o) {
        return age - o.age;
    }
}

class Dog extends Animal {

}

class Humam extends Animal {

}