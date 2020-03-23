package function;

import java.util.*;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        compTest();
    }
    static void compTest(){
        List<Student> students = Arrays.asList(new Student("z1", 5), new Student("z2", 1));
        List<Integer> ints = Arrays.asList(1, 5, 0);

    }
}

class Student{
    String name;
    long id;

    public Student() {
    }

    public Student(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}