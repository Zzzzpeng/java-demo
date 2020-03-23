package com.chen.annotationcac.annotation;

public class Ins extends SuperIns {
    public static void main(String[] args) {
        Class<SuperIns> superInsClass = SuperIns.class;
        boolean annotationPresent = superInsClass.isAnnotationPresent(MyInherited.class);
        System.out.println(annotationPresent);

        Class<Ins> insClass = Ins.class;
        boolean ispre = insClass.isAnnotationPresent(MyInherited
                .class);
        System.out.println(ispre);
    }
}
@MyInherited
@NoInherite
class SuperIns{

}

