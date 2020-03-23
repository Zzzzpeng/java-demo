package anotationTest;

import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.util.Arrays;

@DbTable(name="student")
class Student {
    String name;
    String id;
    int age;


}
class B extends Student{

}

public class Test {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(Student.class.getAnnotations()));
        System.out.println(Student.class.getAnnotation(DbTable.class));
//        Class<Student> studentClass = Student.class;
//        Field[] declaredFields = studentClass.getDeclaredFields();
//        for (Field declaredField : declaredFields) {
//            String name = declaredField.getName();
//            System.out.println(name);
//        }






    }



}
